package com.example.thirdtry.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.example.thirdtry.R
import com.example.thirdtry.ui.article.articleFragment
import com.example.thirdtry.ui.scheme.schemeFragment
import com.example.thirdtry.ui.user.userFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T


class MainActivity : AppCompatActivity() {
    private lateinit var viewPager: ViewPager
    private lateinit var bn_view: BottomNavigationView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewPager = findViewById(R.id.view_pager)
        bn_view = findViewById(R.id.b_view)
        bn_view.setOnNavigationItemSelectedListener(BottomNavigationView.OnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.bottom_nav_scheme -> {
                    viewPager.currentItem = 0
                    return@OnNavigationItemSelectedListener true
                }
                R.id.bottom_nav_article -> {
                    viewPager.currentItem = 1
                    return@OnNavigationItemSelectedListener true
                }
                R.id.bottom_nav_user -> {
                    viewPager.currentItem = 2
                    return@OnNavigationItemSelectedListener true
                }
            }
            false
        })
        viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(i: Int, v: Float, i1: Int) {

            }

            override fun onPageSelected(i: Int) {
                //将滑动到的页面对应的 menu 设置为选中状态
                bn_view.getMenu().getItem(i).setChecked(true)
            }

            override fun onPageScrollStateChanged(i: Int) {

            }
        })

        val adapter = MainViewPagerAdapter(this, supportFragmentManager)
        viewPager.adapter = adapter

    }

}
