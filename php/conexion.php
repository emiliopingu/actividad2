<?php

$servidor = "localhost";
$user = "emiliox3";
$pass = "cartama08";
$bd ="tareas";
//PHP 5.5
$conexion = mysqli_connect($servidor,$user,$pass,$bd);
 try{
  
  $DBcon = new PDO("mysql:host=$servidor;dbname=$bd",$user,$pass);
  $DBcon->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
  
 }catch(PDOException $ex){
  
  die($ex->getMessage());
 }

?>