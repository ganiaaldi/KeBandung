package com.aldi.kebandung.detail


import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.aldi.kebandung.Endpoint

import com.aldi.kebandung.R
import com.aldi.kebandung.adapter.CommentAdapter
import com.aldi.kebandung.data.commentData
import com.aldi.kebandung.model.Comment
import com.aldi.kebandung.view.ChangeToolbarTitle
import com.bumptech.glide.Glide
import com.fasterxml.jackson.databind.util.ClassUtil.getPackageName
import kotlinx.android.synthetic.main.fragment_detail.*

class DetailFragment : Fragment() {

    lateinit var namaDestinasi : String
    lateinit var alamatDestinasi : String
    lateinit var alamatLengkapDestinasi : String
    lateinit var detailDestinasi : String
    lateinit var kategoriDestinasi : String
    lateinit var jamBukaDestinasi : String
    lateinit var jamTutupDestinasi : String
    lateinit var photoDestinasi : String
    var hargaDestinasi : Int = 0
    lateinit var namaHotel : String
    lateinit var daerahHotel : String
    lateinit var alamatHotel : String
    lateinit var detailHotel : String
    var hargaHotel : Int = 0
    lateinit var rangeHotel : String
    var jumlahKamarHotel : Int = 0
    lateinit var fasilitasHotel : String
    lateinit var gambarHotel : String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    @SuppressLint("WrongConstant", "SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as ChangeToolbarTitle).showToolbar(false)
        setupBackButton()
        vacationArgs()
        hotelArgs()
        //detail_destinasi.setImageResource(photoDestinasi)
        if(namaDestinasi != "0") {
            var url = Endpoint.GAMBAR + photoDestinasi
            Glide.with(view.context)
                .load(url)
                .into(detail_destinasi)
            tvNamaDestinasi.text = namaDestinasi
            tvLokasi.text = alamatDestinasi
            tvLokasiDetail.text = alamatLengkapDestinasi
            tv_item_description.text = detailDestinasi
            tvJamm.text = (jamBukaDestinasi + "-" + jamTutupDestinasi)
            tvHarga.text = hargaDestinasi.toString()
        }

        else if(namaHotel != "0") {
            var url = Endpoint.GAMBAR + gambarHotel
            Glide.with(view.context)
                .load(url)
                .into(detail_destinasi)
            tvNamaDestinasi.text = namaHotel
            tvLokasi.text = daerahHotel
            tvLokasiDetail.text = alamatHotel
            tv_item_description.text = detailHotel
            tvJam.text = "Jumlah Kamar : "
            tvJamm.text = jumlahKamarHotel.toString()
            tvHarga.text = "$hargaHotel/hari"
            tvFasilitas.visibility = VISIBLE
            tv_item_fasilitas.visibility = VISIBLE
            tv_item_fasilitas.text = fasilitasHotel

        }

        rvComment.apply{
            layoutManager = LinearLayoutManager(activity, LinearLayout.VERTICAL, false)
            adapter = CommentAdapter(commentData.commentData)
            (adapter as CommentAdapter).setOnItemClickCallback(object : CommentAdapter.OnItemClickCallback {
                override fun onItemClicked(data: Comment) {

                }
            })
        }
    }


    private fun setupBackButton() {
        buttonBackk.setOnClickListener {
            findNavController().navigateUp()
        }
    }

    fun vacationArgs(){
        namaDestinasi= DetailFragmentArgs.fromBundle(arguments!!).nameDestination!!
        alamatDestinasi = DetailFragmentArgs.fromBundle(arguments!!).daerahDestination!!
        alamatLengkapDestinasi = DetailFragmentArgs.fromBundle(arguments!!).alamatDestination!!
        // kecamatanDestinasi = DetailFragmentArgs.fromBundle(arguments!!).kecamatanDestination!!
        detailDestinasi  = DetailFragmentArgs.fromBundle(arguments!!).detailDestination!!
        kategoriDestinasi = DetailFragmentArgs.fromBundle(arguments!!).kategoriDestination!!
        jamBukaDestinasi  = DetailFragmentArgs.fromBundle(arguments!!).jamBukaDestination!!
        jamTutupDestinasi = DetailFragmentArgs.fromBundle(arguments!!).jamTutupDestination!!
        photoDestinasi = DetailFragmentArgs.fromBundle(arguments!!).gambarDestination!!
        hargaDestinasi = DetailFragmentArgs.fromBundle(arguments!!).hargaDestination!!
    }

    fun hotelArgs(){
        namaHotel = DetailFragmentArgs.fromBundle(arguments!!).nameHotel!!
        daerahHotel = DetailFragmentArgs.fromBundle(arguments!!).daerahHotel!!
        alamatHotel = DetailFragmentArgs.fromBundle(arguments!!).alamatHotel!!
        detailHotel = DetailFragmentArgs.fromBundle(arguments!!).detailHotel!!
        hargaHotel = DetailFragmentArgs.fromBundle(arguments!!).hargaHotel!!
        rangeHotel = DetailFragmentArgs.fromBundle(arguments!!).rangeHotel!!
        jumlahKamarHotel = DetailFragmentArgs.fromBundle(arguments!!).jumlahKamarHotel!!
        fasilitasHotel = DetailFragmentArgs.fromBundle(arguments!!).fasilitasHotel!!
        gambarHotel = DetailFragmentArgs.fromBundle(arguments!!).gambarHotel!!
    }
}
