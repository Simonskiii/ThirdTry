package com.example.thirdtry.ui.activity.getInformation

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.thirdtry.R
import com.example.thirdtry.model.Good
import com.example.thirdtry.model.Information
import com.example.thirdtry.ui.activity.articleDetail.ArticleDetialViewModel
import com.example.thirdtry.ui.activity.editInformation.EditInformationActivity
import com.example.thirdtry.ui.activity.register.RegisterActivity
import kotlinx.android.synthetic.main.activity_get_information.*
import kotlinx.android.synthetic.main.activity_get_information.birthday_tv
import kotlinx.android.synthetic.main.activity_get_information.gender_tv

class GetInformationActivity : AppCompatActivity() {
    companion object {
        var instance: GetInformationActivity? = null
    }
    private lateinit var id: String
    private lateinit var token: String
    private lateinit var viewModel: GetInformationViewModel
    private val mObserver: Observer<Information> by lazy {
        Observer<Information> {
            if (it == null) {
                Toast.makeText(this, "网络开小差了，请稍后", Toast.LENGTH_SHORT).show()
                return@Observer
            } else {
                name_tv.text = it.name
                when(it.gender){
                    "male" -> gender_tv.text = "男"
                    "female" -> gender_tv.text = "女"
                }
                birthday_tv.text = it.birthday
                val list = it.typ.split(" ")
                val stringBuffer = StringBuffer()
                for(i in list){
                    when (i){
                        "sqz" -> stringBuffer.append("湿气重" + " ")
                        "poor_sleep" -> stringBuffer.append("睡眠质量差" + " ")
                        "low_dkl" -> stringBuffer.append("抵抗力低下" + " ")
                        "little_hair" -> stringBuffer.append("脱发" + " ")
                    }
                }
                type_tv.text = stringBuffer.trim()
            }
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        instance = this
        setContentView(R.layout.activity_get_information)
        val s = getSharedPreferences("loginToken", Context.MODE_PRIVATE)
        token = s.getString("token", "")!!
        id = s.getString("id","")!!
        viewModel = ViewModelProviders.of(this).get(GetInformationViewModel::class.java)
        viewModel.getInformation(token, id).observe(this,mObserver)
        change.setOnClickListener{
            intent = Intent(this, EditInformationActivity::class.java)
            startActivity(intent)
        }
    }
}
