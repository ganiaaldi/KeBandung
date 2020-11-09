package com.aldi.kebandung.model

data class Hotel(
    var namaHotel: String = "",
    var namaDaerah: String = "",
    var alamatLengkap: String = "",
    var detailHotel: String = "",
    var harga: Int = 0,
    var rangeHarga: String = "",
    var jumlahKamar: Int = 0,
    var fasilitas: String = "",
    var gambarHotel: String = ""
)