<?php
require_once('database/database.php');
require_once('model/student.php');
require_once('model/uv.php');
require_once('model/category.php');
require_once('model/studentUv.php');
require_once('model/description.php');
//Script permettant de récupérer la liste complète des UVs.
//Initialisation du tableau de paramétres
$parameters = array
(
        ':token' => null,
        ':id_category' => null
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
    //S'il s'agit de mes UVs
	if($parameters[':id_category'] === "0"){
		$student->id = (int) $student->id;
		$ids = $db->selectSeveral('studentUv', 'student_uv', 'id_student = :student_id', array(':student_id' => $student->id));
		$uvs = array();
		foreach ($ids as $key => $value) {
			$temp = $db->selectOne('Uv', 'uv', 'id = :id_uv', array(':id_uv' => $ids[$key]->id_uv));
			$category = $db->selectOne('Category', 'category', 'id = :id_category', array(':id_category' => $temp->id_category));
			$temp->category = $category;
			array_push($uvs, $temp);
		}
	}
	//S'il s'agit des 10 meilleures UVs (mieux notées)
	else if($parameters[':id_category'] === "top"){
		$descriptions = $db->selectTop('Description', 'description_uv', 'avg_mark DESC');
		$uvs = array();
		foreach ($descriptions as $key => $value) {
			$temp = $db->selectOne('Uv', 'uv', 'id_description = :id', array(':id' => $descriptions[$key]->id));
			$category = $db->selectOne('Category', 'category', 'id = :id_category', array(':id_category' => $temp->id_category));
			$temp->category = $category;
			array_push($uvs, $temp);
		}

	}
	//S'il s'agit des 10 pires UVs (moins bien notées)
    else if($parameters[':id_category'] === "flop"){
		$descriptions = $db->selectTop('Description', 'description_uv', 'avg_mark ASC');
		$uvs = array();
		foreach ($descriptions as $key => $value) {
			$temp = $db->selectOne('Uv', 'uv', 'id_description = :id', array(':id' => $descriptions[$key]->id));
			$category = $db->selectOne('Category', 'category', 'id = :id_category', array(':id_category' => $temp->id_category));
			$temp->category = $category;
			array_push($uvs, $temp);
		}
	}
	//Dans les autres cas
	else {
		$uvs = $db->selectSeveral('Uv', 'uv', 'id_category = :id_category', array(':id_category' => $parameters[':id_category']));
		$category = $db->selectOne('Category', 'category', 'id = :id_category', array(':id_category' => $parameters[':id_category']));
		foreach ($uvs as $uv) {
			$uv->category = $category;
		}
	}
    //Création du JSON avec la liste des UVs selon les cas
	$json = array(
    	'error' => false,
        'uvs' => $uvs
    );
}

//Affichage du JSON
echo json_encode($json);
?>