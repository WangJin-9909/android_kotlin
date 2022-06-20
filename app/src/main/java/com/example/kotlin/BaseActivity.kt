package com.example.kotlin

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

abstract  class BaseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        //去掉默认的AppBar
        if (supportActionBar != null) {
            supportActionBar!!.hide()

        }


        setContentView(getLayoutResID())

        initUi()


    }
    fun showToast(msg: String): Unit {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }


    fun getContext(): Context {
        return this;
    }

    fun readyGo(clazz: Class<*>, bundle: Bundle?) {
        if (null == getContext()) {
            return
        }
        val intent = Intent(getContext(), clazz)
        if (null != bundle) {
            intent.putExtras(bundle)
        }
        startActivity(intent)
    }


    abstract fun getLayoutResID(): Int

    open fun initUi(){}
}