package com.example.thirdtry.ui.activity.firstPage

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.thirdtry.R
import kotlinx.android.synthetic.main.activity_first_page.*
import android.graphics.Typeface
import com.example.thirdtry.ui.activity.login.LoginActivity
import com.example.thirdtry.ui.activity.register.RegisterActivity


class FirstPageActivity : AppCompatActivity() {
    companion object {
        lateinit var instance: FirstPageActivity
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first_page)
        instance = this
        val type = Typeface.createFromAsset(assets, "MavenPro-Regular.ttf")
        tv_beautiful.typeface = type
        signin.setOnClickListener {
            intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
        signup.setOnClickListener {
            intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }
}
