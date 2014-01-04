<?php
//Tableau récapitulatif des informations pour la connexion à la BD (nom BD, login, mdp)
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