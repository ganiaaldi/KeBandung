<?php
require_once('connection.php');

$query = "SELECT * FROM restaurant";
$res = mysqli_query($CON, $query);

$result = array();

if ($res != false) {
   while ($row = mysqli_fetch_array($res)) {
    array_push($result, array(
            'no_restaurant' => $row['no_restaurant'],
            'nama_restaurant' => $row['nama_restaurant'],
            'no_daerah' => $row['no_daerah'],
            'alamat_lengkap' => $row['alamat_lengkap'],
            'detail_restaurant' => $row['detail_restaurant'],
            'no_kategori_kuliner' => $row['no_kategori_kuliner'],
            'jam_buka' => $row['jam_buka'],
            'jam_tutup' => $row['jam_tutup'],
            'gambar_restaurant' => base64_encode($row['gambar_restaurant'])
        )
    );
}

echo json_encode(array("result" => $result));
}
mysqli_close($CON);


?>
