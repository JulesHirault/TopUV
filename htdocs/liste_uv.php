<?php
require_once('database/database.php');
require_once('model/student.php');
require_once('model/uv.php');
require_once('model/category.php');
require_once('model/studentUv.php');

$parameters = array
(
        ':token' => null,
        ':id_category' => null
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
	$parameters[':id_category'] = (int) $parameters[':id_category'];

	if($parameters[':id_category'] == 0){
		$student->id = (int) $student->id;
		$ids = $db->selectSeveral('studentUv', 'student_uv', 'id_student = :student_id', array(':student_id' => $student->id));

		$uvs = array();
		foreach ($ids as $key => $value) {
			$temp = $db->selectOne('Uv', 'uv', 'id = :id_uv', array(':id_uv' => $ids[$key]->id_uv));
			$category = $db->selectOne('Category', 'category', 'id = :id_category', array(':id_category' => $temp->id_category));
			$temp->category = $category;
			array_push($uvs, $temp);
		}
		
	} else if($parameters[':id_category'] == 8){

	} else if($parameters[':id_category'] == 9){

	} else {
		$uvs = $db->selectSeveral('Uv', 'uv', 'id_category = :id_category', array(':id_category' => $parameters[':id_category']));
		$category = $db->selectOne('Category', 'category', 'id = :id_category', array(':id_category' => $parameters[':id_category']));

		foreach ($uvs as $uv) {
			$uv->category = $category;
		}
	}

	$json = array(
    	'error' => false,
        'uvs' => $uvs
    );
}
echo json_encode($json);
?>