<?php
include 'User.php';
$User= new User(NULL);
for($i=0;true;$i++) {
	if(isset($_GET["Course".$i])) {
		if(isset($_GET["Color".$i])) {
			$User->addCourse($_GET["Course".$i],$_GET["Color".$i]);
		}
		else {
			$User->addCourse($_GET["Course".$i],NULL);
		}
	}
	else {
		break;
	}
}
print json_encode($User);
?>