package com.aldi.kebandung.destination


import android.annotation.SuppressLint
import android.app.ProgressDialog
import android.app.TimePickerDialog
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
import android.widget.TimePicker
import androidx.activity.addCallback
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.navigateUp
import kotlinx.android.synthetic.main.fragment_create_destination.view.*
import java.text.SimpleDateFormat
import java.util.*


class CreateDestination : Fragment(){

    var selectedSpinner : String? = null
    var selectedSpinnerr : String? = null
    var selectedSpinnerrr : String? = null
    var jamBukaOutput : String? = null
    var jamTutupOutput : String? = null
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
        OnClickTime()
        buttonPressed()


        //fan()
    }

    private fun OnClickTime() {
        var jamBuka = timePickerBuka as TimePicker
        var jamTutup = timePickerTutup as TimePicker
        jamBuka.setIs24HourView(true)
        jamTutup.setIs24HourView(true)
        val cal = Calendar.getInstance()

        //jamBukaOutput = SimpleDateFormat("HH:MM").format(cal.time).toString()
        //jamTutupOutput = SimpleDateFormat("HH:MM").format(cal.time).toString()

        jamBuka.setOnTimeChangedListener{
                 _, hour, minute->
            jamBukaOutput = String.format("%02d:%02d:00", hour, minute)
         }
        jamTutup.setOnTimeChangedListener{
                _, hour, minute->
            jamTutupOutput = String.format("%02d:%02d:00", hour, minute)
        }
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

        layoutKategori.setOnClickListener {
            if (layoutKategori.isVisible) {
                layoutKategori.visibility = View.INVISIBLE

                val itemSpinnerrr = context?.let { it1 ->
                    ArrayAdapter(
                        it1,
                        android.R.layout.simple_spinner_dropdown_item,
                        if(selectedSpinner == "Tempat Wisata"){
                            resources.getStringArray(R.array.array_wisata)
                        } else{
                            resources.getStringArray(R.array.array_kuliner)
                        }
                    )
                }
                spinnerKategori.adapter = itemSpinnerrr
                spinnerKategori.performClick()
                spinnerKategori.visibility = View.VISIBLE
                spinnerKategori.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
                    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                        val getSpinnerrr = itemSpinnerrr?.getItem(p2).toString()
                        selectedSpinnerrr = getSpinnerrr
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

    fun buttonPressed(){
        buttonBackk.setOnClickListener {
            findNavController().navigateUp()
        }
        btnNext.setOnClickListener {
            if(selectedSpinner != null && selectedSpinnerr != null && inputDestinasi.text.toString() != "")
            {
            form1.visibility = View.INVISIBLE
            formDetail.visibility = View.VISIBLE
            Toast.makeText(context,"${selectedSpinner.toString()},${selectedSpinnerr.toString()},${inputDestinasi.text.toString()}",Toast.LENGTH_LONG).show()
        } else{
                Toast.makeText(context,"Isi semua form terlebih dahulu",Toast.LENGTH_SHORT).show()
            }
        }

        btnBack.setOnClickListener {
            form1.visibility = View.VISIBLE
            formDetail.visibility = View.INVISIBLE
        }
        btnNext2.setOnClickListener {
            if(inputDetail.text.toString() != ""  && inputAlamatLengkap.text.toString() != "" )
            {
                formDetail.visibility = View.INVISIBLE
                if (selectedSpinner == "Tempat Penginapan") {
                    formDetailHotel.visibility = View.VISIBLE
                } else {
                    formDetailWisataRestaurant.visibility = View.VISIBLE
                }
                Toast.makeText(context,"${inputDetail.text.toString()},${inputAlamatLengkap.text.toString()}",Toast.LENGTH_LONG).show()
            } else{
                Toast.makeText(context,"Isi semua form terlebih dahulu",Toast.LENGTH_SHORT).show()
            }
        }

        btnBack2.setOnClickListener {
            formDetail.visibility = View.VISIBLE
            formDetailHotel.visibility = View.INVISIBLE
            formDetailWisataRestaurant.visibility = View.INVISIBLE
        }

        btnNext3.setOnClickListener {
            if(inputHarga.text.toString() != "" && timePickerTutup != null && timePickerTutup != null && selectedSpinnerrr != null) {
                formDetailHotel.visibility = View.INVISIBLE
                formDetailWisataRestaurant.visibility = View.INVISIBLE
                formPhoto.visibility = View.VISIBLE
                Toast.makeText(context,"${inputHarga.text.toString()}," +
                        "${jamBukaOutput},${jamTutupOutput},${selectedSpinnerrr.toString()}",Toast.LENGTH_LONG).show()
            } else{
                Toast.makeText(context,"Isi semua form terlebih dahulu",Toast.LENGTH_SHORT).show()
            }
        }

        btnBack3.setOnClickListener{
            formDetail.visibility = View.VISIBLE
            formDetailHotel.visibility = View.INVISIBLE
            formDetailWisataRestaurant.visibility = View.INVISIBLE
        }

        btnNext4.setOnClickListener {
            if(inputHargaa.text.toString() != "" && inputJumlahKamar.text.toString() != "" &&
                inputFasilitas.text.toString() != ""){
                formPhoto.visibility = View.VISIBLE
                formDetailHotel.visibility = View.INVISIBLE
                formDetailWisataRestaurant.visibility = View.INVISIBLE
                Toast.makeText(context,"${inputHargaa.text.toString()}," +
                        "${inputJumlahKamar.text.toString()},${inputFasilitas.text.toString()}",Toast.LENGTH_LONG).show()
            }
            else{
                Toast.makeText(context,"Isi semua form terlebih dahulu",Toast.LENGTH_SHORT).show()
            }
        }
        btnBack4.setOnClickListener {
            formDetail.visibility = View.VISIBLE
            formPhoto.visibility = View.INVISIBLE
        }

        btnNext5.setOnClickListener {
            if(uploadPhotoDone != null){
                formPhoto.visibility = View.VISIBLE
                formDetailHotel.visibility = View.INVISIBLE
                formDetailWisataRestaurant.visibility = View.INVISIBLE
                Toast.makeText(context,"${uploadPhotoDone}",Toast.LENGTH_LONG).show()
            }
            else{
                Toast.makeText(context,"Masukan gambar terlebih dahulu",Toast.LENGTH_SHORT).show()
            }
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
