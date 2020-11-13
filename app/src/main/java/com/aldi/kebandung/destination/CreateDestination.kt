package com.aldi.kebandung.destination


import android.app.ProgressDialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.view.isVisible

import com.aldi.kebandung.R
import kotlinx.android.synthetic.main.fragment_create_destination.*

class CreateDestination : Fragment() {
    var selectedSpinner : String? = null
    var selectedSpinnerr : String? = null
    //var listDaerah = ArrayList<Daerah>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_create_destination, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        spinnerCreate()
        createDestination()
        //fan()
    }

    fun spinnerCreate() {
        layoutSpinner.setOnClickListener {
            if (layoutSpinner.isVisible) {
                layoutSpinner.visibility = View.INVISIBLE
                val itemSpinner = getActivity()?.let { it1 ->
                    ArrayAdapter(
                        it1,
                        android.R.layout.simple_spinner_dropdown_item,
                        resources.getStringArray(R.array.menu_kategori)
                    )
                }
                spinnerAdd.adapter = itemSpinner
                spinnerAdd.performClick()
                spinnerAdd.visibility = View.VISIBLE
                spinnerAdd.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
                    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                       val getSpinner = itemSpinner?.getItem(p2).toString()
                        selectedSpinner = getSpinner
                    }

                    override fun onNothingSelected(parent: AdapterView<*>?) {
                        Toast.makeText(getActivity(),"Pilih Kategori Destinasi",Toast.LENGTH_SHORT).show()
                    }
                }
            } else {
                layoutSpinner.visibility = View.VISIBLE
            }
        }
        layoutDaerah.setOnClickListener {
            if (layoutDaerah.isVisible) {
                layoutDaerah.visibility = View.INVISIBLE

                val itemSpinnerr = context?.let { it1 ->
                    ArrayAdapter(
                        it1,
                        android.R.layout.simple_spinner_dropdown_item,
                        resources.getStringArray(R.array.menu_daerah)
                    )
                }
                spinnerDaerah.adapter = itemSpinnerr
                spinnerDaerah.performClick()
                spinnerDaerah.visibility = View.VISIBLE
                spinnerDaerah.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
                    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                        val getSpinnerr = itemSpinnerr?.getItem(p2).toString()
                        selectedSpinnerr = getSpinnerr
                    }

                    override fun onNothingSelected(parent: AdapterView<*>?) {
                        Toast.makeText(getActivity(),"Pilih Daerah",Toast.LENGTH_SHORT).show()
                    }
                }
            } else {
                layoutDaerah.visibility = View.VISIBLE
            }
        }
    }

    fun createDestination(){
        btnNext.setOnClickListener {
           // Toast.makeText(context,"${inputDestinasi.text.toString()}, ${selectedSpinner.toString()}," +
               //     "${selectedSpinnerr.toString()}",Toast.LENGTH_SHORT).show()
           // Toast.makeText(context,"${namaDaerah}",Toast.LENGTH_SHORT).show()
        }
    }

    /**
    fun fan(){
        val loading = ProgressDialog(context)
        loading.setMessage("Memuat data...")
        loading.show()
        AndroidNetworking.get(Endpoint.READDAERAH)
            .setPriority(Priority.HIGH)
            .build()
            .getAsJSONObject(object : JSONObjectRequestListener {

                override fun onResponse(response: JSONObject?) {
                    listDaerah.clear()

                    val jsonArray = response?.optJSONArray("result")

                    if(jsonArray?.length() == 0){
                        loading.dismiss()
                        Toast.makeText(context,"Data Daerah Kosong",Toast.LENGTH_SHORT).show()
                    }

                    for(i in 0 until jsonArray?.length()!!){
                        loading.dismiss()
                        val jsonObject = jsonArray?.optJSONObject(i)
                        listDaerah.add(
                            Daerah(
                                jsonObject.getString("nama_daerah")
                            )

                        )
                    }

                }

                override fun onError(anError: ANError?) {
                    Log.d("ONERROR",anError?.errorDetail?.toString())
                    Toast.makeText(context,"Connection Failure",Toast.LENGTH_SHORT).show()
                }

            })
    }
     **/

}
