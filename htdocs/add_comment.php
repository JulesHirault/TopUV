<?php
require_once('database/database.php');
require_once('model/comment.php');
require_once('model/student.php');
require_once('model/description.php');

$parameters = array
(
		':token' => '984ddb25d8f1cd3228b55be24d86dab6',
        ':text' => 'Cool',
		':id_student' => '1',
		':id_uv' => 'IF26',
		':mark' => '14',
		':id_description' => '1'
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


	$ids_student = array();
	foreach ($comments as $comment) {
		if($comment->mark != -1){

			$find = false;

			if(count($ids_student) == 0){
				array_push($ids_student, $comment->id_student);
			} else {
				foreach($ids_student as $id_student){
					if($id_student == $comment->id_student){
						$find = true;
					}
				}
			}
			
   			if($find == false){
   				$total += $comment->mark;
   			}
			
		}
	}
	$total / count($ids_student);

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