<?php
include 'Assignment.php';
$Assignment=Assignment::getAssignment($_GET["Assignment_id"]);
$Assignment->addNotes($_GET["notes"]);
print json_encode($Assignment);
?>