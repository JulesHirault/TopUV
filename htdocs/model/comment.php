<?php
class Comment
{
        public $id;
        public $text;
        public $id_student;
        public $id_uv;
        public $student;

        public function toDatabase()
        {
                $object = get_object_vars($this);
                unset($object['student']);
                return $object;
        }
}
?>