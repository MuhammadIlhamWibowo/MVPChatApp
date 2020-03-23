package com.secdev.mvpchatapp.utils

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.gson.Gson
import com.secdev.mvpchatapp.data.database.Database
import com.secdev.mvpchatapp.data.model.Users

open class AppCompatActivity : AppCompatActivity() {

    val database = Database()
    var user = Users()
    val gson = Gson()
    private lateinit var sharedPreference: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        sharedPreference = getSharedPreferences(Config.PREFERENCE_FILE_KEY, Context.MODE_PRIVATE)
//        user = getFrom
    }

    protected fun updateUser(onUserFound: () -> Unit, onUserNotFound: () -> Unit) {
        if (database.currentUser != null) {
            database.user
                .child(database.currentUser.uid)
                .addListenerForSingleValueEvent(object : ValueEventListener {
                    override fun onCancelled(error: DatabaseError) {
                        toast(error.message)
                    }

                    override fun onDataChange(data: DataSnapshot) {
                        user = data.getValue(Users::class.java)!!
                        writeUserToSharedPreference(user)
                        onUserFound()
                    }
                })
        } else {
            onUserNotFound()
        }
    }

    private fun writeToSharedPreference(key: String, value: String) {
        val editor = sharedPreference.edit()
        editor.putString(key, value)
        editor.apply()
    }

    private fun writeUserToSharedPreference(user: Users) {
        val editor = sharedPreference.edit()
        editor.putString(Config.SP_USER, gson.toJson(user))
        editor.apply()
    }

    private fun getUserFromSharedPreference(): Users {
        val json = sharedPreference.getString(Config.SP_USER, null)
        return gson.fromJson(json, Users::class.java) ?: Users()
    }

    private fun removeFromSharedPreference(tag: String) {
        val editor = sharedPreference.edit()
        editor.remove(tag)
        editor.apply()
    }

    protected fun logout() {
        removeFromSharedPreference(Config.SP_USER)
        database.auth.signOut()
    }

    protected fun toast(message: String?) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }
}