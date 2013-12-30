<?php
require_once('database/database.php');
require_once('model/student.php');
require_once('model/comment.php');

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

	$comments = $db->selectSeveral('Comment', 'comment', 'comment.id_uv = :id_uv', array(':id_uv' => $parameters[':id_uv']));

	if($comments != false){

		foreach ($comments as $comment) {
			$student = $db->selectOne('Student', 'student', 'id = :id_student', array(':id_student' => $comment->id_student));
			$student->login = "";
			$student->password = "";
			$student->token = "";
			$comment->student = $student;
		}

		$json = array(
	    	'error' => false,
	    	'comment' => $comments
		);
	}
}
echo json_encode($json);
?>