<?php
include 'Course.php';
$Course=Course::getCourse($_GET["Course_id"]);
$Course->addAssignment($_GET["due_date"],$_GET["categories"]);
print json_encode($Course);
?>