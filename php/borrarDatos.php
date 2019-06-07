<?php

$nombre = $_POST["nombre"];
require("conexion.php");


$conexion = mysqli_connect($servidor, $user, $pass);

if (mysqli_connect_errno()) {
    echo "Fallo al conectar con la base de datos";
    exit();
}

mysqli_select_db($conexion, $bd) or die("No se encuentra la base de datos");
mysqli_set_charset($conexion, "utf8");


$registro = "delete from tarea where nombre='$nombre';";

 
$resultados = mysqli_query($conexion, $registro);

if($resultados == false){

    echo"La consulta o los datos estan mal!";
    echo "<br><br>" . $registro;
}else{
 echo "se realizo con existo";
}

mysqli_close($conexion);
?>