<?php
require("conexion.php");

//$nombre = $_POST["TaskName"];
//$lugar = $_POST["TaskPlace"];
//$usuario = $_POST["TaskUser"];
//$fecha = $_POST["DateOfExpiry"];
//$descripcion = $_POST["Description"];


mysqli_select_db($conexion,"tareas") or die("No se encuentra la base de datos");
mysqli_set_charset($conexion, "utf8");


$query ="INSERT INTO task(TaskName,TaskPlace,TaskUser,DateOfExpiry,Description) values ('pepe','pepe','pepe','pepe','pepe')";
mysqli_query($conexion,$query) or die (mysqli_error());
mysqli_close($conexion);
