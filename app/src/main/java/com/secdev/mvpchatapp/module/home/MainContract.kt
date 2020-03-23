package com.secdev.mvpchatapp.module.home

import com.secdev.mvpchatapp.base.BaseView

interface MainContract {
    interface PresenterInterface {

    }

    interface ViewInterface : BaseView {
        fun isError(message: String)
    }
}