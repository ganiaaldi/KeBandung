package com.aldi.kebandung.destination


import android.annotation.SuppressLint
import android.content.res.ColorStateList
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
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipDrawable
import kotlinx.android.synthetic.main.fragment_restaurant.*


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
        restaurantRecyclerView.apply{
            layoutManager = LinearLayoutManager(activity, LinearLayout.VERTICAL, false)
            adapter = DummyAdapter(dummyData.dummyDataRestaurant)
            (adapter as DummyAdapter).setOnItemClickCallback(object : DummyAdapter.OnItemClickCallback {
                override fun onItemClicked(data: Dummy) {
                    showSelectedVacation(data)
                }
            })
        }
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

    private fun showSelectedVacation(data: Dummy) {
        Toast.makeText(context, " "+data.nameVacation,Toast.LENGTH_SHORT).show()
   //     val args = DestinationFragmentDirections.actionDestinationFragmentToDetailFragment(data.nameVacation,data.kecamatanVacation
     //       , data.alamatVacation, data.detailVacation, data.alamatLengkap, data.jamVacation, data.photoVacation, data.harga)
   //     findNavController().navigate(args)
    }

}
