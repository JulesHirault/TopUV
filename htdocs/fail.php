<?php
require_once('database/database.php');
require_once('model/student.php');
require_once('model/uv.php');
require_once('model/description.php');
$parameters = array
(
        ':token' => null
);
foreach($_POST as $key => $value)
{
        $parameters[":$key"] = $value;
}
$json = array(
        'error' => true
);

$configDB = require_once('configDB.php');
$db = new Database($configDB['dsn'], $configDB['username'], $configDB['password'], $configDB['options']);

//$student = $db->selectOne('Student', 'student', 'token = :token', array(':token' => $parameters[':token']));

//if($student !== false){

$descriptions = $db->selectTop('Description', 'description_uv', 'avg_mark ASC');
$uvs = array();
foreach ($descriptions as $key => $value) {
	$temp = $db->selectOne('Uv', 'uv', 'id_description = :id', array(':id' => $descriptions[$key]->id));
	array_push($uvs, $temp);
	}
	
$json = array(
    'error' => false,
    'fails' => $uvs
);
//}
echo json_encode($json);
?>