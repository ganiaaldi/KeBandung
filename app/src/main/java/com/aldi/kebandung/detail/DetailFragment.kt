package com.aldi.kebandung.detail


import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
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
    lateinit var jamDestinasi : String
    lateinit var alamatDestinasi : String
    lateinit var alamatLengkapDestinasi : String
    lateinit var detailDestinasi : String
    lateinit var kategoriDestinasi : String
    lateinit var jamBukaDestinasi : String
    lateinit var jamTutupDestinasi : String
    lateinit var photoDestinasi : String
    var hargaDestinasi : Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    @SuppressLint("WrongConstant")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as ChangeToolbarTitle).showToolbar(false)
        setupBackButton()
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

        //detail_destinasi.setImageResource(photoDestinasi)
        var url = Endpoint.GAMBAR+photoDestinasi
        Glide.with(view.context)
            .load(url)
            .into(detail_destinasi)
        tvNamaDestinasi.text = namaDestinasi
        tvLokasi.text = alamatDestinasi
        tvLokasiDetail.text = alamatLengkapDestinasi
        tv_item_description.text = detailDestinasi
        tvJamm.text = jamBukaDestinasi
        tvHarga.text = hargaDestinasi.toString()

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


}
