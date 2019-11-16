package com.example.thirdtry.ui.activity.login

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import com.example.thirdtry.R
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.lifecycle.Observer
import com.example.thirdtry.ui.activity.main.MainActivity
import kotlinx.android.synthetic.main.activity_login.*


class LoginActivity : AppCompatActivity() {
    private lateinit var loginViewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        //初始化loginViewModel
        loginViewModel = ViewModelProviders.of(this, LoginViewModelFactory())
            .get(LoginViewModel::class.java)

        //app中editText的账号密码规范检查
        loginViewModel.loginFormState.observe(this@LoginActivity, Observer {
            val loginState = it ?: return@Observer
            // disable login button unless both username / password is valid
            login.isEnabled = loginState.isDataValid

            if (loginState.usernameError != null) {
                username.error = getString(loginState.usernameError)
            }
            if (loginState.passwordError != null) {
                password.error = getString(loginState.passwordError)
            }
        })

        //username输入时，实时将值传入，以检测是否规范
        username.afterTextChanged {
            loginViewModel.loginDataChanged(
                username.text.toString(),
                password.text.toString()
            )
        }
        password.apply {
            //password输入时，实时将值传入，以检测是否规范
            afterTextChanged {
                loginViewModel.loginDataChanged(
                    username.text.toString(),
                    password.text.toString()
                )
            }
            //android中软键盘的回车操作绑定
            setOnEditorActionListener { _, actionId, _ ->
                when (actionId) {
                    EditorInfo.IME_ACTION_DONE ->
                        //发出post请求并对response进行观察
                        loginViewModel.loginRequest(username.text.toString(),password.text.toString())
                            .observe(this@LoginActivity, Observer{
                                if (it == null){
                                    Toast.makeText(this@LoginActivity, "无网络连接", Toast.LENGTH_SHORT).show()
                                    return@Observer
                                }
                                else{
                                    loginViewModel.getLoginResult(it)
                                }
                            })
                }
                false
            }
            //对登录按钮进行绑定
            login.setOnClickListener {
                loading.visibility = View.VISIBLE
                //发出post请求并对response进行观察
                loginViewModel.loginRequest(username.text.toString(),password.text.toString())
                    .observe(this@LoginActivity, Observer{
                        if (it == null){
                            Toast.makeText(this@LoginActivity, "无网络连接", Toast.LENGTH_SHORT).show()
                            return@Observer
                        }
                        else{
                            loginViewModel.getLoginResult(it)
                        }
                    })
            }
        }
        //观察登录成功与否，并做出反应
        loginViewModel.loginResult.observe(this@LoginActivity, Observer {
            val loginResult = it ?: return@Observer
            loading.visibility = View.GONE
            if (loginResult.error != null) {
                showLoginFailed(loginResult.error)
            }
            if (loginResult.success != null) {
                updateUiWithUser()
                finish()
            }
            setResult(Activity.RESULT_OK)

            //Complete and destroy login activity once successful

        })
    }

    //登录成功后的反应
    private fun updateUiWithUser() {
        val welcome = getString(R.string.welcome)
        Toast.makeText(
            applicationContext,
            welcome,
            Toast.LENGTH_LONG
        ).show()
        intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    //登录失败后的反应
    private fun showLoginFailed(@StringRes errorString: Int) {
        Toast.makeText(applicationContext, errorString, Toast.LENGTH_SHORT).show()
    }
}
fun EditText.afterTextChanged(afterTextChanged: (String) -> Unit) {
    this.addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(editable: Editable?) {
            afterTextChanged.invoke(editable.toString())
        }

        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
    })
}

