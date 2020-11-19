package com.aldi.kebandung.menu


import android.app.ProgressDialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager

import com.aldi.kebandung.R
import com.aldi.kebandung.adapter.*
import com.aldi.kebandung.etc.ChangeToolbarTitle
import com.aldi.kebandung.etc.Endpoint
import com.aldi.kebandung.model.Hotel
import com.aldi.kebandung.model.Restaurant
import com.aldi.kebandung.model.Wisata
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONObjectRequestListener
import kotlinx.android.synthetic.main.fragment_home.*
import org.json.JSONObject
import java.util.*
import kotlin.collections.ArrayList

class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as ChangeToolbarTitle).showToolbar(true)
        (activity as ChangeToolbarTitle).updateTitle("Beranda")
        buttonCheckDestination()
        populerRv()
        rekomendasiRv()
        penginapanRv()
        kulinerRv()
    }

    private fun populerRv() {
        val loading = ProgressDialog(context)
        loading.setMessage("Memuat data...")
        loading.show()
        var listWisata = ArrayList<Wisata>()
        rvPopuler.setHasFixedSize(true)
        rvPopuler.layoutManager = LinearLayoutManager(activity, LinearLayout.HORIZONTAL, false)
        AndroidNetworking.get(Endpoint.READWISATA)
            .setPriority(Priority.HIGH)
            .build()
            .getAsJSONObject(object : JSONObjectRequestListener {

                override fun onResponse(response: JSONObject?) {
                    listWisata.clear()

                    val jsonArray = response?.optJSONArray("result")

                    if(jsonArray?.length() == 0){
                        loading.dismiss()
                        Toast.makeText(context,"Data Wisata Kosong",Toast.LENGTH_SHORT).show()
                    }

                    for(i in 0 until jsonArray?.length()!!){

                        val jsonObject = jsonArray?.optJSONObject(i)
                        listWisata.add(
                            Wisata(
                                jsonObject.getString("nama_wisata"),
                                jsonObject.getString("nama_daerah"),
                                jsonObject.getString("alamat_lengkap"),
                                jsonObject.getString("detail"),
                                jsonObject.getString("nama_kategori_wisata"),
                                jsonObject.getString("jam_buka"),
                                jsonObject.getString("jam_tutup"),
                                jsonObject.getInt("harga"),
                                jsonObject.getString("gambar_wisata")
                            )
                        )

                        if(jsonArray?.length() - 1 == i){

                            loading.dismiss()
                            val adapter = WisataAdapter(listWisata)
                            Collections.shuffle(listWisata)
                            adapter.notifyDataSetChanged()

                            rvPopuler.adapter = adapter
                            adapter.setOnItemClickCallback(object : WisataAdapter.OnItemClickCallback {
                                override fun onItemClicked(data: Wisata) {
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

    private fun rekomendasiRv() {
        val loading = ProgressDialog(context)
        loading.setMessage("Memuat data...")
        loading.show()
        var listWisata = ArrayList<Wisata>()
        rvRekomendasi.setHasFixedSize(true)
        rvRekomendasi.layoutManager = LinearLayoutManager(activity, LinearLayout.HORIZONTAL, false)
        AndroidNetworking.get(Endpoint.READWISATA)
            .setPriority(Priority.HIGH)
            .build()
            .getAsJSONObject(object : JSONObjectRequestListener {

                override fun onResponse(response: JSONObject?) {
                    listWisata.clear()

                    val jsonArray = response?.optJSONArray("result")

                    if(jsonArray?.length() == 0){
                        loading.dismiss()
                        Toast.makeText(context,"Data Wisata Kosong",Toast.LENGTH_SHORT).show()
                    }

                    for(i in 0 until jsonArray?.length()!!){

                        val jsonObject = jsonArray?.optJSONObject(i)
                        listWisata.add(
                            Wisata(
                                jsonObject.getString("nama_wisata"),
                                jsonObject.getString("nama_daerah"),
                                jsonObject.getString("alamat_lengkap"),
                                jsonObject.getString("detail"),
                                jsonObject.getString("nama_kategori_wisata"),
                                jsonObject.getString("jam_buka"),
                                jsonObject.getString("jam_tutup"),
                                jsonObject.getInt("harga"),
                                jsonObject.getString("gambar_wisata")
                            )
                        )

                        if(jsonArray?.length() - 1 == i){

                            loading.dismiss()
                            val adapter = BannerWisata(listWisata)
                            Collections.shuffle(listWisata)
                            adapter.notifyDataSetChanged()

                            rvRekomendasi.adapter = adapter
                            adapter.setOnItemClickCallback(object : BannerWisata.OnItemClickCallback {
                                override fun onItemClicked(data: Wisata) {
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

    private fun penginapanRv() {
        val loading = ProgressDialog(context)
        loading.setMessage("Memuat data...")
        loading.show()
        var listHotel = ArrayList<Hotel>()
        rvPenginapan.setHasFixedSize(true)
        rvPenginapan.layoutManager = LinearLayoutManager(activity, LinearLayout.HORIZONTAL, false)
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
                        listHotel.add(
                            Hotel(
                                jsonObject.getString("nama_hotel"),
                                jsonObject.getString("nama_daerah"),
                                jsonObject.getString("alamat_lengkap"),
                                jsonObject.getString("detail"),
                                jsonObject.getInt("harga"),
                                jsonObject.getInt("jumlah_kamar"),
                                jsonObject.getString("fasilitas"),
                                jsonObject.getString("gambar_hotel")
                            )
                        )

                        if(jsonArray?.length() - 1 == i){

                            loading.dismiss()
                            val adapter = BannerHotel(listHotel)
                            Collections.shuffle(listHotel)
                            adapter.notifyDataSetChanged()

                            rvPenginapan.adapter = adapter
                            adapter.setOnItemClickCallback(object : BannerHotel.OnItemClickCallback {
                                override fun onItemClicked(data: Hotel) {
                                    showSelectedHotel(data)
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

    fun kulinerRv(){
        val loading = ProgressDialog(context)
        loading.setMessage("Memuat data...")
        loading.show()
        var listRestaurant = ArrayList<Restaurant>()
        rvKuliner.setHasFixedSize(true)
        rvKuliner.layoutManager = LinearLayoutManager(activity, LinearLayout.HORIZONTAL, false)
        AndroidNetworking.get(Endpoint.READRESTAURANT)
            .setPriority(Priority.HIGH)
            .build()
            .getAsJSONObject(object : JSONObjectRequestListener {

                override fun onResponse(response: JSONObject?) {
                    listRestaurant.clear()

                    val jsonArray = response?.optJSONArray("result")

                    if(jsonArray?.length() == 0){
                        loading.dismiss()
                        Toast.makeText(context,"Data Restaurant Kosong",Toast.LENGTH_SHORT).show()
                    }

                    for(i in 0 until jsonArray?.length()!!){

                        val jsonObject = jsonArray?.optJSONObject(i)
                        listRestaurant.add(
                            Restaurant(
                                jsonObject.getString("nama_restaurant"),
                                jsonObject.getString("nama_daerah"),
                                jsonObject.getString("alamat_lengkap"),
                                jsonObject.getString("detail"),
                                jsonObject.getString("nama_kategori_kuliner"),
                                jsonObject.getString("jam_buka"),
                                jsonObject.getString("jam_tutup"),
                                jsonObject.getString("gambar_restaurant")
                            )
                        )

                        if(jsonArray?.length() - 1 == i){

                            loading.dismiss()
                            val adapter = BannerRestaurant(listRestaurant)
                            Collections.shuffle(listRestaurant)
                            adapter.notifyDataSetChanged()

                            rvKuliner.adapter = adapter
                            adapter.setOnItemClickCallback(object : BannerRestaurant.OnItemClickCallback {
                                override fun onItemClicked(data: Restaurant) {
                                    showSelectedRestaurant(data)
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

    fun buttonCheckDestination(){
        btnCheckDestination.setOnClickListener {
            findNavController().navigate(R.id.destinationFragment)
        }
    }

    private fun showSelectedVacation(data: Wisata) {
        Toast.makeText(context, " "+data.namaWisata,Toast.LENGTH_SHORT).show()
        val args = HomeFragmentDirections.actionHomeFragmentToDetailFragment(data.namaWisata,data.namaDaerah
            , data.alamatLengkap, data.detailWisata, data.namaKategoriWisata, data.jamBuka, data.jamTutup,
            data.harga,data.gambarWisata)
        findNavController().navigate(args)
    }

    private fun showSelectedHotel(data: Hotel) {
        Toast.makeText(context, " "+data.namaHotel,Toast.LENGTH_SHORT).show()
        val args = HomeFragmentDirections.actionHomeFragmentToDetailFragment(nameHotel = data.namaHotel,
            daerahHotel = data.namaDaerah, alamatHotel = data.alamatLengkap, detailHotel = data.detailHotel,
            hargaHotel = data.harga, jumlahKamarHotel = data.jumlahKamar,
            fasilitasHotel = data.fasilitas, gambarHotel = data.gambarHotel)
        findNavController().navigate(args)
    }

    private fun showSelectedRestaurant(data: Restaurant) {
        Toast.makeText(context, " "+data.namaRestaurant,Toast.LENGTH_SHORT).show()
        val args = HomeFragmentDirections.actionHomeFragmentToDetailFragment(nameRestaurant = data.namaRestaurant,
            daerahRestaurant = data.namaDaerah,alamatRestaurant = data.alamatLengkap, detailRestaurant = data.detailRestaurant,
            kategoriRestaurant = data.namaKategoriKuliner, jamBukaRestaurant = data.jamBuka,jamTutupRestaurant = data.jamTutup,
            gambarRestaurant = data.gambarRestaurant)
        findNavController().navigate(args)
    }
}
