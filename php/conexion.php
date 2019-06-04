<?php
$servidor = "localhost";
$user = "emiliox3";
$pass = "cartama08";
$bd ="tareas";
//PHP 5.5
$conexion = mysqli_connect($servidor,$user,$pass,$bd);
if(mysqli_connect_errno()){
	echo "Fallo al conectar con mySQL: " . mysqli_connect_error();
}else{
	echo "conecto con la base de datos : $bd";
}

?>