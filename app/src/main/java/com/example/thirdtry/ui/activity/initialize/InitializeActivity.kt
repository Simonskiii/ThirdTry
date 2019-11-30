package com.example.thirdtry.ui.activity.initialize

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.thirdtry.R
import com.example.thirdtry.ui.activity.firstPage.FirstPageActivity
import com.example.thirdtry.ui.activity.main.MainActivity
import com.example.thirdtry.utils.NetworkUtils
import kotlinx.android.synthetic.main.activity_login.*

class InitializeActivity : AppCompatActivity() {
    private lateinit var initializeViewModel: InitializeViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_initialize)

        initializeViewModel = ViewModelProviders.of(this, InitializeViewModelFactory())
            .get(InitializeViewModel::class.java)
        loading.visibility = View.VISIBLE
        val s = getSharedPreferences("loginToken", Context.MODE_PRIVATE)
        val token = s.getString("token", "")
        //从来没登录过或者注销以后
        if(token?.length==0){
            intent = Intent(this, FirstPageActivity::class.java)
            startActivity(intent)
            finish()
        }
        else{
            val a = NetworkUtils.isNetworkConnected(this)
            if (!a){//有token但是无网络连接，直接进入main
                Toast.makeText(this@InitializeActivity, "无网络连接", Toast.LENGTH_SHORT)
                    .show()
                intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
            else{//有token也有网络，刷新token
                initializeViewModel.refreshLoginRequest(token!!)
                    .observe(this@InitializeActivity, Observer {
                        if (it == null) {//网络问题，与服务器之间的通信出现问题
                            Toast.makeText(this@InitializeActivity, "网络开小差了，请稍后", Toast.LENGTH_SHORT)
                                .show()
                            loading.visibility = View.GONE
                            intent = Intent(this, MainActivity::class.java)
                            startActivity(intent)
                            finish()
                            return@Observer
                        }
                        else {//成功连接，刷新token
                            initializeViewModel.getRefreshLoginResult(it)
                        }
                    })
                //观察刷新成功与否，并做出反应
                initializeViewModel.refreshLoginResult.observe(this@InitializeActivity, Observer { it1 ->
                    val refreshLoginResult = it1 ?: return@Observer
                    loading.visibility = View.GONE
                    val sp = getSharedPreferences("loginToken", 0)
                    val editor = sp.edit()
                    if (refreshLoginResult.error != null) {//token过期，返回到login界面
                        editor.remove("token")
                        editor.apply()
                        Toast.makeText(this@InitializeActivity, "登录过期，请重新登录", Toast.LENGTH_SHORT)
                            .show()
                        intent = Intent(this, FirstPageActivity::class.java)
                        startActivity(intent)
                        finish()
                    }
                    if (refreshLoginResult.success != null) {//token成功刷新，进入main
                        editor.remove("token")
                        editor.putString("token", refreshLoginResult.success.token)
                        editor.remove("name")
                        editor.putString("name", refreshLoginResult.success.name)
                        editor.apply()
                        intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)
                        finish()
                    }
                    setResult(Activity.RESULT_OK)
                    //Complete and destroy login activity once successful
                })
            }
        }
    }
}
