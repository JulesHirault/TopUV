<?php
//Tableau récapitulatif des informations pour la création d'une instance de connexion à la BD (nom BD, login, mot de passe)
return array
(
        'dsn' => 'mysql:dbname=u956485103_topuv;host=mysql.hostinger.fr',
        'username' => 'u956485103_flo',
        'password' => 'projetif26',
        'options' => array
        (
                PDO::MYSQL_ATTR_INIT_COMMAND => "SET NAMES 'utf8'"
        )
);
?>