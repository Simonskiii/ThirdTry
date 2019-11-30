package com.example.thirdtry.ui.fragment.good

import com.example.thirdtry.R
import com.example.thirdtry.base.BaseFragment
import kotlinx.android.synthetic.main.good_fragment.*

class GoodFragment : BaseFragment() {

    override fun getLayoutId(): Int = R.layout.good_fragment

    override fun initView() {
        tab_layout.setupWithViewPager(sub_view_pager)
        val adapter = SfViewpagerAdapter(context!!, this.childFragmentManager)
        sub_view_pager.offscreenPageLimit = 3
        sub_view_pager.adapter = adapter
    }

    override fun initViewModel() {}


}
