<?php
require_once('connection.php');
$nama_wisata = $_POST['nama_wisata'];
$nama_daerah = $_POST['nama_daerah'];
$alamat_lengkap = $_POST['alamat_lengkap'];
$detail = $_POST['detail'];
$nama_kategori_wisata = $_POST['nama_kategori_wisata'];
$jam_buka = $_POST['jam_buka'];
$jam_tutup = $_POST['jam_tutup'];
$harga = $_POST['harga'];
$gambar_wisata = $_FILES['gambar']['name'];
$gambar = $_FILES['gambar']['name'];
 $tmp   = $_FILES['gambar']['tmp_name'];
 $path = "images/".$gambar;
 if($gambar_wisata == null){
  $gambar_wisata = "icon2.png";
 }
 if(move_uploaded_file($tmp, $path)){

if(!$nama_wisata || !$nama_daerah || !$alamat_lengkap || !$detail
 || !$nama_kategori_wisata || !$jam_buka || !$jam_tutup || !$harga)
{
  echo json_encode(array('message'=>'form harus terisi semua!'));
}else{	
$query = mysqli_query($CON, "INSERT INTO wisata VALUES
('$no_wisata','$nama_wisata', '$nama_daerah', '$alamat_lengkap', '$detail','$nama_kategori_wisata', '$jam_buka', 
'$jam_tutup', '$harga', '$gambar_wisata')");
if($query){
    echo json_encode(array('message'=>'Data wisata berhasil ditambahkan!'));
  }else{
    echo json_encode(array('message'=>'Data wisata gagal ditambahkan!'));
    //echo ("$nama_wisata,$nama_daerah, $alamat_lengkap, $detail, $nama_kategori_wisata, 
    //$jam_buka, $jam_tutup, $harga, $gambar_wisata");
    echo("Error description: " .mysqli_error($CON));
  }

}
 } else {
   
if(!$nama_wisata || !$nama_daerah || !$alamat_lengkap || !$detail
|| !$nama_kategori_wisata || !$jam_buka || !$jam_tutup || !$harga)
{
 echo json_encode(array('message'=>'form harus terisi semua!'));
}else{	
$query = mysqli_query($CON, "INSERT INTO wisata VALUES
('$no_wisata','$nama_wisata', '$nama_daerah', '$alamat_lengkap', '$detail','$nama_kategori_wisata', '$jam_buka', 
'$jam_tutup', '$harga', '$gambar_wisata')");
if($query){
   echo json_encode(array('message'=>'Data wisata berhasil ditambahkan!'));
 }else{
   echo json_encode(array('message'=>'Data wisata gagal ditambahkan!'));
   //echo ("$nama_wisata,$nama_daerah, $alamat_lengkap, $detail, $nama_kategori_wisata, 
   //$jam_buka, $jam_tutup, $harga, $gambar_wisata");
   echo("Error description: " .mysqli_error($CON));
 }
 }
}
?> 