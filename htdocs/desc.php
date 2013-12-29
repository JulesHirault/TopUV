<?php
require_once('database/database.php');
require_once('model/student.php');
require_once('model/uv.php');

$parameters = array
(
        ':token' => null,
        ':id_uv' => 'IF26'
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

$desc = $db->selectOne('Uv', 'uv', 'uv.id = :id_uv', array(':id_uv' => $parameters[':id_uv']));

$json = array(
    'error' => false,
    'desc' => $desc
);

echo json_encode($json);
?>