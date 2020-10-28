package com.aldi.kebandung.destination


import android.annotation.SuppressLint
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
import com.aldi.kebandung.adapter.DummyAdapter
import com.aldi.kebandung.data.dummyData
import com.aldi.kebandung.menu.DestinationFragmentDirections
import com.aldi.kebandung.model.Dummy
import kotlinx.android.synthetic.main.fragment_restaurant.*


class RestaurantFragment : Fragment() {

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
        restaurantRecyclerView.apply{
            layoutManager = LinearLayoutManager(activity, LinearLayout.VERTICAL, false)
            adapter = DummyAdapter(dummyData.dummyDataRestaurant)
            (adapter as DummyAdapter).setOnItemClickCallback(object : DummyAdapter.OnItemClickCallback {
                override fun onItemClicked(data: Dummy) {
                    showSelectedVacation(data)
                }
            })
        }
    }

    private fun showSelectedVacation(data: Dummy) {
        Toast.makeText(context, " "+data.nameVacation,Toast.LENGTH_SHORT).show()
        val args = DestinationFragmentDirections.actionDestinationFragmentToDetailFragment(data.nameVacation,data.kecamatanVacation
            , data.alamatVacation, data.detailVacation, data.alamatLengkap, data.jamVacation, data.photoVacation, data.harga)
        findNavController().navigate(args)
    }

}
