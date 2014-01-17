<?php
require_once('database/database.php');
require_once('model/student.php');
//Script d'authentification
//Création d'une instance de connexion (cf configDB.php)
//Initialisation du tableau de paramétres
$parameters = array
(
    ':login' => null,
    ':password' => null
);

//Récupération des paramétres passés en POST (login + password)
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

//Création du grain de sel pour le mdp
$salt = substr($parameters[':password'], strlen($parameters[':password'])-2, strlen($parameters[':password']));
//Cryptage en MD5 du mdp
$md5password = md5(md5($parameters[':password']).$salt);
//Ajout du mdp encrypté au tableau de paramétres
$parameters[':password'] = $md5password;

//Récupération dans la BD de l'étudiant correspondant au couple de paramétres login + mdp
$student = $db->selectOne('Student', 'student', 'login = :login AND password = :password', $parameters);

//Si l'étudiant existe...
if($student !== false)
{
    //Création du jeton de connexion
    $token = md5(time() . $student->login . $student->password);
    //Affectation du jeton à l'étudiant
    $student->token = $token;
    //Création du JSON et maj de la BD
    if($db->update($student, 'student', 'id = :id', array(':id' => $student->id)))
    {
        $json = array(
        'error' => false,
        'token' => $token,
        'id' => $student->id
        );
    }
}
//Affichage du JSON
echo json_encode($json);
?>