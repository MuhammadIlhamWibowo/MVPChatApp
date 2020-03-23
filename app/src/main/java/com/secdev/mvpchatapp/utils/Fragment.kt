package com.secdev.mvpchatapp.utils

import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment

open class Fragment : Fragment() {
    lateinit var act: AppCompatActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        act = activity as AppCompatActivity
    }

    fun toast(message: String) {
        Toast.makeText(act, message, Toast.LENGTH_LONG).show()
    }
}