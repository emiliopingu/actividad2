<?php

require 'conexion.php';



if(isset($_POST['TaskName']) && !empty($_POST['TaskName']) && isset($_POST['TaskPlace']) && !empty($_POST['TaskPlace']) && isset($_POST['TaskUser']) && !empty($_POST['TaskUser']) && isset($_POST['DateOfExpiry']) && !empty($_POST['DateOfExpiry']) && isset($_POST['Description']) && !empty($_POST['Description'])) {
	
 $nombre= $_POST['TaskName'];
 $lugar = $_POST['TaskPlace'];
 $usuario = $_POST['TaskUser'];
 $fecha = $_POST['DateOfExpiry'];
 $descripcion =$_POST['Description'];
 
 $sql = "INSERT INTO task(TaskName,TaskPlace,TaskUser,DateOfExpiry,Description) VALUES('$nombre','$lugar',' $usuario ',' $fecha' , $descripcion )";
 

 if(mysqli_query($conexion,$sql)){

 echo 'todo okey';
 }else{
 
 echo 'vuelve a intentarlo';
 }
 

 mysqli_close($conexion);
}
?>