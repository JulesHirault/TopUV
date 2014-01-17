<?php
require_once('database/database.php');
require_once('model/student.php');
require_once('model/uv.php');
require_once('model/description.php');
//Script permettant de récupérer la liste des 10 UVs les mieux notées
//Récupérer les 10 uv les moins bien notées revient à selectionner les 10 premières uv après les avoir toutes triées (ordre décroissant) en fonction de leur note moyenne
//Initialisation du tableau de paramétres
$parameters = array
(
        ':token' => null
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

//Connexion à la BDD
$configDB = require_once('configDB.php');
$db = new Database($configDB['dsn'], $configDB['username'], $configDB['password'], $configDB['options']);

//Récupération des descriptions des 10 meilleures UVs (Objets Description)
$descriptions = $db->selectTop('Description', 'description_uv', 'avg_mark DESC');

//Récupérations de la liste des 10 meilleures UVs (Objets UV) dans la BD
$uvs = array();
foreach ($descriptions as $key => $value) {
	$temp = $db->selectOne('Uv', 'uv', 'id_description = :id', array(':id' => $descriptions[$key]->id));
    array_push($uvs, $temp);
}

//Création du JSON avec la liste des UVs
$json = array(
    'error' => false,
    'top' => $uvs
);

//Affichage du JSON
echo json_encode($json);
?>