<?php
require_once('database/database.php');
require_once('model/student.php');
require_once('model/uv.php');
require_once('model/category.php');
require_once('model/description.php');

$parameters = array
(
        ':token' => null,
        ':id_uv' => 'IF26',
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

$details = $db->selectSeveral('Description', 'description_uv, uv', 'uv.id_description = description_uv.id AND uv.id = :id_uv', array(':id_uv' => $parameters[':id_uv']));

$json = array(
    'error' => false,
    'details' => $details
);

echo json_encode($json);
?>