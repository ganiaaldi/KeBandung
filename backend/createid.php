<?php
require_once('connection.php');
$id_user = $_POST['id_user'];
$email = $_POST['email'];
$username = $_POST['username'];
$nama_lengkap = $_POST['nama_lengkap'];
$kata_sandi = $_POST['kata_sandi'];


if(!$email || !$username || !$nama_lengkap || !$kata_sandi)
{
  echo json_encode(array('message'=>'form harus terisi semua!'));
}else{	
$query = mysqli_query($CON, "INSERT INTO user VALUES ('$id_user','$email','$username','$nama_lengkap',
'$kata_sandi')");
if($query){
    echo json_encode(array('message'=>'Data user berhasil ditambahkan!'));
  }else{
    echo json_encode(array('message'=>'Data user gagal ditambahkan!'));
  }

}
?> 