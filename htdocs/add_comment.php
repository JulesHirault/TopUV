<?php
require_once('database/database.php');
require_once('model/comment.php');

$parameters = array
(
        ':text' => 'coucousalutcava',
		':id_student' => 1,
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
$result = $db->insert($parameters, 'Comment');
?>