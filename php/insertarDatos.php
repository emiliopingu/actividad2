<?php
require("conexion.php");

$nombre = $_POST["TaskName"];
$lugar = $_POST["TaskPlace"];
$usuario = $_POST["TaskUser"];
$fecha = $_POST["DateOfExpiry"];
$descripcion = $_POST["Description"];


mysqli_select_db($conexion,"tareas") or die("No se encuentra la base de datos");
mysqli_set_charset($conexion, "utf8");

$query2 ="INSERT INTO task(TaskName,TaskPlace,TaskUser,DateOfExpiry,Description) values ($nombre,$lugar ,$usuario',$fecha,$descripcion)";

$query ="INSERT INTO task(TaskName,TaskPlace,TaskUser,DateOfExpiry,Description) values ('prueba1','prueba1','prueba1','prueba1','prueba1')";
mysqli_query($conexion,$query) or die (mysqli_error());
mysqli_query($conexion,$query2) or die (mysqli_error());
mysqli_close($conexion);


$sql = "select * from";
$res = mysql_query($sql,$conexion);
$row = mysql_fetch_Assoc($res);
$json = array(
    'nombreTarea' => $row['TaskName'],
    'lugar' => $row['TaskPlace'],
    'usuario' => $row['TaskUser'],
    'fecha' => $row['DateOfExpiry'],
	'descripcion' => $row['Description']
);
?>
