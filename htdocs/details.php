<?php
require_once('database/database.php');
require_once('model/student.php');
require_once('model/uv.php');
require_once('model/category.php');
require_once('model/description.php');

$parameters = array
(
        ':token' => null,
        ':id_description' => null
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

$student = $db->selectOne('Student', 'student', 'token = :token', array(':token' => $parameters[':token']));

if($student !== false){

	$details = $db->selectOne('Description', 'description_uv', 'id = :id_description', array(':id_description' => $parameters[':id_description']));

	$json = array(
	    'error' => false,
	    'description' => $details
	);
}

echo json_encode($json);
?>