<?php
class User {
	function __construct($User_id) {
		include_once('db.php');
		if(is_null($User_id)){
			$stmt = $db->prepare("INSERT INTO User VALUES ()");
			if($stmt->execute()) {
				$this->id = $db->lastInsertId();
			}
			else {
				$this->id= -1;
			}
			$this->Courses=array();
		}else {
			include_once('Course.php');
			$this->id=$User_id;
			$this->Courses=Course::getCourses($User_id);
		}
	}
	function addCourse($Name,$Color)
	{
		include_once('Course.php');
		array_push($this->Courses,new Course(NULL,$Name,$Color, $this));
	}

	public static function getUser($User_id){
		return new User($User_id);
	}
}
?>