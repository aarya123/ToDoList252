<?php
include 'Course.php';
$Course=Course::getCourse($_POST["Course_id"]);
$Course->addAssignment($_POST["due_date"],$_POST["categories"]);
print json_encode($Course);
?>