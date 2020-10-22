package com.aldi.kebandung.view

import android.view.View

interface ChangeToolbarTitle {

    fun updateTitle(title: String)
    fun toolbarAction(onClickListener: View.OnClickListener)
    fun showToolbar(show: Boolean)
}