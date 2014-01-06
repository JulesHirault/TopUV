<?php
class Uv
{
    public $id;
    public $label;
    public $id_description;
    public $id_category;

    public $category;

    public function toDatabase(){
        $object = get_object_vars($this);
        unset($object['category']);
        return $object;
    }
}
?>