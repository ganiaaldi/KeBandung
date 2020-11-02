<?php
require_once('connection.php');
$nama_restaurant = $_POST['nama_restaurant'];
$no_daerah = $_POST['no_daerah'];
$alamat_lengkap = $_POST['alamat_lengkap'];
$detail_restaurant = $_POST['detail_restaurant'];
$no_kategori_kuliner = $_POST['no_kategori_kuliner'];
$jam_buka = $_POST['jam_buka'];
$jam_tutup = $_POST['jam_tutup'];
 $gambar_restaurant   = addslashes(file_get_contents($_FILES['gambar_restaurant']['tmp_name']));

if(!$nama_restaurant || !$no_daerah || !$alamat_lengkap || !$detail_restaurant|| !$no_kategori_kuliner
 || !$jam_buka || !$jam_tutup)
{
  echo json_encode(array('message'=>'form harus terisi semua!'));
}else{	
$query = mysqli_query($CON, "INSERT INTO restaurant VALUES ('$no_hotel','$nama_restaurant','$no_daerah','$alamat_lengkap',
'$detail_restaurant','$no_kategori_kuliner','$jam_buka','$jam_tutup','$gambar_restaurant')");
if($query){
    echo json_encode(array('message'=>'Data restaurant berhasil ditambahkan!'));
  }else{
    echo json_encode(array('message'=>'Data restaurant gagal ditambahkan!'));
  }

}
?> 