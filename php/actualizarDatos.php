<?php
require("conexion.php");


$nombre = $_POST["nombre"];
$lugar = $_POST["lugar"];
$usuario = $_POST["usuario"];
$fecha = $_POST["fecha"];
$descripcion = $_POST["descripcion"];
require("conexion.php");

$conexion = mysqli_connect($servidor, $user, $pass);

if (mysqli_connect_errno()) {
    echo "Fallo al conectar con la base de datos";
    exit();
}

mysqli_select_db($conexion, $bd) or die("No se encuentra la base de datos");
mysqli_set_charset($conexion, "utf8");

  $sql = "UPDATE agenda SET lugar='$lugar', usuario='$usuario',".
      "fecha='$fecha', descripcion='$descripcion' WHERE nombre=$nombre";
   $result = mysql_query($sql);
?>