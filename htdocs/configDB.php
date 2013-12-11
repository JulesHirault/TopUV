<?php

return array
(
        'dsn' => 'mysql:dbname=topuv;host=localhost',
        'username' => 'root',
        'password' => 'root',
        'options' => array
        (
                PDO::MYSQL_ATTR_INIT_COMMAND => "SET NAMES 'utf8'"
        )
);
?>