<?php
$username = 'user';
$password = 'user';
$conn_str='mysql:host=54.213.80.211;dbname=ToDoList;charset=utf8';
$opt = array(
	PDO::ATTR_ERRMODE            => PDO::ERRMODE_EXCEPTION,
	PDO::ATTR_DEFAULT_FETCH_MODE => PDO::FETCH_ASSOC
	);
$db = new PDO($conn_str, $username, $password, $opt);
?>