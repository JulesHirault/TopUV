<?php
require_once('database/database.php');
require_once('model/student.php');
require_once('model/description.php');

$parameters = array
(
        ':token' => null
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

//$student = $db->selectOne('Student', 'student', 'token = :token', array(':token' => $parameters[':token']));

//if($student !== false){

$top = $db->selectTop('Description', 'description_uv', 'avg_mark ASC');
$json = array(
    'error' => false,
    'fails' => $top
);
//}
echo json_encode($json);
?>