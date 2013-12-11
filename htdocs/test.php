<?php
try {//creation de l'objet PDO
	$db = new PDO('mysql:host=localhost;dbname=test','root','root');
	$username = $_POST[username];
	$password = $_POST[password];
	//$username = "flo";
	//$password = "azerty";

	//création de la requête avec la methode des marqueurs
	$strSQL = "SELECT * FROM connexion WHERE login='$username' and mdp='$password'";


	$stmt = $db->prepare($strSQL); // création de l'objet PDOStatment
	$stmt->execute(array('mon_login','mon_password'));


	$row = array();
	//affichage du résultat
	while($result = $stmt->fetch(PDO::FETCH_OBJ)){
		$row['login'] = $result->login;
		$row['mdp'] = $result->mdp;
	}
	if($row == null){
		$row['error'] = true;
	} else {
		$row['error'] = false;
	}
    header('Content-type: application/json');
    echo json_encode(array('connexion'=>$row));
} catch (PDOException $e){ //erreur de connexion à la basse
	print "Erreur : ".$e->getMessage();
	die();
}
$db = null; //on ferme la connexion
?>