package com.example.thirdtry.ui.fragment.scheme

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.thirdtry.api.RetrofitClient
import com.example.thirdtry.base.BaseDataResult
import com.example.thirdtry.model.Scheme

class SchemeViewModel : ViewModel() {
    fun getScheme(token: String): MutableLiveData<BaseDataResult<Scheme>> =
        RetrofitClient.serviceApi.getScheme("JWT $token")
    var scheme : MutableLiveData<BaseDataResult<Scheme>>? = null
}
