package com.aldi.kebandung.menu


import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment

import com.aldi.kebandung.R
import com.aldi.kebandung.adapter.DestinationPagerAdapter
import com.aldi.kebandung.etc.ChangeToolbarTitle
import kotlinx.android.synthetic.main.fragment_destination.*


class DestinationFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_destination, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as ChangeToolbarTitle).showToolbar(true)
        (activity as ChangeToolbarTitle).updateTitle("Destinasi")
        setHasOptionsMenu(true)
        createPager()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater!!.inflate(R.menu.options_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    fun createPager(){
        val pagerAdapter = DestinationPagerAdapter(childFragmentManager)
        destinationViewPager.adapter = pagerAdapter
        destinationTabLayout.setupWithViewPager(destinationViewPager)
    }

}
