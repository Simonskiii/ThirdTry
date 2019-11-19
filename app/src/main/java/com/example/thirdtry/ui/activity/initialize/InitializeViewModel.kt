package com.example.thirdtry.ui.activity.initialize

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.thirdtry.api.RetrofitClient
import com.example.thirdtry.base.BaseDataResult
import com.example.thirdtry.ui.activity.login.LoginModel
import com.example.thirdtry.ui.activity.login.LoginResponseResult

class InitializeViewModel: ViewModel() {

    private val _refreshLoginResult = MutableLiveData<LoginResponseResult>()
    val refreshLoginResult: LiveData<LoginResponseResult> = _refreshLoginResult

    fun refreshLoginRequest(token:String): MutableLiveData<BaseDataResult<LoginModel>>{
        return RetrofitClient.serviceApi.refresh_login(token)
    }

    fun getRefreshLoginResult(response : BaseDataResult<LoginModel>) {
        // can be launched in a separate asynchronous job
        if (response.success !=null) {
            _refreshLoginResult.value = LoginResponseResult(success = response.subjects)
        } else {
            _refreshLoginResult.value = LoginResponseResult(error = "登录失败")
        }
    }
}