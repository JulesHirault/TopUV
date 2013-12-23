<?php
class Category
{
        public $id;
        public $label;

        public function toDatabase()
        {
                $object = get_object_vars($this);
                return $object;
        }
}
?>