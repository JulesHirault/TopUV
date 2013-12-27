<?php
class Description
{
        public $id;
        public $uv;
        public $curricula;
        public $objectives;
        public $type_uv;
        public $credit;
        public $tutorials;
        public $practicals;
        public $personnal;
        public $lectures;
        public $availibility;

        public $uv;

        public function toDatabase()
        {
                $object = get_object_vars($this);
                unset($object['uv']);
                return $object;
        }
}
?>