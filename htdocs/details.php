<?php
require_once('database/database.php');
require_once('model/student.php');
require_once('model/uv.php');
require_once('model/category.php');
require_once('model/studentUv.php');

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

$desc = $db->selectOne('Description', 'description_uv', 'uv.id_description = description_uv.id AND uv.id = :id_uv', array(':id_uv' => $parameters[':id_uv']));

$json = array(
    'error' => false,
    'desc' => $desc
);

echo json_encode($json);
?>