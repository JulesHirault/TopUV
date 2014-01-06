<?php
class Description
    {
    public $id;
    public $curricula;
    public $objectives;
    public $type_uv;
    public $credits;
    public $availability;
    public $lectures;
    public $tutorials;
    public $practicals;
    public $personnal;
    public $avg_mark;

    public function toDatabase(){
            $object = get_object_vars($this);
            return $object;
    }
}
?>