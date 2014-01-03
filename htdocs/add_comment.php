<?php
require_once('database/database.php');
require_once('model/comment.php');
require_once('model/student.php');
require_once('model/description.php');

$parameters = array
(
		':token' => null,
        ':text' => null,
		':id_student' => null,
		':id_uv' => null,
		':mark' => null,
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

	$comment = new Comment();
	$comment->text = $parameters[":text"];
	$comment->id_student = $parameters[":id_student"];
	$comment->id_uv = $parameters[":id_uv"];
	$comment->mark = $parameters[":mark"];
	$result = $db->insert($comment, 'comment');

	$comments = $db->selectSeveral('Comment', 'comment', 'id_uv = :id_uv', array(':id_uv' => $parameters[':id_uv']));
	$total = 0;


	$ids_student = 0;
	foreach ($comments as $current_comment) {
		if($current_comment->mark != -1){

			if($comment->id_student == $current_comment->id_student){
				$ids_student +=1;
				$current_comment->mark = $comment->mark;
				if($db->update($current_comment, 'comment', 'id = :id', array(':id' => $current_comment->id))){

				}
				$total += $current_comment->mark;
			}
		}
	}
	$total = $total / $ids_student;

	$detail = $db->selectOne('Description', 'description_uv', 'id = :id_description', array(':id_description' => $parameters[':id_description']));
	$detail->avg_mark = $total;

	if($db->update($detail, 'description_uv', 'id = :id', array(':id' => $detail->id)))
        {
			$json = array(
        		'error' => false,
        		'result' => $result
			);
        }

}
echo json_encode($json);
?>