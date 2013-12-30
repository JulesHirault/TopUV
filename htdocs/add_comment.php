<?php
require_once('database/database.php');
require_once('model/comment.php');

$parameters = array
(
        ':text' => '',
		':id_student' => '',
		':id_uv' => ''
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

$comment = new Comment();
$comment->text = $parameters[":text"];
$comment->id_student = $parameters[":id_student"];
$comment->id_uv = $parameters[":id_uv"];
$result = $db->insert($comment, 'comment');
?>