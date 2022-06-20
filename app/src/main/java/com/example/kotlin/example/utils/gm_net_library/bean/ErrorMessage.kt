package com.example.kotlin.example.utils.gm_net_library.bean

import okhttp3.Request
import okhttp3.Response

data class ErrorMessage(var request: Request, var response: Response, var bean:ErrorResponse?)