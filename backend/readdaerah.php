<?php
require_once('connection.php');

$query = "SELECT * FROM daerah ORDER BY  nama_daerah ASC";
$res = mysqli_query($CON, $query);

$result = array();

if ($res != false) {
   while ($row = mysqli_fetch_array($res)) {
    array_push($result, array(
            'no_daerah' => $row['no_daerah'],
            'nama_daerah' => $row['nama_daerah']
        )
    );
}

echo json_encode(array("result" => $result));
}
mysqli_close($CON);


?>
