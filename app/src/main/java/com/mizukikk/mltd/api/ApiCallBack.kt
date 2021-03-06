package com.mizukikk.mltd.api

import com.mizukikk.mltd.BuildConfig
import com.mizukikk.mltd.MLTDApplication
import com.mizukikk.mltd.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

abstract class ApiCallBack<T> : Callback<T> {

    override fun onResponse(call: Call<T>, response: Response<T>) {
        if (response.isSuccessful) {
            val body = response.body()!!
            apiSuccess(body, call)
        } else {
            val message = MLTDApplication.appContext.getString(R.string.service_error)
            apiFail(message, response.code(), call)
        }
    }

    override fun onFailure(call: Call<T>, t: Throwable) {
        if (BuildConfig.DEBUG)
            t.printStackTrace()
        val message = MLTDApplication.appContext.getString(R.string.service_error)
        apiFail(message, null, call)
    }

    abstract fun apiSuccess(response: T, call: Call<T>)
    abstract fun apiFail(errorMessage: String, errorCode: Int?, call: Call<T>)

}