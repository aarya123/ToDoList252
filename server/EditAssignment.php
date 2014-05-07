<?php
include 'Assignment.php';
$Assignment=Assignment::getAssignment($_POST["Assignment_id"]);
$Assignment->addNotes($_POST["notes"]);
print json_encode($Assignment);
?>