<?php
require_once('database/database.php');
require_once('model/comment.php');
require_once('model/student.php');
require_once('model/description.php');
require_once('model/uv.php');
//Script permettant l'ajout de commentaire concernant une UV
//Initialisation du tableau de paramétres
$parameters = array
(
    ':token' => null,
    ':text' => null,
    ':id_student' => null,
    ':id_uv' => null,
    ':mark' => null,
    ':id_description' => null
);
//Récupération des paramétres passés en POST
foreach($_POST as $key => $value)
{
    $parameters[":$key"] = $value;
}
//Initialisation du JSON
$json = array(
    'error' => true
);

//Connexion à la BD
$configDB = require_once('configDB.php');
$db = new Database($configDB['dsn'], $configDB['username'], $configDB['password'], $configDB['options']);

//Récupération de l'étudiant correspondant au jeton passé en paramétre
$student = $db->selectOne('Student', 'student', 'token = :token', array(':token' => $parameters[':token']));
//Si l'étudiant existe
if($student !== false){
	//Création de l'object commentaire
	$comment = new Comment();
	$comment->text = $parameters[":text"];
	$comment->id_student = $parameters[":id_student"];
	$comment->id_uv = $parameters[":id_uv"];
	$comment->mark = $parameters[":mark"];
	//Insertion du commentaire dans la BD
	$result = $db->insert($comment, 'comment');

    //Récupération des commentaires relatifs à l'UV
	$comments = $db->selectSeveral('Comment', 'comment', 'id_uv = :id_uv', array(':id_uv' => $parameters[':id_uv']));
	//Initialisation variables
	$total = 0;
	$ids_student = 0;
	foreach ($comments as $current_comment) {
		if($current_comment->mark != -1){
			if($comment->id_student == $current_comment->id_student){
				$ids_student +=1;
				$current_comment->mark = $comment->mark;
				if($db->update($current_comment, 'comment', 'id = :id', array(':id' => $current_comment->id))){

				}
				$total += $current_comment->mark;
			}
		}
	}
	//Calcul de la nouvelle note moyenne
	$total = $total / $ids_student;

	if($parameters[':id_description'] == 0){
		$desc = new Description();
		$desc->curricula = "";
    	$desc->objectives = "";
    	$desc->type_uv = "";
    	$desc->credits = "";
    	$desc->availability= "";
    	$desc->lectures = "";
    	$desc->tutorials = "";
    	$desc->practicals = "";
    	$desc->personnal = "";
    	$desc->avg_mark = '-1';

		$req = $db->count('description_uv');
		$count = $req + 1;
		$resultbis = $db->insert($desc, 'description_uv');
		$parameters[':id_description'] = $count;
		$uv = $db->selectOne('Uv', 'uv', 'id = :id', array(':id' => $parameters[':id_uv']));
		$uv->id_description = $parameters[':id_description'];
		$resultbis = $db->update($uv, 'uv', 'id = :id', array(':id' => $parameters[':id_uv']));
	}

    //maj de la note moyenne de l'UV dans la BD
	$detail = $db->selectOne('Description', 'description_uv', 'id = :id_description', array(':id_description' => $parameters[':id_description']));
	$detail->avg_mark = $total;

	if($db->update($detail, 'description_uv', 'id = :id', array(':id' => $detail->id))){
        //Création du JSON
        $json = array(
            'error' => false,
            'result' => $result
        );
    }
}

//Affichage du JSON
echo json_encode($json);
?>