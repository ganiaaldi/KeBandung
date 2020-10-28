package com.aldi.kebandung.data

import com.aldi.kebandung.R
import com.aldi.kebandung.model.Dummy

object dummyData : ArrayList<Dummy>() {
     val dummyData = arrayListOf(
        Dummy("Dago Dreampark","Bandung Barat",
            "Dago","Dago Dreampark merupakan wisata kekinian di Kota Bandung dengan luas 13 hektar yang mengusung konsep Jawa - Sunda & Bali dengan dilengkapi berbagai fasilitas & wahana yang menarik."
            , R.drawable.dagodreampark, "Jl. Dago Giri Km. 2.2 Mekarwangi",
            "09.00 - 17.00", 40000),
        Dummy("Tebing Keraton","Bandung Barat",
            "Ciburial","Tebing Keraton Bandung tempat wisata cocok bagi " +
                    "pecinta alam yang dikelilingi perbukitan membuatnya jadi satu dari sekian kota " +
                    "yang spesial di Kota Bandung. Berada di ketinggian memungkinan pengunjung bisa melihat" +
                    " pemandangan matahari terbit dan tenggelam. Jika datang waktu pagi hari, semburat kuning sinar" +
                    " matahari, berpadu dengan hijaunya hutan dan kabut. Sedangkan saat sore hari, sinar keemasan" +
                    " mataharari bisa menjadi latar belakang untuk berfoto.\n" +
                    "\n" +
                    "Waktu terbaik untuk datang saat pagi hari yaitu 05.00-07.00 WIB. Namun, " +
                    "untuk mendapatkan foto yang lebih bagus dan meminimalisir noise, bisa datang saat " +
                    "hari biasa. Hal tersebut disebabkan akan lebih banyak pengunjung yang datang saat akhir pekan."
            , R.drawable.tebingkeraton, "Ciburial, Kec.Cimenyan, Kabupaten Bandung Barat, Jawa Barat 40198",
            "05.00 - 18.00",15000),
        Dummy("Tahura Ir. H. Djuanda","Bandung Kota",
            "Cimenyan","Taman Hutan Raya Ir. H. Djuanda (Tahura Djuanda) merupakan kawasan konservasi yang terpadu antara alam sekunder dengan hutan tanaman dengan jenis Pinus (Pinus merkusil) yang terletak di Sub-Daerah Aliran Sungai Cikapundung dan DAS Citarum yang membentang mulai dari Curug Dago, Dago Pakar sampai Curug Maribaya yang merupakan bagian dari kelompok hutan Gunung Pulosari."
            , R.drawable.tahura,"Kompleks Tahura, Jl. Ir. H. Juanda No.99, Ciburial, Kec. Cimenyan, Bandung, Jawa Barat 40198",
            "07.00 - 17.00", 15000)
    )

    val dummyDataRekomendasi = arrayListOf(
        Dummy("Tebing Keraton","Bandung Barat",
            "Ciburial","Tebing Keraton Bandung tempat wisata cocok bagi " +
                    "pecinta alam yang dikelilingi perbukitan membuatnya jadi satu dari sekian kota " +
                    "yang spesial di Kota Bandung. Berada di ketinggian memungkinan pengunjung bisa melihat" +
                    " pemandangan matahari terbit dan tenggelam. Jika datang waktu pagi hari, semburat kuning sinar" +
                    " matahari, berpadu dengan hijaunya hutan dan kabut. Sedangkan saat sore hari, sinar keemasan" +
                    " mataharari bisa menjadi latar belakang untuk berfoto.\n" +
                    "\n" +
                    "Waktu terbaik untuk datang saat pagi hari yaitu 05.00-07.00 WIB. Namun, " +
                    "untuk mendapatkan foto yang lebih bagus dan meminimalisir noise, bisa datang saat " +
                    "hari biasa. Hal tersebut disebabkan akan lebih banyak pengunjung yang datang saat akhir pekan."
            , R.drawable.tebingkeraton, "Ciburial, Kec.Cimenyan, Kabupaten Bandung Barat, Jawa Barat 40198",
            "05.00 - 18.00",15000),
        Dummy("Dago Dreampark","Bandung Barat",
            "Dago","Dago Dreampark merupakan wisata kekinian di Kota Bandung dengan luas 13 hektar yang mengusung konsep Jawa - Sunda & Bali dengan dilengkapi berbagai fasilitas & wahana yang menarik."
            , R.drawable.dagodreampark, "Jl. Dago Giri Km. 2.2 Mekarwangi",
            "09.00 - 17.00", 40000),
        Dummy("Tahura Ir. H. Djuanda","Bandung Kota",
            "Cimenyan","Taman Hutan Raya Ir. H. Djuanda (Tahura Djuanda) merupakan kawasan konservasi yang terpadu antara alam sekunder dengan hutan tanaman dengan jenis Pinus (Pinus merkusil) yang terletak di Sub-Daerah Aliran Sungai Cikapundung dan DAS Citarum yang membentang mulai dari Curug Dago, Dago Pakar sampai Curug Maribaya yang merupakan bagian dari kelompok hutan Gunung Pulosari."
            , R.drawable.tahura,"Kompleks Tahura, Jl. Ir. H. Juanda No.99, Ciburial, Kec. Cimenyan, Bandung, Jawa Barat 40198",
            "07.00 - 17.00", 15000)
    )

    val dummyDataRestaurant = arrayListOf(
        Dummy("Sudirman Street Day and Night Market","Bandung Barat",
            "Karang Anyar","Tempat khusus kuliner berkonsep foodcourt ini menjajakan berbagai " +
                    "macam kuliner mulai tradisional, oriental, western, dan berbagai menu lainnya yang hits dan " +
                    "happening.\n"
            , R.drawable.rtsudirman,"Jl. Jend. Sudirman No.107, Karanganyar",
            "10.00 - 22.00"),
        Dummy("Wisata Kuliner Cikapundung Barat","Bandung Barat",
            "Braga","Tempat kuliner kekinian telah hadir di cikapundung barat. Tempat ini" +
                    "merupakan buah kerja sama Pemkot Bandung dengan PT Tirta Investama atau Danone Aqua."
            , R.drawable.rtcikapundung, "Jl. Cikapundung Barat, Braga, Kec. Sumur Bandung, Kota Bandung, Jawa Barat 40111"
        ,"04.00 - 00.00"),
        Dummy("Paskal Food Market","Bandung Kota",
            "Pasir Kaliki","Bingung mau makan atau nongkrong ke mana di Bandung? Ke Paskal Food Market aja, menu makanannya lengkap & suasananya asik!"
            , R.drawable.rtpaskal,"Paskal Hyper Square, Jl. Pasir Kaliki No.25-27, Kb. Jeruk, Kec. Andir, Kota Bandung, Jawa Barat 40181",
        "10.00 - 23.30")
    )

    val dummyDataHotel = arrayListOf(
        Dummy("THE 1O1 Bandung Dago","Bandung Barat",
            "Dago","Harga/kamar/malam mulai dari\n" +
                    "Rp 450.000"
            , R.drawable.hotel101,"Jl. Ir H. Juanda No. 3 , Dago","24 Jam"),
        Dummy("Atlantic City Hotel","Bandung Barat",
            "Pasirkaliki","Harga/kamar/malam mulai dari\n" +
                    "Rp 346.000"
            , R.drawable.hotelatlantic,"Jl. Pasirkaliki No.126, Pasirkaliki","24 Jam"),
        Dummy("Hotel Vio Pasteur","Bandung Kota",
            "Pasteur","Harga/kamar/malam mulai dari\n" +
                    "Rp 283.500"
            , R.drawable.hotelvio,"Dr. Djunjunan (Pasteur) 154 Bandung","24 Jam")
    )
}
