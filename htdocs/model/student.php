<?php
class Student
{
    public $id;
    public $login;
    public $password;
    public $name;
    public $surname;
    public $picture;
    public $token;

    public function toDatabase(){
            $object = get_object_vars($this);
            return $object;
    }
}
?>