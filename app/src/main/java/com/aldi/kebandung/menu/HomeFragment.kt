package com.aldi.kebandung.menu


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager

import com.aldi.kebandung.R
import com.aldi.kebandung.adapter.BannerAdapter
import com.aldi.kebandung.adapter.DummyAdapter
import com.aldi.kebandung.data.dummyData
import com.aldi.kebandung.model.Dummy
import com.aldi.kebandung.etc.ChangeToolbarTitle
import kotlinx.android.synthetic.main.fragment_home.*

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
        rvPopuler.apply{
            layoutManager = LinearLayoutManager(activity, LinearLayout.HORIZONTAL, false)
            adapter = DummyAdapter(dummyData.dummyData)
            (adapter as DummyAdapter).setOnItemClickCallback(object : DummyAdapter.OnItemClickCallback {
                override fun onItemClicked(data: Dummy) {
                    showSelectedVacation(data)
                }
            })
        }

        rvRekomendasi.apply{
            layoutManager = LinearLayoutManager(activity, LinearLayout.HORIZONTAL, false)
            adapter = BannerAdapter(dummyData.dummyDataRekomendasi)
            (adapter as BannerAdapter).setOnItemClickCallback(object : BannerAdapter.OnItemClickCallback {
                override fun onItemClicked(data: Dummy) {
                    showSelectedVacation(data)
                }
            })
        }

        rvPenginapan.apply{
            layoutManager = LinearLayoutManager(activity, LinearLayout.HORIZONTAL, false)
            adapter = BannerAdapter(dummyData.dummyDataHotel)
            (adapter as BannerAdapter).setOnItemClickCallback(object : BannerAdapter.OnItemClickCallback {
                override fun onItemClicked(data: Dummy) {
                    showSelectedVacation(data)
                }
            })
        }

        rvKuliner.apply{
            layoutManager = LinearLayoutManager(activity, LinearLayout.HORIZONTAL, false)
            adapter = BannerAdapter(dummyData.dummyDataRestaurant)
            (adapter as BannerAdapter).setOnItemClickCallback(object : BannerAdapter.OnItemClickCallback {
                override fun onItemClicked(data: Dummy) {
                    showSelectedVacation(data)
                }
            })
        }
    }

    fun buttonCheckDestination(){
        btnCheckDestination.setOnClickListener {
            findNavController().navigate(R.id.destinationFragment)
        }
    }

    private fun showSelectedVacation(data: Dummy) {
    Toast.makeText(context, " "+data.nameVacation, Toast.LENGTH_SHORT).show()
      //  val args = HomeFragmentDirections.actionHomeFragmentToDetailFragment(data.nameVacation,data.kecamatanVacation
     //   , data.alamatVacation, data.detailVacation, data.alamatLengkap, data.jamVacation, data.photoVacation, data.harga)
     //   findNavController().navigate(args)
    }


}
