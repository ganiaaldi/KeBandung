<?php
require_once('connection.php');

$query = "SELECT * FROM wisata";
$res = mysqli_query($CON, $query);

$result = array();

if ($res != false) {
   while ($row = mysqli_fetch_array($res)) {
    array_push($result, array(
            'no_wisata' => $row['no_wisata'],
            'nama_wisata' => $row['nama_wisata'],
            'nama_daerah' => $row['nama_daerah'],
            'alamat_lengkap' => $row['alamat_lengkap'],
            'detail' => $row['detail'],
            'nama_kategori_wisata' => $row['nama_kategori_wisata'],
            'jam_buka' => $row['jam_buka'],
            'jam_tutup' => $row['jam_tutup'],
            'harga' => $row['harga'],
            'gambar_wisata' => $row['gambar_wisata']
        )
    );
}

echo json_encode(array("result" => $result));
}
mysqli_close($CON);


?>
