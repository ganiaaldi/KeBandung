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
import android.widget.LinearLayout
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.aldi.kebandung.Endpoint

import com.aldi.kebandung.R
import com.aldi.kebandung.adapter.DummyAdapter
import com.aldi.kebandung.adapter.RestaurantAdapter
import com.aldi.kebandung.data.dummyData
import com.aldi.kebandung.menu.DestinationFragmentDirections
import com.aldi.kebandung.model.Dummy
import com.aldi.kebandung.model.Restaurant
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONObjectRequestListener
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipDrawable
import kotlinx.android.synthetic.main.fragment_restaurant.*
import org.json.JSONObject


class RestaurantFragment : Fragment() {

    private lateinit var chipCategory: ArrayList<Int>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_restaurant, container, false)
    }

    @SuppressLint("WrongConstant")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
       // restaurantRecyclerView.apply{
    //        layoutManager = LinearLayoutManager(activity, LinearLayout.VERTICAL, false)
     //       adapter = DummyAdapter(dummyData.dummyDataRestaurant)
    //        (adapter as DummyAdapter).setOnItemClickCallback(object : DummyAdapter.OnItemClickCallback {
    //            override fun onItemClicked(data: Dummy) {
    //                showSelectedVacation(data)
    //            }
   //         })
    //    }
        chipCategory = ArrayList()
        val restaurantCategory = arrayOf(
            "Keluarga",
            "Fast Food",
            "Seafood",
            "Oleh - oleh",
            "Coffe Shop",
            "Casual Dining",
            "Cafe",
            "Korea food",
            "Japan food",
            "Western food",
            "Rumah makan"
        )

        restaurantCategory.forEach { categoryName ->
            val chip = generateChip(categoryName)

            restaurantCategoryChip.addView(chip)
        }

        restaurantCategoryChip.check(chipCategory[0])

        restaurantCategoryChip.isSingleSelection = true

        restaurantCategoryChip.setOnCheckedChangeListener { chipGroup, i ->
            val chip = chipGroup.findViewById<Chip>(i)

            if (chip != null) {
                Toast.makeText(context,"Kategori Tempat Kuliner : ${chip.text}",Toast.LENGTH_SHORT).show()
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
        var listRestaurant = ArrayList<Restaurant>()
        restaurantRecyclerView.setHasFixedSize(true)
        restaurantRecyclerView.layoutManager = LinearLayoutManager(context)
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
                        listRestaurant.add(Restaurant(
                            jsonObject.getString("nama_restaurant"),
                            jsonObject.getString("nama_daerah"),
                            jsonObject.getString("alamat_lengkap"),
                            jsonObject.getString("detail_restaurant"),
                            jsonObject.getString("nama_kategori_kuliner"),
                            jsonObject.getString("jam_buka"),
                            jsonObject.getString("jam_tutup"),
                            jsonObject.getString("gambar_restaurant")
                        ))

                        if(jsonArray?.length() - 1 == i){

                            loading.dismiss()
                            val adapter = RestaurantAdapter(listRestaurant)
                            adapter.notifyDataSetChanged()

                            restaurantRecyclerView.adapter = adapter
                            adapter.setOnItemClickCallback(object : RestaurantAdapter.OnItemClickCallback {
                                override fun onItemClicked(data: Restaurant) {
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


    private fun showSelectedVacation(data: Restaurant) {
        Toast.makeText(context, " "+data.namaRestaurant,Toast.LENGTH_SHORT).show()
        val args = DestinationFragmentDirections.actionDestinationFragmentToDetailFragment(nameRestaurant = data.namaRestaurant,
            daerahRestaurant = data.namaDaerah,alamatRestaurant = data.alamatLengkap, detailRestaurant = data.detailRestaurant,
            kategoriRestaurant = data.namaKategoriKuliner, jamBukaRestaurant = data.jamBuka,jamTutupRestaurant = data.jamTutup,
            gambarRestaurant = data.gambarRestaurant)
        findNavController().navigate(args)
    }

}
