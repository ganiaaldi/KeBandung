package com.aldi.kebandung.data

import com.aldi.kebandung.R
import com.aldi.kebandung.model.Comment

object commentData : ArrayList<Comment>() {

    val commentData = arrayListOf(
        Comment("Asep Kabayan", R.drawable.asepnormal,"Tempatnya strategis di kota Bandung, " +
                "Pelayanan bagus, tempar parkir luas."),
        Comment("Euis Ayu", R.drawable.euishappy,"Kemarin kesini bareng sama teman-teman." +
                "Tempatnya bagus dan banyak spot foto-foto yang kekinian, cocok buat liburan sama teman-teman. " +
                "Pokoknya reccomended banget bun.")
    )
}