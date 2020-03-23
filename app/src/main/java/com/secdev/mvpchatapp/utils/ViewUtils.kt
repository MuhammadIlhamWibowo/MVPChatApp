package com.secdev.mvpchatapp.utils

import android.content.Context
import android.content.Intent
import com.secdev.mvpchatapp.module.auth.login.LoginActivity
import com.secdev.mvpchatapp.module.contact.add_new_contact.AddNewContactActivity
import com.secdev.mvpchatapp.module.home.MainActivity

fun Context.startHomeActivity() =
    Intent(this, MainActivity::class.java).also {
        it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(it)
    }

fun Context.startLoginActivity() =
    Intent(this, LoginActivity::class.java).also {
        it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(it)
    }