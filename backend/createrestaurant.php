<?php
require_once('connection.php');
$nama_restaurant = $_POST['nama_restaurant'];
$nama_daerah = $_POST['nama_daerah'];
$alamat_lengkap = $_POST['alamat_lengkap'];
$detail = $_POST['detail'];
$nama_kategori_kuliner = $_POST['nama_kategori_kuliner'];
$jam_buka = $_POST['jam_buka'];
$jam_tutup = $_POST['jam_tutup'];
$gambar_restaurant = $_FILES['gambar']['name'];
$gambar = $_FILES['gambar']['name'];
 $tmp   = $_FILES['gambar']['tmp_name'];
 $path = "images/".$gambar;
if($gambar_restaurant == null){
  $gambar_restaurant = "icon2.png";
 }
 if(move_uploaded_file($tmp, $path)){
if(!$nama_restaurant || !$nama_daerah || !$alamat_lengkap || !$detail|| !$nama_kategori_kuliner
 || !$jam_buka || !$jam_tutup)
{
  echo json_encode(array('message'=>'form harus terisi semua!'));
}else{	
$query = mysqli_query($CON, "INSERT INTO restaurant(nama_restaurant,nama_daerah,alamat_lengkap,detail,nama_kategori_kuliner,jam_buka,jam_tutup,gambar_restaurant) VALUES ('$nama_restaurant','$nama_daerah','$alamat_lengkap','$detail','$nama_kategori_kuliner','$jam_buka','$jam_tutup','$gambar_restaurant')");
if($query){
    echo json_encode(array('message'=>'Data tempat kuliner berhasil ditambahkan!'));
  }else{
    echo json_encode(array('message'=>'Data tempat kuliner gagal ditambahkan!'));
  }

}
 }

 else{
  if(!$nama_restaurant || !$nama_daerah || !$alamat_lengkap || !$detail|| !$nama_kategori_kuliner
  || !$jam_buka || !$jam_tutup)
 {
   echo json_encode(array('message'=>'form harus terisi semua!'));
 }else{	
 $query = mysqli_query($CON, "INSERT INTO restaurant(nama_restaurant,nama_daerah,alamat_lengkap,detail,nama_kategori_kuliner,jam_buka,jam_tutup,gambar_restaurant) VALUES ('$nama_restaurant','$nama_daerah','$alamat_lengkap',
 '$detail','$nama_kategori_kuliner','$jam_buka','$jam_tutup','$gambar_restaurant')");
 if($query){
     echo json_encode(array('message'=>'Data tempat kuliner berhasil ditambahkan!'));
   }else{
     echo json_encode(array('message'=>'Data tempat kuliner gagal ditambahkan!'));
   }
 
 }
 }
?> 