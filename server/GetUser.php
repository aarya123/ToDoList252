<?php
include 'User.php';
print json_encode(new User($_GET['User_id']));
?>