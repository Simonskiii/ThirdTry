package com.example.thirdtry.ui.fragment.user

import android.content.DialogInterface
import android.content.Intent
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.thirdtry.R
import com.example.thirdtry.base.BaseFragment
import com.example.thirdtry.ui.activity.articleDetail.ArticleDetialActivity
import com.example.thirdtry.ui.activity.login.LoginActivity
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.android.synthetic.main.user_fragment.*
import org.greenrobot.eventbus.EventBus

class UserFragment : BaseFragment() {
    private lateinit var viewModel: UserViewModel

    override fun getLayoutId(): Int = R.layout.user_fragment

    override fun initView() {
        viewModel = ViewModelProviders.of(this).get(UserViewModel::class.java)
    }

    override fun initViewModel() {
        tv_logout.setOnClickListener {
            MaterialAlertDialogBuilder(it.context)
                .setMessage("是否真的要注销？")
                .setPositiveButton(
                    "确定",
                    DialogInterface.OnClickListener() { dialogInterface: DialogInterface, i: Int ->
                        val sp = activity?.getSharedPreferences("loginToken", 0)
                        val editor = sp?.edit()
                        editor?.remove("token")
                        editor?.apply()
                        val intent = Intent(it.context, LoginActivity::class.java)
                        it.context.startActivity(intent)
                        this.activity?.finish()
                    })
                .setNegativeButton("取消", null)
                .show()
        }
    }



}
