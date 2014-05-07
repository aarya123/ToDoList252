<?php
include 'User.php';
$User= new User(NULL);
for($i=0;true;$i++) {
	if(isset($_POST["Course".$i])) {
		if(isset($_POST["Color".$i])) {
			$User->addCourse($_POST["Course".$i],$_POST["Color".$i]);
		}
		else {
			$User->addCourse($_POST["Course".$i],NULL);
		}
	}
	else {
		break;
	}
}
print json_encode($User);
?>