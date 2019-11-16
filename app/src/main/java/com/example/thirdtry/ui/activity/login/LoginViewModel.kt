package com.example.thirdtry.ui.activity.login

import android.util.Patterns
import android.view.View
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.example.thirdtry.R
import com.example.thirdtry.api.RetrofitClient
import com.example.thirdtry.model.Article
import kotlinx.android.synthetic.main.article_fragment.*
import kotlinx.coroutines.delay

class LoginViewModel : ViewModel() {

    private val _loginForm = MutableLiveData<LoginFormState>()
    val loginFormState: LiveData<LoginFormState> = _loginForm

    private val _loginResult = MutableLiveData<LoginResult>()
    val loginResult: LiveData<LoginResult> = _loginResult

    fun loginRequest(username: String, password: String): MutableLiveData<LoginModel>{
        return RetrofitClient.serviceApi.login(username, password)
    }

//    val result(username: String, password: String){
//        result = RetrofitClient.serviceApi.login(username, password)
//    }

    fun login1(username: String, password: String) :MutableLiveData<LoginModel>
            = RetrofitClient.serviceApi.login(username, password)

    fun getLoginResult(response : LoginModel) {
        // can be launched in a separate asynchronous job
        if (response.token !=null) {
            _loginResult.value = LoginResult(success = "yes")
        } else {
            _loginResult.value = LoginResult(error = R.string.login_failed)
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