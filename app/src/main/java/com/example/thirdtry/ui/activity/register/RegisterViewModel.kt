package com.example.thirdtry.ui.activity.register

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.thirdtry.R
import com.example.thirdtry.api.RetrofitClient
import com.example.thirdtry.base.BaseDataResult
import com.example.thirdtry.base.BaseResponseResult
import com.example.thirdtry.base.BaseResult
import com.example.thirdtry.utils.Validator

class RegisterViewModel : ViewModel() {
    private val _registerForm = MutableLiveData<RegisterFormState>()
    val registerFormState: LiveData<RegisterFormState> = _registerForm

    private val _emailForm = MutableLiveData<EmailFormState>()
    val emailFormState: LiveData<EmailFormState> = _emailForm

    private val _registerResult = MutableLiveData<BaseResponseResult>()
    val registerResult: LiveData<BaseResponseResult> = _registerResult

    private val _verifyResult = MutableLiveData<BaseResponseResult>()
    val verifyResult: LiveData<BaseResponseResult> = _verifyResult

    fun verifyRequest(email: String): MutableLiveData<BaseResult> {
        return RetrofitClient.serviceApi.verify(email)
    }

    fun registerRequest(
        name: String,
        password: String,
        email: String,
        code: String
    ): MutableLiveData<BaseResult> {
        return RetrofitClient.serviceApi.register(name, password, email, code)
    }

    fun registerDataChanged(name: String, password: String, email: String, code: String) {
        if (!isUserNameValid(name)) {
            _registerForm.value = RegisterFormState(usernameError = R.string.invalid_username)
        } else if (!isPasswordValid(password)) {
            _registerForm.value = RegisterFormState(passwordError = R.string.invalid_password)
        } else if (!isEmailValid(email)) {
            _registerForm.value = RegisterFormState(emailError = R.string.invalid_email)
        } else if (!isCodeValid(code)) {
            _registerForm.value = RegisterFormState(codeError = R.string.invalid_code)
        } else {
            _registerForm.value = RegisterFormState(isDataValid = true)
        }
    }

    fun emailDataChanged(email: String) {
        if (isEmailValid(email)) {
            _emailForm.value = EmailFormState(isEmailValid = true)
        }
    }

    private fun isEmailValid(email: String): Boolean {
        return Validator.isEmail(email)
    }

    private fun isUserNameValid(username: String): Boolean {
        return username.length < 20
    }

    // A placeholder password validation check
    private fun isPasswordValid(password: String): Boolean {
        return password.length > 7
    }

    private fun isCodeValid(code: String): Boolean {
        return code.length > 5
    }

    fun getVerifyResult(response: BaseResult) {
        // can be launched in a separate asynchronous job
        if (response.success != null) {
            _verifyResult.value = BaseResponseResult(success = response.success)
        } else {
            _verifyResult.value = BaseResponseResult(error = response.error)
        }
    }

    fun getRegisterResult(response: BaseResult) {
        // can be launched in a separate asynchronous job
        if (response.success != null) {
            _registerResult.value = BaseResponseResult(success = "yes")
        } else {
            _registerResult.value = BaseResponseResult(error = response.error)
        }
    }
}