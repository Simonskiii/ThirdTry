package com.example.thirdtry.utils

import androidx.lifecycle.MutableLiveData
import retrofit2.CallAdapter
import retrofit2.CallAdapter.Factory
import retrofit2.Response
import retrofit2.Retrofit
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type


class LiveDataCallAdapterFactory : Factory() {
    override fun get(
        returnType: Type,
        annotations: Array<Annotation>,
        retrofit: Retrofit
    ): CallAdapter<*, *>? {
        val responseType: Type
        if (Factory.getRawType(returnType) != MutableLiveData::class.java) {
            throw IllegalStateException("return type must be parameterized")
        }
        val observableType = Factory.getParameterUpperBound(0, returnType as ParameterizedType)
        val a = observableType
        val rawObservableType = Factory.getRawType(observableType)
        responseType = if (rawObservableType == Response::class.java) {
            if (observableType !is ParameterizedType) {
                throw IllegalArgumentException("Response must be parameterized")
            }
            Factory.getParameterUpperBound(1, observableType)
        } else {
            observableType
        }
        return LiveDataCallAdapter<Any>(responseType)
    }
}