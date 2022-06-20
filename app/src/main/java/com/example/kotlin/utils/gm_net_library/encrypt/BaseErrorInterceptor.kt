package com.example.kotlin.utils.gm_net_library.encrypt

import com.example.kotlin.utils.gm_net_library.bean.ErrorMessage
import com.example.kotlin.utils.gm_net_library.bean.ErrorResponse
import okhttp3.*
import okio.Buffer
import okio.BufferedSource
import java.nio.charset.Charset

public abstract class BaseErrorInterceptor : Interceptor{
    abstract fun getError(json:String):ErrorResponse
    abstract fun isError(response: ErrorMessage)

    override fun intercept(chain: Interceptor.Chain?): Response {
        //从请求链中获取请求
        var request :Request = chain!!.request()
        //获取请求的Url
        var url:HttpUrl = request.url()
        //从链中获取响应
        var response:Response = chain.proceed(request)
        try{
            var body : ResponseBody = response.body()
            var contentLength:Long = body.contentLength()
            var source : BufferedSource = body.source()
            source.request(Long.MAX_VALUE)
            val buffer: Buffer = source.buffer()

            var utf_8 : Charset =Charset.forName("UTF-8")
            var charset = utf_8
            var contentType : MediaType = body.contentType()
            if(contentType!=null){
                try{
                    charset = contentType.charset(utf_8)
                }catch (e:Exception){
                    return  response
                }
            }
            if (isPlainText(buffer)) {
                //("<-- END HTTP (binary " + buffer.size() + "-byte body omitted)");
                return response
            }
            var code : String? = null
            var msg:String? = null
            var response_1 : ErrorResponse? = null
            if(contentLength > 0){
                var json:String = buffer.clone().readString(charset)
                response_1 = getError(json)
                if(response_1 != null){
                    code = response_1.code
                    msg= response_1.code
                }else{
                    code = ""
                    msg = ""
                }
            }
            //服务器返回数据不对,返回数据不对，回调字类实现的isError方法
            if(response.code() != 200 || code == null || !code.equals("200")){
                var errorMessage:  ErrorMessage = ErrorMessage(request, response, response_1)
                isError(errorMessage)
            }

        }catch (e:Exception){
            e.printStackTrace()
        }
        return  response
    }



     fun isPlainText(buffer: Buffer):Boolean{
        try{
            val prefix = Buffer()
            val byteCount : Long = if(buffer.size() < 64){
                buffer.size()
            }else{
                64
            }
            buffer.copyTo(prefix, 0, byteCount)
            for(i in 0..16){
                if(prefix.exhausted()) break
                val codePoint:Int = prefix.readUtf8CodePoint()
                if(Character.isISOControl(codePoint) && !Character.isWhitespace(codePoint)){
                    return false
                }
                return true
            }

        }catch (e:Exception){
            return false
        }
         return false
    }

}