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
import com.aldi.kebandung.adapter.BannerWisata
import com.aldi.kebandung.etc.AppPreferences
import com.aldi.kebandung.etc.AppPreferences.preferences
import com.aldi.kebandung.etc.AppPreferences.username
import com.aldi.kebandung.etc.ChangeToolbarTitle
import com.aldi.kebandung.etc.Endpoint
import com.aldi.kebandung.model.Wisata
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONObjectRequestListener
import kotlinx.android.synthetic.main.fragment_account.*
import kotlinx.android.synthetic.main.fragment_home.*
import org.json.JSONObject
import java.util.*
import kotlin.collections.ArrayList


class AccountFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_account, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as ChangeToolbarTitle).showToolbar(true)
        (activity as ChangeToolbarTitle).updateTitle("Akun")
       // buttonLogin()
        buttonLogout()
        createDestination()
        rekomendasiRv()
        tvProfileUsername.text = username
    }

    /** fun buttonLogin(){
        btnLogin.setOnClickListener {
            findNavController().navigate(R.id.loginFragment2)
            (activity as ChangeToolbarTitle).showToolbar(false)
        }
    } **/

    private fun createDestination(){
        tvTambahTempat.setOnClickListener {
           findNavController().navigate(R.id.createDestination)
            (activity as ChangeToolbarTitle).showToolbar(false)
        }
    }

    fun buttonLogout(){
        btnLogout.setOnClickListener {
            (activity as ChangeToolbarTitle).showToolbar(false)
            AppPreferences.isLogin = false
            preferences.edit().remove("username").apply()
            preferences.edit().remove("password").apply()
            findNavController().navigate(R.id.authFragment)
        }
    }

    private fun rekomendasiRv() {
        val loading = ProgressDialog(context)
        loading.setMessage("Memuat data...")
        loading.show()
        var listWisata = ArrayList<Wisata>()
        rvHistory.setHasFixedSize(true)
        rvHistory.layoutManager = LinearLayoutManager(activity, LinearLayout.HORIZONTAL, false)
        AndroidNetworking.get(Endpoint.READWISATA)
            .setPriority(Priority.HIGH)
            .build()
            .getAsJSONObject(object : JSONObjectRequestListener {

                override fun onResponse(response: JSONObject?) {
                    listWisata.clear()

                    val jsonArray = response?.optJSONArray("result")

                    if(jsonArray?.length() == 0){
                        loading.dismiss()
                        Toast.makeText(context,"Data Wisata Kosong", Toast.LENGTH_SHORT).show()
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

                            rvHistory.adapter = adapter
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
                    Toast.makeText(context,"Connection Failure", Toast.LENGTH_SHORT).show()
                }

            })
    }

    private fun showSelectedVacation(data: Wisata) {
        Toast.makeText(context, " "+data.namaWisata,Toast.LENGTH_SHORT).show()
        val args = AccountFragmentDirections.actionAccountFragmentToDetailFragment(data.namaWisata,data.namaDaerah
            , data.alamatLengkap, data.detailWisata, data.namaKategoriWisata, data.jamBuka, data.jamTutup,
            data.harga,data.gambarWisata)
        findNavController().navigate(args)
    }
}
