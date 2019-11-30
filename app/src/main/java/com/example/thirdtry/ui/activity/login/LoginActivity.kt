package com.example.thirdtry.ui.activity.login

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import com.example.thirdtry.R
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.lifecycle.Observer
import com.example.thirdtry.ui.activity.firstPage.FirstPageActivity
import com.example.thirdtry.ui.activity.main.MainActivity
import com.example.thirdtry.ui.activity.register.RegisterActivity
import com.example.thirdtry.utils.afterTextChanged
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_login.loading
import kotlinx.android.synthetic.main.activity_login.password
import kotlinx.android.synthetic.main.activity_login.username


class LoginActivity : AppCompatActivity() {
    private lateinit var loginViewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        
        //初始化loginViewModel
        loginViewModel = ViewModelProviders.of(this, LoginViewModelFactory())
            .get(LoginViewModel::class.java)
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
                        loginViewModel.loginRequest(
                            username.text.toString(),
                            password.text.toString()
                        )
                            .observe(this@LoginActivity, Observer {
                                if (it == null) {//因为服务器问题，没有返回值
                                    Toast.makeText(this@LoginActivity, "网络开小差了，请稍后", Toast.LENGTH_SHORT)
                                        .show()
                                    loading.visibility = View.GONE
                                    return@Observer
                                } else {
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
                loginViewModel.loginRequest(username.text.toString(), password.text.toString())
                    .observe(this@LoginActivity, Observer {
                        if (it == null) {//因为服务器问题，没有返回值
                            Toast.makeText(this@LoginActivity, "网络开小差了，请稍后", Toast.LENGTH_LONG).show()
                            loading.visibility = View.GONE
                            return@Observer
                        } else {
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
                val sp = getSharedPreferences("loginToken", 0)
                val editor = sp.edit()
                loginResult.success.name?.let { it1 ->
                    updateUiWithUser(it1)
                    editor.putString("name", it1)
                }
                editor.putString("token", loginResult.success.token)
                editor.putString("id", loginResult.success.id)
                editor.apply()
                if (FirstPageActivity.instance != null){
                    FirstPageActivity.instance.finish()
                }
                finish()
            }
            setResult(Activity.RESULT_OK)

            //Complete and destroy login activity once successful

        })

    }

    //登录成功后的反应
    private fun updateUiWithUser(name: String) {
        val welcome = getString(R.string.welcome)
        Toast.makeText(
            applicationContext,
            "$welcome,$name",
            Toast.LENGTH_LONG
        ).show()
        intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    //登录失败后的反应
    private fun showLoginFailed(errorString: String) {
        Toast.makeText(applicationContext, errorString, Toast.LENGTH_SHORT).show()
    }
}


