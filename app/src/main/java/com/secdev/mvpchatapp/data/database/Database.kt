package com.secdev.mvpchatapp.data.database

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage

class Database {
    private val database = FirebaseDatabase.getInstance()
    val auth = FirebaseAuth.getInstance()
    private val storage = FirebaseStorage.getInstance()

    val currentUser = auth.currentUser

    val user = database.reference.child("users")
    private val contacts = database.reference.child("contacts")

    // Notification
    private val notification = database.reference.child("notifications")

    // Messages
    private val chats = database.reference.child("chats")
    private val messages = database.reference.child("messages")

    fun getContactReference(userId: String): DatabaseReference {
        return contacts.child(userId)
    }

    fun getChatReference(userId: String): DatabaseReference {
        return chats.child(userId)
    }

    fun getMessageReference(userId: String): DatabaseReference {
        return messages.child(userId)
    }

    fun getUnreadMessage(userId: String): DatabaseReference {
        return notification.child(userId)
    }
}