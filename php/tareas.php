<?php
require_once 'conexion.php';
 
 $query = "SELECT * FROM task";
 
 $stmt = $DBcon->prepare($query);
 $stmt->execute();
 
 $ArrayDeTareas = array();
 
 while($row=$stmt->fetch(PDO::FETCH_ASSOC)){
  
  $ArrayDeTareas[] = $row;
 }
 
 echo json_encode($ArrayDeTareas);
 ?>