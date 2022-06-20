package com.example.kotlin.example.pages

import com.example.kotlin.BaseActivity
import com.example.kotlin.R
import com.example.kotlin.utils.PreferenceUtil
import com.example.kotlin.utils.PreferenceUtil.getSecurePreference
import kotlinx.android.synthetic.main.activity_storage.*

class StorageActivity : BaseActivity() {
    override fun getLayoutResID(): Int {
        return R.layout.activity_storage
    }

    override fun initUi() {
        super.initUi()
        btn_storage_1.setOnClickListener { view ->
            showToast("目前存在一些问题 " + view.id)
            //testSharePreference()
        }
    }


    fun testSharePreference() {
        PreferenceUtil.getDefaultPreference(this)
            .edit()
            .putBoolean("test", false)
            .apply()
        getSecurePreference().edit().putBoolean("goSignHome", true).commit()
        val boolean = getSecurePreference().getBoolean("goSignHome", false)
        showToast("testSharePreference = $boolean")
    }

}