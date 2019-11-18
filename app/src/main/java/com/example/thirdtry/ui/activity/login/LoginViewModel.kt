package com.example.thirdtry.ui.activity.login

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.thirdtry.R
import com.example.thirdtry.api.RetrofitClient
import com.example.thirdtry.base.BaseDataResult
import com.example.thirdtry.base.BaseResponseResult

class LoginViewModel : ViewModel() {

    private val _loginForm = MutableLiveData<LoginFormState>()
    val loginFormState: LiveData<LoginFormState> = _loginForm

    private val _loginResult = MutableLiveData<LoginResponseResult>()
    val loginResult: LiveData<LoginResponseResult> = _loginResult

    private val _refreshLoginResult = MutableLiveData<LoginResponseResult>()
    val refreshLoginResult: LiveData<LoginResponseResult> = _refreshLoginResult

    fun loginRequest(username: String, password: String): MutableLiveData<BaseDataResult<LoginModel>>{
        return RetrofitClient.serviceApi.login(username, password)
    }

    fun refreshLoginRequest(token:String): MutableLiveData<BaseDataResult<LoginModel>>{
        return RetrofitClient.serviceApi.refresh_login(token)
    }

    fun getLoginResult(response : BaseDataResult<LoginModel>) {
        // can be launched in a separate asynchronous job
        if (response.success !=null) {
            _loginResult.value = LoginResponseResult(success = response.subjects)
        } else {
            _loginResult.value = LoginResponseResult(error = "登录失败")
        }
    }

    fun getRefreshLoginResult(response : BaseDataResult<LoginModel>) {
        // can be launched in a separate asynchronous job
        if (response.success !=null) {
            _refreshLoginResult.value = LoginResponseResult(success = response.subjects)
        } else {
            _refreshLoginResult.value = LoginResponseResult(error = "登录失败")
        }
    }

    fun loginDataChanged(username: String, password: String) {
        if (!isUserNameValid(username)) {
            _loginForm.value = LoginFormState(usernameError = R.string.invalid_username)
        } else if (!isPasswordValid(password)) {
            _loginForm.value = LoginFormState(passwordError = R.string.invalid_password)
        } else {
            _loginForm.value = LoginFormState(isDataValid = true)
        }
    }

    // A placeholder username validation check
    private fun isUserNameValid(username: String): Boolean {
        return if (username.contains('@')) {
            Patterns.EMAIL_ADDRESS.matcher(username).matches()
        } else {
            username.isNotBlank()
        }
    }

    // A placeholder password validation check
    private fun isPasswordValid(password: String): Boolean {
        return password.length != null
    }
}