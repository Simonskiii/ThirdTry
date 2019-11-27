package com.example.thirdtry.ui.fragment.scheme

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.thirdtry.R
import com.example.thirdtry.base.BaseDataResult
import com.example.thirdtry.base.BaseFragment
import com.example.thirdtry.databinding.SchemeFragmentBinding
import com.example.thirdtry.databinding.SchemeFragmentNewBinding
import com.example.thirdtry.model.Commodity
import com.example.thirdtry.model.Scheme
import com.example.thirdtry.model.Tip
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.scheme_fragment.*
import kotlinx.android.synthetic.main.scheme_fragment_new.*

class SchemeFragment : BaseFragment() {
    private lateinit var viewModel: SchemeViewModel
    private lateinit var binding : SchemeFragmentNewBinding
    private val mObserver: Observer<BaseDataResult<Scheme>> by lazy {
        Observer<BaseDataResult<Scheme>> {
            if (it == null){
                Toast.makeText(activity, "无网络连接", Toast.LENGTH_SHORT).show()
                return@Observer
            }
            else{
                binding.data = it.subjects.todo[0]
            }
        }
    }
    override fun getLayoutId(): Int = R.layout.scheme_fragment


    companion object {
        fun newInstance() = SchemeFragment()
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val s = activity?.getSharedPreferences("loginToken", 0)
        token = s?.getString("token", "").toString()
        binding = DataBindingUtil.inflate<SchemeFragmentNewBinding>(inflater, R.layout.scheme_fragment_new,container,false)
        return binding.root
    }
    override fun initView() {
        tab_layout.setupWithViewPager(sub_view_pager)
        val adapter = SchemeViewPagerAdapter(context!!, this.childFragmentManager)
        sub_view_pager.offscreenPageLimit = 2
        sub_view_pager.adapter = adapter
    }

    override fun initViewModel() {
        viewModel = ViewModelProviders.of(this.activity!!).get(SchemeViewModel::class.java)
        viewModel.scheme = viewModel.getScheme(token)
        viewModel.scheme?.observe(this, mObserver)
    }
}
