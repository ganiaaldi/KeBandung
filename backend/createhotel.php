<?php
require_once('connection.php');
$nama_hotel = $_POST['nama_hotel'];
$no_daerah = $_POST['no_daerah'];
$alamat_lengkap = $_POST['alamat_lengkap'];
$detail_hotel = $_POST['detail_hotel'];
$harga = $_POST['harga'];
if($harga <= 200000){
    $no_range = "1";
}
else if ($harga >= 200000 && $harga <= 300000  ){
    $no_range = "2";
}
else if ($harga >= 300000 && $harga <= 400000  ){
    $no_range = "3";
}
else if ($harga >= 400000 && $harga <= 500000  ){
    $no_range = "4";
}
else{
    $no_range = "5";
}

$jumlah_kamar = $_POST['jumlah_kamar'];
$fasilitas = $_POST['harga'];
 $gambar_hotel   = addslashes(file_get_contents($_FILES['gambar_hotel']['tmp_name']));

if(!$nama_hotel || !$no_daerah || !$alamat_lengkap || !$detail_hotel || !$no_daerah
 || !$harga || !$no_range || !$jumlah_kamar || !$fasilitas)
{
  echo json_encode(array('message'=>'form harus terisi semua!'));
}else{	
$query = mysqli_query($CON, "INSERT INTO hotel VALUES ('$no_hotel','$nama_hotel','$no_daerah','$alamat_lengkap',
'$detail_hotel','$harga','$no_range','$jumlah_kamar','$fasilitas','$gambar_hotel')");
if($query){
    echo json_encode(array('message'=>'Data hotel berhasil ditambahkan!'));
  }else{
    echo json_encode(array('message'=>'Data hotel gagal ditambahkan!'));
  }

}
?> 