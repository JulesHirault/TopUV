<?php
class Mark
{
        public $value;
        public $id_student;
        public $id_uv;

        public $student;
        public $uv;

        public function toDatabase()
        {
                $object = get_object_vars($this);
                unset($object['student']);
                unset($object['uv']);
                return $object;
        }
}
?>