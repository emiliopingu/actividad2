<?php
require_once 'conexion.php';
 
 $query = "SELECT * FROM task";
 
 $stmt = $DBcon->prepare($query);
 $stmt->execute();
 
 $Tareas = array();
 
 while($row=$stmt->fetch(PDO::FETCH_ASSOC)){
  
  $Tareas[] = $row;
 }
 
 echo json_encode($Tareas);
 ?>