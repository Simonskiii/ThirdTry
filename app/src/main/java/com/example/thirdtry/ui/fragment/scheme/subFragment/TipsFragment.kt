package com.example.thirdtry.ui.fragment.scheme.subFragment

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.os.Handler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.thirdtry.R
import com.example.thirdtry.base.BaseDataResult
import com.example.thirdtry.base.BaseFragment
import com.example.thirdtry.model.Scheme
import com.example.thirdtry.model.Tip
import com.example.thirdtry.ui.fragment.scheme.SchemeViewModel
import com.example.thirdtry.ui.fragment.scheme.TipBindingAdapter
import kotlinx.android.synthetic.main.sub_fragment_fragment.*

class TipsFragment : BaseFragment() {
    private lateinit var viewModel: SchemeViewModel
    override fun getLayoutId(): Int = R.layout.sub_fragment_fragment
    private var tips = mutableListOf<Tip>()
    private val tipAdapter: TipBindingAdapter by lazy {
        TipBindingAdapter(tips)
    }
    private val mObserver: Observer<BaseDataResult<Scheme>> by lazy {
        Observer<BaseDataResult<Scheme>> {
            if (it == null){
                Toast.makeText(activity, "无网络连接", Toast.LENGTH_SHORT).show()
                return@Observer
            }
            else{
                if (tips.isEmpty()) {
                    this.tips.addAll(it.subjects.tips)
                    tipAdapter.notifyDataSetChanged()
                }
                else{
                    this.tips.clear()
                    this.tips.addAll(it.subjects.tips)
                    tipAdapter.notifyDataSetChanged()
                }
            }
        }
    }

    override fun initView() {
        line_recy_view.layoutManager = LinearLayoutManager(this.context)
        line_recy_view.adapter = tipAdapter
        SRL.setOnRefreshListener {
            viewModel.getScheme(token).observe(this, mObserver)
            //下拉刷新图标持续时间
            Handler().postDelayed({
                if (SRL.isRefreshing) {
                    SRL.isRefreshing = false
                }
            }, 750)
        }
    }

    override fun initViewModel() {
        viewModel = ViewModelProviders.of(this.activity!!).get(SchemeViewModel::class.java)
        viewModel.getScheme(token).observe(this, mObserver)
    }

}
