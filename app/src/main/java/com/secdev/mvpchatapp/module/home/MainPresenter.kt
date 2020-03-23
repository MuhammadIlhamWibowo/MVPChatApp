package com.secdev.mvpchatapp.module.home

import com.secdev.mvpchatapp.base.BasePresenter

class MainPresenter(
    var _view: MainContract.ViewInterface? = null
): BasePresenter<MainContract.ViewInterface>, MainContract.PresenterInterface {
    override fun onAttach(view: MainContract.ViewInterface) {
        _view = view
    }

    override fun onDettach() {
        _view = null
    }
}