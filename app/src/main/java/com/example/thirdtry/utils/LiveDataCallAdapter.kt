package com.example.thirdtry.utils

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Callback
import retrofit2.Response
import java.lang.reflect.Type
import java.util.concurrent.atomic.AtomicBoolean


//修改retrofit源码，返回的call类型改成livedata
class LiveDataCallAdapter<R>(private val responseType: Type) :
    CallAdapter<R, MutableLiveData<R>> {

    override fun responseType() = responseType
    override fun adapt(call: Call<R>): MutableLiveData<R> {
        return object : MutableLiveData<R>() {
            private var started = AtomicBoolean(false)
            override fun onActive() {
                super.onActive()
                if (started.compareAndSet(false, true)) {
                    call.enqueue(object : Callback<R> {
                        override fun onResponse(call: Call<R>, response: Response<R>) {
                            val a = response.body()
                            val b = a.toString()
                            postValue(response.body())

                        }
                        override fun onFailure(call: Call<R>, throwable: Throwable) {
                            postValue(null)
                        }
                    })
                }
            }
        }
    }
}