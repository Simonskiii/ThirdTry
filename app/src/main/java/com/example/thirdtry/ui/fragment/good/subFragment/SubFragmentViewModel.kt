package com.example.thirdtry.ui.fragment.good.subFragment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.thirdtry.api.RetrofitClient
import com.example.thirdtry.base.BaseDataResult
import com.example.thirdtry.model.Good

class SubFragmentViewModel : ViewModel() {
    fun getGoods(token: String): MutableLiveData<BaseDataResult<MutableList<Good>>> =
        RetrofitClient.serviceApi.getGoods("JWT $token")
}