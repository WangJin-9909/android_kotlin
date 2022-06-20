package com.example.kotlin.example.utils

import android.content.Context
import android.text.TextUtils
import java.io.File

class FileUtils {
    companion object {


        /**
         * todo 将文件写入本地
         */

        /**
         * 文件自动重命名
         */
        private fun rename(oriFile: File):File {
            var destFile = File(oriFile.parent.substring(0, oriFile.path.length) + ".tmp".length)
            oriFile.renameTo(destFile)
            return destFile
        }

        private fun fileIsExist(file: File): Boolean {
            try {
                val f: File = file
                if (!f.exists()) {
                    return false
                }
            } catch (e: Exception) {
                return false
            }
            return true

        }

        private fun createDirs(dirPath: String) {
            val file: File = File(dirPath)
            if (!fileIsExist(file)) {
                file.mkdirs()
            }
        }

        private fun createDirs(file: File) {
            if (file != null) {
                if (fileIsExist(file)) {
                    file.delete()
                }
                val dir: String = file.parent
                createDirs(dir)
            }
        }

        private fun getFileSize(file: File): Long {
            if (file != null) {
                return file.length()
            } else {
                return 0
            }
        }

        private fun getSaveFilePath(context: Context, fileName: String): String? {
            var saveFilePath: String? = null
            var requestFileName = fileName


            //请求的文件名为空则根据时间戳生成一个临时文件名
            if (TextUtils.isEmpty(requestFileName)) {
                requestFileName = System.currentTimeMillis().toString() + ".tmp"
            }

            //如果保存路径是一个文件夹,则在后面加上请求文件名
            if (!TextUtils.isEmpty(saveFilePath)) {
                val file = File(saveFilePath)
                if (file.isDirectory) {
                    saveFilePath = saveFilePath + File.separator + requestFileName
                }
            }

            //如果保存路径为null则设置默认保存到sdcard根目录
            if (TextUtils.isEmpty(saveFilePath)) {
                saveFilePath = context.getCacheDir().getPath()
                    .toString() + File.separator + requestFileName
            }
            GMLogger.i("saveFilePath:$saveFilePath")
            return saveFilePath
        }
    }
}