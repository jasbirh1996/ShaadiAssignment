package com.jb.shaadiassignment.util

import okhttp3.logging.HttpLoggingInterceptor

class ApiLogger : HttpLoggingInterceptor.Logger {
    override fun log(message: String) {
        println(message)
    }
}