package com.aldi.kebandung.model

import java.sql.Blob

data class Wisata(
    var namaWisata: String = "",
    var namaDaerah: String = "",
    var alamatLengkap: String = "",
    var detailWisata: String = "",
    var namaKategoriWisata: String = "",
    var jamBuka: String = "",
    var jamTutup: String = "",
    var harga: Int = 0,
    var gambarWisata: Int = 0
    )