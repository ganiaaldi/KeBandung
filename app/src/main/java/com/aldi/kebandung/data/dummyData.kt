package com.aldi.kebandung.data

import com.aldi.kebandung.R
import com.aldi.kebandung.model.Dummy

object dummyData : ArrayList<Dummy>() {
     val dummyData = arrayListOf(
        Dummy("Dago Dreampark","Bandung Barat",
            "Jl. Dago Giri Km. 2.2 Mekarwangi","Dago Dreampark merupakan wisata kekinian di Kota Bandung dengan luas 13 hektar yang mengusung konsep Jawa - Sunda & Bali dengan dilengkapi berbagai fasilitas & wahana yang menarik."
            , R.drawable.dagodreampark),
        Dummy("Tebing Keraton","Bandung Barat",
            "Ciburial, Cimenyan","Tebing Keraton Bandung tempat wisata cocok bagi pecinta alam yang dikelilingi perbukitan membuatnya jadi satu dari sekian kota yang spesial di Kota Bandung"
            , R.drawable.tebingkeraton),
        Dummy("Tahura Ir. H. Djuanda","Bandung Kota",
            "Cimenyan","Taman Hutan Raya Ir. H. Djuanda (Tahura Djuanda) merupakan kawasan konservasi yang terpadu antara alam sekunder dengan hutan tanaman dengan jenis Pinus (Pinus merkusil) yang terletak di Sub-Daerah Aliran Sungai Cikapundung dan DAS Citarum yang membentang mulai dari Curug Dago, Dago Pakar sampai Curug Maribaya yang merupakan bagian dari kelompok hutan Gunung Pulosari."
            , R.drawable.tahura)
    )

    val dummyDataRestaurant = arrayListOf(
        Dummy("Sudirman Street Day and Night Market","Bandung Barat",
            "Jl. Jend. Sudirman No.107, Karanganyar"," "
            , R.drawable.rtsudirman),
        Dummy("Wisata Kuliner Cikapundung Barat","Bandung Barat",
            "Jl. Cikapundung Barat, Braga"," "
            , R.drawable.rtcikapundung),
        Dummy("Paskal Food Market","Bandung Kota",
            "Paskal Hyper Square",""
            , R.drawable.rtpaskal)
    )

    val dummyDataHotel = arrayListOf(
        Dummy("THE 1O1 Bandung Dago","Bandung Barat",
            "Jl. Ir H. Juanda No. 3 , Dago","Harga/kamar/malam mulai dari\n" +
                    "Rp 450.000"
            , R.drawable.hotel101),
        Dummy("Atlantic City Hotel","Bandung Barat",
            "Jl. Pasirkaliki No.126, Pasirkaliki","Harga/kamar/malam mulai dari\n" +
                    "Rp 346.000"
            , R.drawable.hotelatlantic),
        Dummy("Hotel Vio Pasteur","Bandung Kota",
            "Dr. Djunjunan (Pasteur) 154 Bandung","Harga/kamar/malam mulai dari\n" +
                    "Rp 283.500"
            , R.drawable.hotelvio)
    )
}
