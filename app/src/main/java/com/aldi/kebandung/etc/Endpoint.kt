package com.aldi.kebandung.etc

class Endpoint {
    companion object {

        private val SERVER = "http://192.168.43.11/kebandung/"
        val CREATEWISATA = SERVER +"createwisata.php"
        val READWISATA = SERVER +"readwisata.php"
        val GAMBAR = SERVER +"/images/"
        val READHOTEL = SERVER +"readhotel.php"
        val READRESTAURANT = SERVER +"readrestaurant.php"
        val REGISTER = SERVER +"createid.php"
        val READID = SERVER +"readid.php"
        val READDAERAH = SERVER +"readdaerah.php"
        val CREATERESTAURANT = SERVER +"createrestaurant.php"
        val CREATEHOTEL = SERVER +"createhotel.php"
    }
}