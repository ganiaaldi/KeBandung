package com.aldi.kebandung.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.aldi.kebandung.destination.HotelFragment
import com.aldi.kebandung.destination.RestaurantFragment
import com.aldi.kebandung.destination.VacationFragment

class DestinationPagerAdapter(fragmentManager: FragmentManager) :
    FragmentStatePagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> VacationFragment()
            1 -> HotelFragment()
            else -> RestaurantFragment()
        }
    }

    override fun getCount(): Int {
        return 3
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when (position) {
            0 -> "Wisata"
            1 -> "Hotel"
            else -> "Kuliner"
        }
    }
}