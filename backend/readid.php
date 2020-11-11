<?php
require_once('connection.php');

$username = $_POST['username'];
$email = $_POST['email'];
$kata_sandi = $_POST['kata_sandi'];
$query = "SELECT * FROM user WHERE username = '$username' 
AND kata_sandi ='$kata_sandi' OR
email = '$email' AND kata_sandi ='$kata_sandi' ";
$res = mysqli_query($CON, $query);

$result = array();

if ($res != false) {
   while ($row = mysqli_fetch_array($res)) {
    array_push($result, array(
            'id_user' => $row['id_user'],
            'email' => $row['email'],
            'username' => $row['username'],
            'nama_lengkap' => $row['nama_lengkap'],
            'kata_sandi' => $row['kata_sandi'],
        )
    );
}

echo json_encode(array("result" => $result));
}
mysqli_close($CON);


?>
