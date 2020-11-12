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
import com.aldi.kebandung.adapter.WisataAdapter
import com.aldi.kebandung.menu.DestinationFragmentDirections
import com.aldi.kebandung.model.Wisata
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONObjectRequestListener
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipDrawable
import kotlinx.android.synthetic.main.fragment_vacation.*
import org.json.JSONObject


class VacationFragment : Fragment() {

    private lateinit var chipCategory: ArrayList<Int>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_vacation, container, false)
    }

    @SuppressLint("WrongConstant")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



      //   vacationRecyclerView.apply {
       // layoutManager = LinearLayoutManager(activity, LinearLayout.VERTICAL, false)
       // (adapter as WisataAdapter).setOnItemClickCallback(object : WisataAdapter.OnItemClickCallback {
       //     override fun onItemClicked(data: Wisata) {
       //         showSelectedVacation(data)
       //     }
       //     })
       //   }


        chipCategory = ArrayList()
        val vacationCategory = arrayOf(
            "Taman",
            "Pantai",
            "Gunung",
            "Bukit",
            "Hutan",
            "Air Terjun",
            "Kebung Binatang",
            "Pantai",
            "Museum",
            "Makam",
            "Situs Sejarah"
        )

        vacationCategory.forEach { categoryName ->
            val chip = generateChip(categoryName)

            vacationCategoryChip.addView(chip)
        }

        vacationCategoryChip.check(chipCategory[0])

        vacationCategoryChip.isSingleSelection = true

        vacationCategoryChip.setOnCheckedChangeListener { chipGroup, i ->
            val chip = chipGroup.findViewById<Chip>(i)

            if (chip != null) {
                Toast.makeText(context,"Kategori Wisata : ${chip.text}",Toast.LENGTH_SHORT).show()
            }
        }
    }
    override fun onResume() {
        super.onResume()
        fan()
    }

    fun fan(){
        val loading = ProgressDialog(context)
        loading.setMessage("Memuat data...")
        loading.show()
        var listWisata = ArrayList<Wisata>()
        vacationRecyclerView.setHasFixedSize(true)
        vacationRecyclerView.layoutManager = LinearLayoutManager(context)
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
                        listWisata.add(Wisata(
                            jsonObject.getString("nama_wisata"),
                            jsonObject.getString("nama_daerah"),
                            jsonObject.getString("alamat_lengkap"),
                            jsonObject.getString("detail_wisata"),
                            jsonObject.getString("nama_kategori_wisata"),
                            jsonObject.getString("jam_buka"),
                            jsonObject.getString("jam_tutup"),
                            jsonObject.getInt("harga"),
                            jsonObject.getString("gambar_wisata")
                        ))

                        if(jsonArray?.length() - 1 == i){

                            loading.dismiss()
                            val adapter = WisataAdapter(listWisata)
                            adapter.notifyDataSetChanged()

                            vacationRecyclerView.adapter = adapter
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

    private fun showSelectedVacation(data: Wisata) {
        Toast.makeText(context, " "+data.namaWisata,Toast.LENGTH_SHORT).show()
        val args = DestinationFragmentDirections.actionDestinationFragmentToDetailFragment(data.namaWisata,data.namaDaerah
            , data.alamatLengkap, data.detailWisata, data.namaKategoriWisata, data.jamBuka, data.jamTutup,
            data.harga,data.gambarWisata)
        findNavController().navigate(args)
    }



}
