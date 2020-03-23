package com.secdev.mvpchatapp.module.splashscreen

import android.os.Handler
import com.google.firebase.auth.FirebaseAuth
import com.secdev.mvpchatapp.base.BasePresenter

class SplashPresenter(
    var _view: SplashContract.ViewInterface? = null
): BasePresenter<SplashContract.ViewInterface>, SplashContract.PresenterInterface {
    override fun onAttach(view: SplashContract.ViewInterface) {
        _view = view
    }

    override fun onDettach() {
        _view = null
    }

    override fun delay(timer: Long, auth: FirebaseAuth?) {
        Handler().postDelayed({
            if(auth?.currentUser != null) {
                _view?.isSuccess()
            } else {
                _view?.isError("App crashed, I'm sorry :(")
            }
        }, timer)
    }

}