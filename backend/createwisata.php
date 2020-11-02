<?php
require_once('connection.php');
$no_wisata = $_POST['no_wisata'];
$nama_wisata = $_POST['nama_wisata'];
$no_daerah = $_POST['no_daerah'];
$alamat_lengkap = $_POST['alamat_lengkap'];
$detail_wisata = $_POST['detail_wisata'];
$no_kategori_wisata = $_POST['no_kategori_wisata'];
$jam_buka = $_POST['jam_buka'];
$jam_tutup = $_POST['jam_tutup'];
$harga = $_POST['harga'];
 $gambar_wisata   = addslashes(file_get_contents($_FILES['gambar_wisata']['tmp_name']));

if(!$nama_wisata || !$no_daerah || !$alamat_lengkap || !$detail_wisata || !$no_daerah
 || !$no_kategori_wisata || !$jam_buka || !$jam_tutup || !$harga)
{
  echo json_encode(array('message'=>'form harus terisi semua!'));
}else{	
$query = mysqli_query($CON, "INSERT INTO wisata VALUES ('$no_wisata','$nama_wisata','$no_daerah','$alamat_lengkap',
'$detail_wisata','$no_kategori_wisata','$jam_buka','$jam_tutup','$harga','$gambar_wisata')");
if($query){
    echo json_encode(array('message'=>'Data wisata berhasil ditambahkan!'));
  }else{
    echo json_encode(array('message'=>'Data wisata gagal ditambahkan!'));
  }

}
?> 