<?php
try {//creation de l'objet PDO
	$db = new PDO('mysql:host=localhost;dbname=test','root','root');
	$id_uv = $_POST[id];


	//création de la requête avec la methode des marqueurs
	$strSQL = "SELECT curricula, objectives, type_uv, credits, availability, lectures, tutorials, practicals, personnal FROM uv, description_uv WHERE uv.id_description = description_uv.id and uv.id = '$id_uv'";


	$stmt = $db->prepare($strSQL); // création de l'objet PDOStatment
	$stmt->execute(array('id_uv'));


	$row = array();
	//affichage du résultat
	while($result = $stmt->fetch(PDO::FETCH_OBJ)){
		$row['curricula'] = $result->curricula;
		$row['objectives'] = $result->objectives;
		$row['type_uv'] = $result->type_uv;
		$row['credits'] = $result->credits;
		$row['availability'] = $result->availability;
		$row['lectures'] = $result->lectures;
		$row['tutorials'] = $result->tutorials;
		$row['practicals'] = $result->practicals;
		$row['personnal'] = $result->personnal;
	}
	if($row == null){
		$row['error'] = true;
	} else {
		$row['error'] = false;
	}
    header('Content-type: application/json');
    echo json_encode(array('descritpion_uv'=>$row));
} catch (PDOException $e){ //erreur de connexion à la base
	print "Erreur : ".$e->getMessage();
	die();
}
$db = null; //on ferme la connexion
?>