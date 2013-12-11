<?php
require_once('database/database.php');
require_once('model/student.php');

$parameters = array
(
        ':login' => null,
        ':password' => null
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

$salt = substr($parameters[':password'], strlen($parameters[':password'])-2, strlen($parameters[':password']));
$md5password = md5(md5($parameters[':password']).$salt);
$parameters[':password'] = $md5password;

$student = $db->selectOne('Student', 'student', 'login = :login AND password = :password', $parameters);

if($student !== false)
{
        $token = md5(time() . $student->login . $student->password);
        $student->token = $token;

        if($db->update($student, 'student', 'id = :id', array(':id' => $student->id)))
        {
                $json = array(
                        'error' => false,
                        'token' => $token
                );
        }
}
echo json_encode($json);
?>