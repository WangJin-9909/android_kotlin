package com.example.kotlin.example.pages

import android.util.Log
import android.widget.Button
import com.example.kotlin.BaseActivity
import com.example.kotlin.R
import com.example.kotlin.utils.FileUtils
import com.example.kotlin.utils.FFmpeg
import kotlinx.android.synthetic.main.activity_bili.*
import java.io.File

class BiliToolsActivity : BaseActivity() {
    val TAG = BiliToolsActivity::class.simpleName
    val fFmpeg: FFmpeg = FFmpeg()
    override fun getLayoutResID(): Int {
        return R.layout.activity_bili
    }

    override fun initUi() {
        super.initUi()
        findViewById<Button>(R.id.btn_bili_1).setOnClickListener {
            readFile("/sdcard/Android/data/tv.danmaku.bili/download/500050652/1/entry.json")
        }

        btn_bili_2.setOnClickListener {
            showToast(fFmpeg.fFMPEGVersion!!)
        }

    }


    private fun readFile(path: String): String? {
        File(path).takeIf { it.exists() }?.also {
            return FileUtils.readFileContent(getContext(), path)
        }
        Log.d(TAG, "readFile: $path not exists.")
        return null
    }


}