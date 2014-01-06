<?php
require_once('database/database.php');
require_once('model/student.php');
require_once('model/uv.php');
require_once('model/category.php');
require_once('model/description.php');
//Script permettant de récupérer la description détaillée d'une UV
//Initialisation du tableau de paramétres
$parameters = array
(
    ':token' => null,
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
    //Récupération de la description détaillée pour l'UV donnée
    $details = $db->selectOne('Description', 'description_uv', 'id = :id_description', array(':id_description' => $parameters[':id_description']));
    //Création du JSON
    $json = array(
    'error' => false,
    'description' => $details
    );
}

//Affichage du JSON
echo json_encode($json);
?>