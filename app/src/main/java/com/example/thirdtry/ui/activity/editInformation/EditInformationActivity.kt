package com.example.thirdtry.ui.activity.editInformation

import android.annotation.SuppressLint
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.thirdtry.R
import kotlinx.android.synthetic.main.activity_edit_information.*
import android.widget.CheckBox
import android.widget.Toast
import android.app.DatePickerDialog
import android.content.Intent
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.thirdtry.model.Information
import com.example.thirdtry.ui.activity.getInformation.GetInformationActivity
import com.example.thirdtry.ui.fragment.scheme.SchemeFragment
import com.example.thirdtry.ui.fragment.user.UserFragment
import kotlinx.android.synthetic.main.user_fragment.*
import java.util.Calendar


class EditInformationActivity : AppCompatActivity() {

    private lateinit var viewModel: EditInformationViewModel
    private lateinit var id: String
    private lateinit var token: String
    private val calendar = Calendar.getInstance()
    private var checkBoxList = mutableListOf<CheckBox>()


    private val mObserver: Observer<Information> by lazy {
        Observer<Information> {
            if (it == null) {
                Toast.makeText(this, "网络开小差了，请稍后", Toast.LENGTH_SHORT).show()
                return@Observer
            } else {
                GetInformationActivity.instance?.finish()

                intent = Intent(this, GetInformationActivity::class.java)
                startActivity(intent)
                Toast.makeText(GetInformationActivity.instance, "修改成功", Toast.LENGTH_SHORT).show()
                UserFragment.instance?.tv_nickname?.text = it.name
                SchemeFragment.instance?.getData()
                val s = getSharedPreferences("loginToken", Context.MODE_PRIVATE)
                val editor = s.edit()
                editor.putString("name", it.name)
                finish()
            }
        }
    }

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_information)
        viewModel = ViewModelProviders.of(this).get(EditInformationViewModel::class.java)
        val s = getSharedPreferences("loginToken", Context.MODE_PRIVATE)
        var year = calendar.get(Calendar.YEAR)
        var month = calendar.get(Calendar.MONTH) + 1
        var day = calendar.get(Calendar.DATE)
        var gender = "male"
        token = s.getString("token", "")!!
        id = s.getString("id", "")!!
        checkBoxList.add(checkbox_one)
        checkBoxList.add(checkbox_two)
        checkBoxList.add(checkbox_three)
        checkBoxList.add(checkbox_four)

        radio_group.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.radio_button_one -> gender = "male"
                R.id.radio_button_two -> gender = "female"
            }
        }
        birthday.setOnClickListener {
            DatePickerDialog(
                this,
                DatePickerDialog.OnDateSetListener { datePicker, i, i1, i2 ->
                    year = i
                    month = i1 + 1
                    day = i2
                    birthday.text = "$year-$month-$day"
                }, year, month, day
            ).show()
        }
        upload.setOnClickListener {
            val stringBuffer = StringBuffer()
            for (c in checkBoxList) {
                if (c.isChecked) {
                    when (c.text){
                        "湿气重"->stringBuffer.append("sqz ")
                        "睡眠质量差"->stringBuffer.append("poor_sleep ")
                        "脱发"->stringBuffer.append("little_hair ")
                        "免疫力低下"->stringBuffer.append("low_dkl ")
                    }
                }
            }
            if (nickname.text.isNullOrBlank()) {
                Toast.makeText(this@EditInformationActivity, "昵称不能为空", Toast.LENGTH_SHORT).show()
            } else if (stringBuffer.isBlank()) {
                Toast.makeText(this@EditInformationActivity, "请选择症状", Toast.LENGTH_SHORT).show()
            } else if (nickname.text!!.length > 13) {
                Toast.makeText(this@EditInformationActivity, "昵称过长", Toast.LENGTH_SHORT).show()
            } else {
                viewModel.editInformation(
                    token = token,
                    Id = id,
                    name = nickname.text.toString(),
                    gender = gender,
                    birthday = birthday.text.toString(),
                    type = stringBuffer.toString().trim()
                ).observe(this, mObserver)
            }

        }

    }
}
