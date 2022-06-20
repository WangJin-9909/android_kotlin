package com.example.kotlin.example.pages

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.kotlin.BaseActivity
import com.example.kotlin.BuildConfig

import com.example.kotlin.R
import kotlinx.android.synthetic.main.activity_build.*

class BuildActivity : BaseActivity() {
    override fun getLayoutResID(): Int {
        return R.layout.activity_build
    }

    override fun initUi() {
        super.initUi()
        btn_build_1.setOnClickListener { view->
            showBuildInfo()
        }
    }

    private fun showBuildInfo(){
        val content = BuildConfig.DOMAIN
        showToast("content = $content")
    }

}