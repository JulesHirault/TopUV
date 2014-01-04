<?php
class Database
{
    private $pdo;

    //Fonction de connexion à la BD
    public function Database($dsn, $username, $password, $options = null){
            $this->pdo = new PDO($dsn, $username, $password, $options);
    }
    //Fonction de selection pour 1 élément dans la BD
    public function selectOne($class, $table, $where, $whereArgs = array(), $order = null){
        $sql = "SELECT * FROM $table WHERE $where";
        if($order != null){
                $sql .= " ORDER BY $order";
        }
        $sql .= ' LIMIT 1';
        $pdoStatement = $this->pdo->prepare($sql);
        $pdoStatement->execute($whereArgs);
        return $pdoStatement->fetchObject($class);
    }
    //Fonction pour la selection de plusieurs éléments dans la BD
    public function selectSeveral($class, $table, $where, $whereArgs = array()){
        $sql = "SELECT * FROM $table WHERE $where";
        $pdoStatement = $this->pdo->prepare($sql);
        $pdoStatement->execute($whereArgs);
        return $pdoStatement->fetchAll(PDO::FETCH_CLASS, $class);
    }
    //Fonction de selection des 10 premiers éléments dans la BD (les 10 meilleures uv ou les 10 plus mauvaises)
    public function selectTop($class, $table, $order){
        $sql = "SELECT * FROM $table ORDER BY $order LIMIT 10";
        $pdoStatement = $this->pdo->prepare($sql);
        $pdoStatement->execute();
        return $pdoStatement->fetchAll(PDO::FETCH_CLASS, $class);
    }
    //Fonction pour l'insertion d'élément dans la BD
    public function insert($model, $table) {
        $fields = '';
        $values = '';
        $whereArgs = array();
        foreach($model->toDatabase() as $name => $value){
            if($fields != ''){
                    $fields .= ', ';
                    $values .= ', ';
            }
            $fields .= $name;
            $values .= ":$name";
            $whereArgs[":$name"] = $value;
        }
        $sql = "INSERT INTO $table ($fields) VALUES ($values)";
        $pdoStatement = $this->pdo->prepare($sql);
        $result = $pdoStatement->execute($whereArgs);
        if(!$result){
                return false;
        }
        return $this->pdo->lastInsertId();

    }
    //Fonction pour la maj d'élément dans la BD
    public function update($model, $table, $where, $whereArgs = array()){
        $set = '';
        foreach($model->toDatabase() as $name => $value)
        {
                if($set != ''){
                        $set .= ', ';
                }
                $set .= "$name = :$name";
                $whereArgs[":$name"] = $value;
        }
        $sql = "UPDATE $table SET $set WHERE $where";
        $pdoStatement = $this->pdo->prepare($sql);
        return $pdoStatement->execute($whereArgs);
    }
    //Fonction pour la suppression d'élément dans la BD
    public function delete($table, $where, $whereArgs = array()){
            $sql = "DELETE FROM $table WHERE $where";
            $pdoStatement = $this->pdo->prepare($sql);
            return $pdoStatement->execute($whereArgs);
    }
}
?>