<?php
require_once('connection.php');
$nama_hotel = $_POST['nama_hotel'];
$nama_daerah = $_POST['nama_daerah'];
$alamat_lengkap = $_POST['alamat_lengkap'];
$detail= $_POST['detail'];
$harga = $_POST['harga'];
$jumlah_kamar = $_POST['jumlah_kamar'];
$fasilitas = $_POST['fasilitas'];
$gambar_hotel = $_FILES['gambar']['name'];
$gambar = $_FILES['gambar']['name'];
 $tmp   = $_FILES['gambar']['tmp_name'];
 $path = "images/".$gambar;
if($gambar_hotel == null){
  $gambar_hotel = "icon2.png";
 }
 if(move_uploaded_file($tmp, $path)){

if(!$nama_hotel  || !$alamat_lengkap || !$detail || !$nama_daerah
 || !$harga|| !$jumlah_kamar || !$fasilitas)
{
  echo json_encode(array('message'=>'form harus terisi semua!'));
}else{	
$query = mysqli_query($CON, "INSERT INTO hotel(nama_hotel,nama_daerah,alamat_lengkap,detail,harga,jumlah_kamar,fasilitas,gambar_hotel) VALUES ('$nama_hotel','$nama_daerah','$alamat_lengkap',
'$detail','$harga','$jumlah_kamar','$fasilitas','$gambar_hotel')");
if($query){
    echo json_encode(array('message'=>'Data hotel berhasil ditambahkan!'));
  }else{
    echo json_encode(array('message'=>'Data hotel gagal ditambahkan!'));
  }
}
 }
 else{
 if(!$nama_hotel  || !$alamat_lengkap || !$detail || !$nama_daerah
 || !$harga|| !$jumlah_kamar || !$fasilitas)
{
  echo json_encode(array('message'=>'form harus terisi semua!'));
}else{	
$query = mysqli_query($CON, "INSERT INTO hotel(nama_hotel,nama_daerah,alamat_lengkap,detail,harga,jumlah_kamar,fasilitas,gambar_hotel) VALUES ('$nama_hotel','$nama_daerah','$alamat_lengkap',
'$detail','$harga','$jumlah_kamar','$fasilitas','$gambar_hotel')");
if($query){
    echo json_encode(array('message'=>'Data hotel berhasil ditambahkan!'));
  }else{
    echo json_encode(array('message'=>'Data hotel gagal ditambahkan!'));
  }
}
}
?> 