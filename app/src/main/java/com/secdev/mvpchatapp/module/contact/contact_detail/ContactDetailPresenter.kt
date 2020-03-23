package com.secdev.mvpchatapp.module.contact.contact_detail

import com.secdev.mvpchatapp.base.BasePresenter
import com.secdev.mvpchatapp.data.model.Users

class ContactDetailPresenter(
    var _view: ContactDetailContract.ViewInterface? = null
) : BasePresenter<ContactDetailContract.ViewInterface>, ContactDetailContract.PresenterInterface {
    override fun onAttach(view: ContactDetailContract.ViewInterface) {
        _view = view
    }

    override fun onDettach() {
        _view = null
    }

    override fun doGetContactDetail(data: Users) {
        if (data.id != null) {
            _view?.isSuccessGetContactDetail(data)
        } else {
            _view?.isError("Gagal mendapatkan data")
        }
    }
}