package com.example.testmacropay.utils

import android.text.InputFilter
import android.widget.EditText

fun EditText.filterEmoji() {
    filters = arrayOf(InputFilter { source, _, _, _, _, _ ->
        source.filter { Character.getType(it) != Character.SURROGATE.toInt() }
    })
}