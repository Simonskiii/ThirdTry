package com.example.thirdtry.ui.fragment.scheme

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.MutableLiveData
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
import java.util.stream.IntStream

class SchemeFragment : BaseFragment() {
    lateinit var viewModel: SchemeViewModel
    private lateinit var binding : SchemeFragmentNewBinding
    private val mObserver: Observer<BaseDataResult<Scheme>> by lazy {
        Observer<BaseDataResult<Scheme>> {
            if (it == null){
                Toast.makeText(activity, "无网络连接", Toast.LENGTH_SHORT).show()
                return@Observer
            }
            else{

                val stringBuffer = StringBuffer()
                val length = it.subjects.todo.size
                for(i in 0 until length){
                    when (it.subjects.todo[i].typ){
                        "sqz" -> stringBuffer.append("湿气重: ")
                        "poor_sleep" -> stringBuffer.append("睡眠质量差: ")
                        "low_dkl" -> stringBuffer.append("抵抗力低下: ")
                        "little_hair" -> stringBuffer.append("脱发: ")
                    }
                    stringBuffer.append(it.subjects.todo[i].content)
                    if (i!= length -1 ){
                        stringBuffer.append("\n")
                    }
                }
                binding.data = stringBuffer
            }
        }
    }
    override fun getLayoutId(): Int = R.layout.scheme_fragment_new


    companion object {
        fun newInstance() = SchemeFragment()
        var instance : SchemeFragment? = null
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
        instance = this
        tab_layout.setupWithViewPager(sub_view_pager)
        val adapter = SchemeViewPagerAdapter(context!!, this.childFragmentManager)
        sub_view_pager.offscreenPageLimit = 2
        sub_view_pager.adapter = adapter
    }

    override fun initViewModel() {
        viewModel = ViewModelProviders.of(this.activity!!).get(SchemeViewModel::class.java)
        viewModel.getScheme(token).observe(this, mObserver)
    }
    fun getData() {
        viewModel.scheme = viewModel.getScheme(token)
        viewModel.scheme?.observe(this, mObserver)
    }
}
