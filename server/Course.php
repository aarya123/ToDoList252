<?php
class Course{
	function __construct($Course_id,$Name, $Color, $User) {
		include 'db.php';
		if(is_null($Course_id)){
			if (is_null($Color)||empty($Color)) {
				$stmt = $db->prepare("INSERT INTO Course (name, User_id) VALUES (?,?)");
				$values=array($Name, $User->id);
				$Color="FFFFFF";
			}
			else {
				$stmt = $db->prepare("INSERT INTO Course (name, color, User_id) VALUES (?,?,?)");
				$values=array($Name, $Color, $User->id);
			}
			if($stmt->execute($values)) {
				$this->id=$db->lastInsertId();
			}
			else {
				$this->id=-1;
			}
			$this->Name=$Name;
			$this->Color=$Color;
			$this->Assignments=array();
		} else {
			$this->id=$Course_id;
			$stmt = $db->prepare("SELECT color, name FROM Course where id=?");
			if($stmt->execute(array($Course_id))) {
				$data=$stmt->fetchAll();
				foreach($data as $row){
					$this->Name=$row['name'];
					$this->Color=$row['color'];
				}
				include_once('Assignment.php');
				$this->Assignments=Assignment::getAssignments($Course_id);
			}
		}
	}

	function addAssignment($DueDate,$Categories)
	{
		include_once('Assignment.php');
		array_push($this->Assignments,new Assignment(NULL,$this,$DueDate,$Categories));
	}

	static function getCourse($Course_id){
		return new Course($Course_id,NULL,NULL,NULL);
	}

	public static function getCourses($User_id){
		include 'db.php';
		$stmt = $db->prepare("SELECT id FROM Course where User_id=?");
		$Courses=array();
		if($stmt->execute(array($User_id))) {
			$data=$stmt->fetchAll();
			foreach ($data as $row) {
				array_push($Courses,Course::getCourse($row['id']));
			}
		}
		return $Courses;
	}
}
?>