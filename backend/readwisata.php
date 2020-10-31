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
            'no_daerah' => $row['no_daerah'],
            'alamat_lengkap' => $row['alamat_lengkap'],
            'detail_wisata' => $row['detail_wisata'],
            'no_daerah' => $row['no_daerah'],
            'no_kategori_wisata' => $row['no_kategori_wisata'],
            'jam_buka' => $row['jam_buka'],
            'jam_tutup' => $row['jam_tutup'],
            'harga' => $row['harga'],
            'gambar_wisata' => base64_encode($row['gambar_wisata'])
        )
    );
}

echo json_encode(array("result" => $result));
}
mysqli_close($CON);


?>
