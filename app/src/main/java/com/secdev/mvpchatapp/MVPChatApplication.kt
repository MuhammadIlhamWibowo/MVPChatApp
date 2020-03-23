package com.secdev.mvpchatapp

import android.app.Application
import com.google.firebase.database.FirebaseDatabase

class MVPChatApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        FirebaseDatabase.getInstance().setPersistenceEnabled(true)
    }
}