package com.example.kotlin.example.pages

import android.app.Activity
import android.view.View
import androidx.annotation.IdRes
import com.example.kotlin.BaseActivity
import com.example.kotlin.R
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_json.*
import org.json.JSONObject
import java.util.*

class JsonActivity : BaseActivity(), View.OnClickListener {
    override fun getLayoutResID(): Int {
        return R.layout.activity_json
    }


    override fun initUi() {
        super.initUi()
        btn_1.setOnClickListener(this)
        btn_2.setOnClickListener(this)
        btn_3.setOnClickListener (this)


    }

    fun testJson_3(){
        val contacts: MutableMap<String, String> =
            HashMap()
        contacts["lastName"] = "wangJin"
        contacts["mobile"] = "123456765432"
        val callJsMap: MutableMap<String, String> =
            HashMap()
        callJsMap["result"] = "0"
        callJsMap["contacts"] = Gson().toJson(contacts)
        showToast("contacts = " + Gson().toJson(contacts));
    }

    //为Activity增加一个扩展方法
    fun <T : View> Activity.find(@IdRes id: Int): T {
        return findViewById(id)
    }

    //为布局文件中的控件增加一个扩展方法
    fun Int.onClick(activity: Activity, click: () -> Unit) {
        activity.find<View>(this).apply {
            setOnClickListener{
                click()
            }
        }
    }


    fun testJson_1() {
        val json = """
            {
                "msg": {
                    "text": "成功",
                    "type": "0"
                },
                "resultMsg": "成功",
                "status": "1000"
            }
            
        """.trimIndent()
        val jsonObject: JSONObject = JSONObject(json)
        //判断是否存在某字段
        if (!jsonObject.isNull("msg")) {
            showToast("msg = ${jsonObject.getString("msg")}")
        }

        if (!jsonObject.isNull("message")) {
            showToast("message = ${jsonObject.getString("message")}")
        }
    }

    fun testJson_2() {
        val json = """
            {
                "msg": {
                    "text": "成功",
                    "type": "0"
                },
                "resultMsg": "成功",
                "status": "1000"
            }
            
        """.trimIndent()

        val jsonObject: JSONObject = JSONObject(json)
        jsonObject.put("status", "测试文本")

        showToast(jsonObject.toString())

    }


    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.btn_1 -> {
                testJson_1()
            }
            R.id.btn_2 -> {
                testJson_2()

            }

            R.id.btn_3 ->
                testJson_3()
        }
    }


}