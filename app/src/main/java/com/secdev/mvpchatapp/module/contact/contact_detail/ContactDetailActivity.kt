package com.secdev.mvpchatapp.module.contact.contact_detail

import android.os.Bundle
import com.bumptech.glide.Glide
import com.secdev.mvpchatapp.R
import com.secdev.mvpchatapp.data.model.Users
import com.secdev.mvpchatapp.utils.AppCompatActivity
import com.secdev.mvpchatapp.utils.Config
import kotlinx.android.synthetic.main.activity_contact_detail.*

class ContactDetailActivity : AppCompatActivity(), ContactDetailContract.ViewInterface {

    private var contact = Users()
    private val presenter by lazy { ContactDetailPresenter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact_detail)

        contact = gson.fromJson(intent.getStringExtra(Config.ARGS_CONTACT), Users::class.java)

        presenter
        onAttachView()
        presenter.doGetContactDetail(contact)

        btnBackContactDetail.setOnClickListener {
            onBackPressed()
        }
    }

    override fun isSuccessGetContactDetail(data: Users) {
        nameContactDetail.text = data.name
        Glide.with(this)
            .load(data.photoProfileUrl)
            .centerCrop()
            .placeholder(R.drawable.ic_photo_profile_placeholder)
            .into(contactDetailPhoto)
        phoneNumberContactDetail.text = data.phoneNumber
    }

    override fun isError(message: String) {
        toast(message)
    }

    override fun onAttachView() {
        presenter.onAttach(this)
    }

    override fun onDettachView() {
        presenter.onDettach()
    }

    override fun onStart() {
        super.onStart()
        presenter.onAttach(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDettach()
    }
}
