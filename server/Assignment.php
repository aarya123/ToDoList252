<?php
class Assignment{
	function __construct($Assignment_id,$Course,$DueDate,$Categories){
		include 'db.php';
		if(is_null($Assignment_id)){
			$this->DueDate=$DueDate;
			$this->Categories=$Categories;
			$stmt = $db->prepare("INSERT INTO Assignment (Course_id,due_date,categories) VALUES (?,?,?)");
			if($stmt->execute(array($Course->id,$DueDate,$Categories))) {
				$this->id= $db->lastInsertId();
			}
			else {
				$this->id= -1;
			}
		}
		else{
			$this->$id=$Assignment_id;
			$stmt = $db->prepare("SELECT due_date,categories FROM Assignment where id=?");
			if($stmt->execute(array($Assignment_id))) {
				$data=$stmt->fetchAll();
				foreach($data as $row){
					$this->$DueDate=$row['due_date'];
					$this->$Categories=$row['categories'];
				}
			}
		}
	}
	function addNotes($Notes){
		include 'db.php';
		$stmt = $db->prepare("UPDATE Assignment SET notes=? WHERE id=?;");
		if(!($stmt->execute(array($Notes,$this->$id)))) {
			print "failed :(";
		}
	}

	static function getAssignment($Assignment_id){
		return new Course($Assignment_id,NULL,NULL,NULL);
	}

	public static function getAssignments($Course_id){
		include 'db.php';
		$stmt = $db->prepare("SELECT id FROM Assignment where Course_id=?");
		$Assignments=array();
		if($stmt->execute(array($Course_id))) {
			$data=$stmt->fetchAll();
			foreach ($data as $row) {
				array_push($Assignments,Course::getCourse($row['id']));
			}
		}
		return $Assignments;
	}
}
?>