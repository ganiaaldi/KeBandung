package com.aldi.kebandung.detail


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController

import com.aldi.kebandung.R
import kotlinx.android.synthetic.main.fragment_detail.*

class DetailFragment : Fragment() {

    lateinit var namaDestinasi : String
    lateinit var kecamatanDestinasi : String
    lateinit var jamDestinasi : String
    lateinit var alamatDestinasi : String
    lateinit var alamatLengkapDestinasi : String
    lateinit var detailDestinasi : String
    var photoDestinasi : Int = 0
    var hargaDestinasi : Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupBackButton()
        namaDestinasi= DetailFragmentArgs.fromBundle(arguments!!).nameDestination!!
        alamatDestinasi = DetailFragmentArgs.fromBundle(arguments!!).alamatDestination!!
        alamatLengkapDestinasi = DetailFragmentArgs.fromBundle(arguments!!).alamatLengkapDestination!!
       // kecamatanDestinasi = DetailFragmentArgs.fromBundle(arguments!!).kecamatanDestination!!
        detailDestinasi  = DetailFragmentArgs.fromBundle(arguments!!).detailDestination!!
        jamDestinasi = DetailFragmentArgs.fromBundle(arguments!!).jamDestination!!
        photoDestinasi = DetailFragmentArgs.fromBundle(arguments!!).photoDestination!!
        hargaDestinasi = DetailFragmentArgs.fromBundle(arguments!!).hargaDestination!!

        detail_destinasi.setImageResource(photoDestinasi)
        tvNamaDestinasi.text = namaDestinasi
        tvLokasi.text = alamatDestinasi
        tvLokasiDetail.text = alamatLengkapDestinasi
        tv_item_description.text = detailDestinasi
        tvJamm.text = jamDestinasi
        tvHarga.text = hargaDestinasi.toString()
    }

    private fun setupBackButton() {
        buttonBackk.setOnClickListener {
            findNavController().navigateUp()
        }
    }


}
