package com.secdev.mvpchatapp.module.splashscreen

import android.os.Bundle
import com.secdev.mvpchatapp.R
import com.secdev.mvpchatapp.utils.AppCompatActivity
import com.secdev.mvpchatapp.utils.startHomeActivity
import com.secdev.mvpchatapp.utils.startLoginActivity

class SplashActivity : AppCompatActivity(), SplashContract.ViewInterface {

    private val presenter by lazy { SplashPresenter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        presenter
        onAttachView()
        presenter.delay(
            getSplashDuration(),
            database.auth
        )
    }

    private fun getSplashDuration() = 2000L

    override fun isSuccess() {
        startHomeActivity()
    }

    override fun isError(message: String) {
        toast(message)
        startLoginActivity()
    }

    override fun onAttachView() {
        presenter.onAttach(this)
    }

    override fun onDettachView() {
        presenter.onDettach()
    }

    override fun onStart() {
        super.onStart()
        onAttachView()
    }

    override fun onDestroy() {
        super.onDestroy()
        onDestroy()
    }
}
