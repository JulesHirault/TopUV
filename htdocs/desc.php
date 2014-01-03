<?php
require_once('database/database.php');
require_once('model/student.php');
require_once('model/uv.php');
require_once('model/category.php');

$parameters = array
(
        ':token' => null,
        ':id_uv' => null
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

	$uvs = $db->selectSeveral('Uv', 'uv', 'uv.id = :id_uv', array(':id_uv' => $parameters[':id_uv']));
	$category = $db->selectOne('Category', 'category', 'id = :id_category', array(':id_category' => $uvs[0]->id_category));
	$uvs[0]->category = $category;

	$json = array(
    	'error' => false,
    	'uvs' => $uvs
	);
}
echo json_encode($json);
?>