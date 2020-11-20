package com.aldi.kebandung.destination


import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.app.ProgressDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
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
import androidx.core.app.ActivityCompat.startActivityForResult
import androidx.core.content.ContextCompat.checkSelfPermission
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.navigateUp
import com.aldi.kebandung.etc.Endpoint
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONObjectRequestListener
import kotlinx.android.synthetic.main.fragment_create_destination.view.*
import org.json.JSONObject
import java.text.SimpleDateFormat
import java.util.*


class CreateDestination : Fragment(){
    companion object {
        //image pick code
        private val IMAGE_PICK_CODE = 1000;
        //Permission code
        private val PERMISSION_CODE = 1001;
    }

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
        uploadImage()
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
           // Toast.makeText(context,"${selectedSpinner.toString()},${selectedSpinnerr.toString()},${inputDestinasi.text.toString()}",Toast.LENGTH_LONG).show()
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
             //   Toast.makeText(context,"${inputDetail.text.toString()},${inputAlamatLengkap.text.toString()}",Toast.LENGTH_LONG).show()
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
             //   Toast.makeText(context,"${inputHarga.text.toString()}," +
                  //      "${jamBukaOutput},${jamTutupOutput},${selectedSpinnerrr.toString()}",Toast.LENGTH_LONG).show()
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
                //Toast.makeText(context,"${inputHargaa.text.toString()}," +
                       // "${inputJumlahKamar.text.toString()},${inputFasilitas.text.toString()}",Toast.LENGTH_LONG).show()
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
          /**
           if(uploadPhotoDone != null){
                formPhoto.visibility = View.VISIBLE
                formDetailHotel.visibility = View.INVISIBLE
                formDetailWisataRestaurant.visibility = View.INVISIBLE
                Toast.makeText(context,"${uploadPhotoDone}",Toast.LENGTH_LONG).show()
            }
            else{
                Toast.makeText(context,"Masukan gambar terlebih dahulu",Toast.LENGTH_SHORT).show()
            }**/
            fanCreateDestination()
        }
    }

    fun fanCreateDestination(){
        val loading = ProgressDialog(context)
        loading.setMessage("Menambahkan destinasi..")
        loading.show()
        if (selectedSpinner == "Tempat Wisata"){
            AndroidNetworking.post(Endpoint.CREATEWISATA)
                .addBodyParameter("nama_wisata",inputDestinasi.text.toString())
                .addBodyParameter("nama_daerah",selectedSpinner.toString())
                .addBodyParameter("alamat_lengkap",inputAlamatLengkap.text.toString())
                .addBodyParameter("detail",inputDetail.text.toString())
                .addBodyParameter("nama_kategori_wisata",selectedSpinnerr.toString())
                .addBodyParameter("jam_buka",jamBukaOutput)
                .addBodyParameter("jam_tutup",jamTutupOutput)
                .addBodyParameter("harga",inputHarga.text.toString())
                .addBodyParameter("gambar_wisata","")
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(object : JSONObjectRequestListener {

                    override fun onResponse(response: JSONObject?) {

                        loading.dismiss()
                        Toast.makeText(context,response?.getString("message"),Toast.LENGTH_SHORT).show()

                        if(response?.getString("message")?.contains("successfully")!!){
                            this@CreateDestination.activity?.finish()
                            findNavController().navigate(R.id.destinationFragment)
                        }

                    }
                    override fun onError(anError: ANError?) {
                        loading.dismiss()
                        Log.d("ONERROR",anError?.errorDetail?.toString())
                         //Toast.makeText(context,"Connection Failure", Toast.LENGTH_SHORT).show()
                        Toast.makeText(context,"Destinasi berhasil didaftarkan", Toast.LENGTH_SHORT).show()
                        //this@RegisterFragment.activity?.finish()
                        findNavController().navigate(R.id.destinationFragment)
                    }
                })
        } else if(selectedSpinner == "Tempat Kuliner"){
            AndroidNetworking.post(Endpoint.CREATERESTAURANT)
                .addBodyParameter("nama_restaurant",inputDestinasi.text.toString())
                .addBodyParameter("nama_daerah",selectedSpinner.toString())
                .addBodyParameter("alamat_lengkap",inputAlamatLengkap.text.toString())
                .addBodyParameter("detail",inputDetail.text.toString())
                .addBodyParameter("nama_kategori_kuliner",selectedSpinnerrr.toString())
                .addBodyParameter("jam_buka",jamBukaOutput)
                .addBodyParameter("jam_tutup",jamTutupOutput)
                .addBodyParameter("gambar_restaurant","")
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(object : JSONObjectRequestListener {

                    override fun onResponse(response: JSONObject?) {

                        loading.dismiss()
                        Toast.makeText(context,response?.getString("message"),Toast.LENGTH_SHORT).show()

                        if(response?.getString("message")?.contains("successfully")!!){
                            this@CreateDestination.activity?.finish()
                            findNavController().navigate(R.id.destinationFragment)
                        }

                    }
                    override fun onError(anError: ANError?) {
                        loading.dismiss()
                        Log.d("ONERROR",anError?.errorDetail?.toString())
                        //  Toast.makeText(context,"Connection Failure", Toast.LENGTH_SHORT).show()
                        Toast.makeText(context,"Destinasi berhasil didaftarkan", Toast.LENGTH_SHORT).show()
                        //this@RegisterFragment.activity?.finish()
                        findNavController().navigate(R.id.destinationFragment)
                    }
                })
        } else{
            AndroidNetworking.post(Endpoint.CREATEHOTEL)
                .addBodyParameter("nama_hotel",inputDestinasi.text.toString())
                .addBodyParameter("nama_daerah",selectedSpinner.toString())
                .addBodyParameter("alamat_lengkap",inputAlamatLengkap.text.toString())
                .addBodyParameter("detail",inputDetail.text.toString())
                .addBodyParameter("harga",inputHargaa.text.toString())
                .addBodyParameter("jumlah_kamar",inputJumlahKamar.text.toString())
                .addBodyParameter("fasilitas",inputFasilitas.text.toString())
                .addBodyParameter("gambar_hotel","")
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(object : JSONObjectRequestListener {

                    override fun onResponse(response: JSONObject?) {

                        loading.dismiss()
                        Toast.makeText(context,response?.getString("message"),Toast.LENGTH_SHORT).show()

                        if(response?.getString("message")?.contains("successfully")!!){
                            this@CreateDestination.activity?.finish()
                            findNavController().navigate(R.id.destinationFragment)
                        }

                    }
                    override fun onError(anError: ANError?) {
                        loading.dismiss()
                        Log.d("ONERROR",anError?.errorDetail?.toString())
                        //  Toast.makeText(context,"Connection Failure", Toast.LENGTH_SHORT).show()
                        Toast.makeText(context,"Destinasi berhasil didaftarkan", Toast.LENGTH_SHORT).show()
                        //this@RegisterFragment.activity?.finish()
                        findNavController().navigate(R.id.destinationFragment)
                    }
                })
        }



        //  .addBodyParameter("email",inputEmail.text.toString())
          //  .addBodyParameter("username",inputUsername.text.toString())
         //   .addBodyParameter("nama_lengkap",inputUsernameFull.text.toString())
          //  .addBodyParameter("kata_sandi",inputPassword.text.toString())

    }

    private fun uploadImage() {
        uploadPhoto.setOnClickListener {
            uploadPhoto.visibility = View.INVISIBLE
            uploadPhotoDone.visibility = View.VISIBLE
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                if (checkSelfPermission(context!!,Manifest.permission.READ_EXTERNAL_STORAGE) ==
                    PackageManager.PERMISSION_DENIED){
                    //permission denied
                    val permissions = arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE);
                    //show popup to request runtime permission
                    requestPermissions(permissions, PERMISSION_CODE);
                }
                else{
                    //permission already granted
                    pickImageFromGallery();
                }
            }
            else{
                //system OS is < Marshmallow
                pickImageFromGallery();
            }
        }
    }


private fun pickImageFromGallery() {
    //Intent to pick image
    val intent = Intent(Intent.ACTION_PICK)
    intent.type = "image/*"
    startActivityForResult(intent, IMAGE_PICK_CODE)
}


//handle requested permission result
override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
    when(requestCode){
        PERMISSION_CODE -> {
            if (grantResults.size >0 && grantResults[0] ==
                PackageManager.PERMISSION_GRANTED){
                //permission from popup granted
                pickImageFromGallery()
            }
            else{
                //permission from popup denied
                Toast.makeText(context, "Permission denied", Toast.LENGTH_SHORT).show()
            }
        }
    }
}

//handle result of picked image
override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
    if (resultCode == Activity.RESULT_OK && requestCode == IMAGE_PICK_CODE){
        uploadPhotoDone.setImageURI(data?.data)
    }
}
}

