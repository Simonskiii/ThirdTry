package com.example.thirdtry.ui.activity.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager.widget.ViewPager
import com.example.thirdtry.R
import com.example.thirdtry.ui.activity.searchActivity.SearchActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        b_view.setOnNavigationItemSelectedListener(BottomNavigationView.OnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.bottom_nav_scheme -> {
                    view_pager.currentItem = 0
                    return@OnNavigationItemSelectedListener true
                }
                R.id.bottom_nav_article -> {
                    view_pager.currentItem = 1
                    return@OnNavigationItemSelectedListener true
                }
                R.id.bottom_nav_user -> {
                    view_pager.currentItem = 2
                    return@OnNavigationItemSelectedListener true
                }
            }
            false
        })
        view_pager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(i: Int, v: Float, i1: Int) {}

            override fun onPageSelected(i: Int) {
                //将滑动到的页面对应的 menu 设置为选中状态
                b_view.menu.getItem(i).isChecked = true
            }
            override fun onPageScrollStateChanged(i: Int) {}
        })

        val adapter = MainViewPagerAdapter(this, supportFragmentManager)
        view_pager.adapter = adapter


        iv_search.setOnClickListener { openSearchActivity() }
    }
    private fun openSearchActivity() {
            startActivity(Intent(this, SearchActivity::class.java))
    }
}
