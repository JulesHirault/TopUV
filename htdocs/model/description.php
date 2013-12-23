<?php
class Description
{
        public $id;
        public $uv;
        public $curricula;
        public $objectives;
        public $type_uv;
        public $credit;
        public $time_cours;
        public $time_td;
        public $time_tp;
        public $time_perso;
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