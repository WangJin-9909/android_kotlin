package com.example.kotlin.example.utils.gm_net_library.bean


data class Error(
    var code: String,
    var message: String,
    var passportCode: String,
    var passportMessage: String,
    var type: String,
    var value: String
)

data class Response<E>(
    var data: E,
    var code: String,
    var msg: String,
    var success: Boolean,
    var error: Error
)