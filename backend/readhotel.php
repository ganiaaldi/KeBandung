<?php
require_once('connection.php');

$query = "SELECT * FROM hotel JOIN daerah
ON hotel.no_daerah=daerah.no_daerah
JOIN range_harga
ON hotel.no_range=range_harga.no_range";
$res = mysqli_query($CON, $query);

$result = array();

if ($res != false) {
   while ($row = mysqli_fetch_array($res)) {
    array_push($result, array(
            'no_hotel' => $row['no_hotel'],
            'nama_hotel' => $row['nama_hotel'],
            'nama_daerah' => $row['nama_daerah'],
            'alamat_lengkap' => $row['alamat_lengkap'],
            'detail_hotel' => $row['detail_hotel'],
            'harga' => $row['harga'],
            'range_harga' => $row['range_harga'],
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
