package com.example.kotlin

import android.view.View
import com.example.kotlin.example.pages.BuildActivity
import com.example.kotlin.example.pages.JsonActivity
import com.example.kotlin.example.pages.StorageActivity
import com.example.kotlin.example.pages.UiActivity
import com.example.kotlin.example.utils.gm_net_library.bean.DownLoadBean
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.btn_1

class MainActivity : BaseActivity() {
    override fun getLayoutResID(): Int {
        return R.layout.activity_main
    }

    override fun initUi() {
        super.initUi()
        btn_1.setOnClickListener(View.OnClickListener {
            readyGo(JsonActivity::class.java, null)
        })
        btn_2_ui.setOnClickListener({
            readyGo(UiActivity::class.java, null)
        })
        btn_3.setOnClickListener { view->
            readyGo(StorageActivity::class.java, null)
        }
        btn_4_build.setOnClickListener { view->
            readyGo(BuildActivity::class.java, null)
        }
        btn_5_net_demo.setOnClickListener { view->

        }






    }






}
