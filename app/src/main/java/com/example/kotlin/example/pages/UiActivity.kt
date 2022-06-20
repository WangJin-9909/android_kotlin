package com.example.kotlin.example.pages

import com.example.kotlin.BaseActivity
import com.example.kotlin.R
import kotlinx.android.synthetic.main.activity_ui.*

class UiActivity : BaseActivity() {
    override fun getLayoutResID(): Int {



        return R.layout.activity_ui


    }


    override fun initUi() {
        super.initUi()

        btn_ui_1.postDelayed(Runnable(){
            showToast("控件初始化后会在一定时间后调用post Delay安排的任务")
        },500)
    }





}