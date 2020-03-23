package com.secdev.mvpchatapp.module.splashscreen

import com.google.firebase.auth.FirebaseAuth
import com.secdev.mvpchatapp.base.BaseView

interface SplashContract {
    interface PresenterInterface {
        fun delay(timer: Long, auth: FirebaseAuth?)
    }
    interface ViewInterface: BaseView {
        fun isSuccess()
        fun isError(message: String)
    }
}