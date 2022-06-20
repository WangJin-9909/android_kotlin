package com.example.kotlin.example.pages

import android.provider.Settings
import android.provider.Settings.System.SCREEN_OFF_TIMEOUT
import com.example.kotlin.BaseActivity
import com.example.kotlin.R
import kotlinx.android.synthetic.main.activity_screen.*

class ScreenActivity :BaseActivity(){
    override fun getLayoutResID(): Int {
        return R.layout.activity_screen
    }

    override fun initUi() {
        super.initUi()
        btn_screen_1.setOnClickListener {
            // value为毫秒数,
            Settings.System.putInt(getContentResolver(),SCREEN_OFF_TIMEOUT, Integer.MAX_VALUE);

        }
    }



}