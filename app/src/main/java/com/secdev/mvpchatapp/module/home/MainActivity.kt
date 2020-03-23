package com.secdev.mvpchatapp.module.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.secdev.mvpchatapp.R
import com.secdev.mvpchatapp.module.contact.contact_list.contact_list_fragment.ContactListFragment
import com.secdev.mvpchatapp.utils.Fragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    private val bottomNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener {
        when (it.itemId) {
            R.id.bottom_contact -> {
                val fragment = ContactListFragment.newInstance()
                addFragment(fragment)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    private fun addFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.frameLayoutMain, fragment, fragment.javaClass.simpleName)
            .commit()
    }
}
