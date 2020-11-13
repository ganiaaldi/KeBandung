package com.aldi.kebandung.destination


import android.annotation.SuppressLint
import android.app.ProgressDialog
import android.content.res.ColorStateList
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.aldi.kebandung.etc.Endpoint

import com.aldi.kebandung.R
import com.aldi.kebandung.adapter.HotelAdapter
import com.aldi.kebandung.menu.DestinationFragmentDirections
import com.aldi.kebandung.model.Hotel
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONObjectRequestListener
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipDrawable
import kotlinx.android.synthetic.main.fragment_hotel.*
import org.json.JSONObject


class HotelFragment : Fragment() {

    private lateinit var chipCategory: ArrayList<Int>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_hotel, container, false)
    }

    @SuppressLint("WrongConstant")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
       // hotelRecyclerView.apply{
       //     layoutManager = LinearLayoutManager(activity, LinearLayout.VERTICAL, false)
        //    adapter = HotelAdapter(dummyData.dummyDataHotel)
        //    (adapter as HotelAdapter).setOnItemClickCallback(object : HotelAdapter.OnItemClickCallback {
        //        override fun onItemClicked(data: Hotel) {
       //             showSelectedVacation(data)
        //        }
       //     })
       // }
        
        
        chipCategory = ArrayList()
        val hotelCategory = arrayOf(
            "< 200.000",
            "200.000 - 300.000",
            "300.000 - 400.000",
            "400.000 - 500.000",
            "> 500.000"
        )

        hotelCategory.forEach { categoryName ->
            val chip = generateChip(categoryName)

            hotelCategoryChip.addView(chip)
        }

        hotelCategoryChip.check(chipCategory[0])

        hotelCategoryChip.isSingleSelection = true

        hotelCategoryChip.setOnCheckedChangeListener { chipGroup, i ->
            val chip = chipGroup.findViewById<Chip>(i)

            if (chip != null) {
                Toast.makeText(context,"Range Harga : ${chip.text}",Toast.LENGTH_SHORT).show()
            }
        }
    }

    @SuppressLint("NewApi")
    private fun generateChip(text: String): Chip {
        val chip = Chip(activity)
        chip.id = View.generateViewId()

        chipCategory.add(chip.id)

        val chipDrawable =
            ChipDrawable.createFromAttributes(activity, null, 0, R.style.customChips)
        chip.setChipDrawable(chipDrawable)
        chip.setTextAppearanceResource(R.style.customChips)
        chip.chipStrokeWidth =1f
        chip.chipStrokeColor = ColorStateList.valueOf(resources.getColor(R.color.colorPrimaryGray))

        chip.isClickable = true
        chip.isCheckable = true
        chip.text = text
        return chip
    }

    override fun onResume() {
        super.onResume()
        fan()
    }

    fun fan(){
        val loading = ProgressDialog(context)
        loading.setMessage("Memuat data...")
        loading.show()
        var listHotel = ArrayList<Hotel>()
        hotelRecyclerView.setHasFixedSize(true)
        hotelRecyclerView.layoutManager = LinearLayoutManager(context)
        AndroidNetworking.get(Endpoint.READHOTEL)
            .setPriority(Priority.HIGH)
            .build()
            .getAsJSONObject(object : JSONObjectRequestListener {

                override fun onResponse(response: JSONObject?) {
                    listHotel.clear()

                    val jsonArray = response?.optJSONArray("result")

                    if(jsonArray?.length() == 0){
                        loading.dismiss()
                        Toast.makeText(context,"Data Hotel Kosong",Toast.LENGTH_SHORT).show()
                    }

                    for(i in 0 until jsonArray?.length()!!){

                        val jsonObject = jsonArray?.optJSONObject(i)
                        listHotel.add(Hotel(
                            jsonObject.getString("nama_hotel"),
                            jsonObject.getString("nama_daerah"),
                            jsonObject.getString("alamat_lengkap"),
                            jsonObject.getString("detail"),
                            jsonObject.getInt("harga"),
                            jsonObject.getString("range_harga"),
                            jsonObject.getInt("jumlah_kamar"),
                            jsonObject.getString("fasilitas"),
                            jsonObject.getString("gambar_hotel")
                        ))

                        if(jsonArray?.length() - 1 == i){

                            loading.dismiss()
                            val adapter = HotelAdapter(listHotel)
                            adapter.notifyDataSetChanged()

                            hotelRecyclerView.adapter = adapter
                            adapter.setOnItemClickCallback(object : HotelAdapter.OnItemClickCallback {
                                override fun onItemClicked(data: Hotel) {
                                    showSelectedVacation(data)
                                }
                            })

                        }

                    }

                }

                override fun onError(anError: ANError?) {
                    loading.dismiss()
                    Log.d("ONERROR",anError?.errorDetail?.toString())
                    Toast.makeText(context,"Connection Failure",Toast.LENGTH_SHORT).show()
                }

            })
    }

    private fun showSelectedVacation(data: Hotel) {
      Toast.makeText(context, " "+data.namaHotel,Toast.LENGTH_SHORT).show()
       val args = DestinationFragmentDirections.actionDestinationFragmentToDetailFragment(nameHotel = data.namaHotel,
           daerahHotel = data.namaDaerah, alamatHotel = data.alamatLengkap, detailHotel = data.detailHotel,
           hargaHotel = data.harga, rangeHotel = data.rangeHarga, jumlahKamarHotel = data.jumlahKamar,
           fasilitasHotel = data.fasilitas, gambarHotel = data.gambarHotel)
        findNavController().navigate(args)
    }

}
