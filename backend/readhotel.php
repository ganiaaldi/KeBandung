<?php
require_once('connection.php');

$query = "SELECT * FROM hotel";
$res = mysqli_query($CON, $query);

$result = array();

if ($res != false) {
   while ($row = mysqli_fetch_array($res)) {
    array_push($result, array(
            'no_hotel' => $row['no_hotel'],
            'nama_hotel' => $row['nama_hotel'],
            'nama_daerah' => $row['nama_daerah'],
            'alamat_lengkap' => $row['alamat_lengkap'],
            'detail' => $row['detail'],
            'harga' => $row['harga'],
            'jumlah_kamar' => $row['jumlah_kamar'],
            'fasilitas' => $row['fasilitas'],
            'gambar_hotel' => $row['gambar_hotel']
        )
    );
}

echo json_encode(array("result" => $result));
}
mysqli_close($CON);


?>
