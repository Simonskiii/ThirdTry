package com.example.thirdtry.ui

import android.annotation.SuppressLint
import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.fragment.app.FragmentStatePagerAdapter
import com.example.thirdtry.R
import com.example.thirdtry.ui.article.articleFragment
import com.example.thirdtry.ui.scheme.schemeFragment
import com.example.thirdtry.ui.user.userFragment


/**
 * View pager to show all tabbed destinations - Instructions, Theme Summary and Components.
 */
@SuppressLint("WrongConstant")
class MainViewPagerAdapter(
    private val context: Context,
    fragmentManager: FragmentManager
) : FragmentPagerAdapter(fragmentManager, FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    enum class MainFragments(val titleRes: Int) {
        SCHEMES(R.string.nav_schemes),
        ARTICLES(R.string.nav_articles),
        USERS(R.string.nav_users)
    }

    override fun getCount(): Int = MainFragments.values().size

    private fun getItemType(position: Int): MainFragments {
        return MainFragments.values()[position]
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return context.getString(getItemType(position).titleRes)
    }

    override fun getItem(position: Int): Fragment {
        return when (getItemType(position)) {
            MainFragments.SCHEMES -> schemeFragment()
            MainFragments.ARTICLES -> articleFragment()
            MainFragments.USERS -> userFragment()
        }
    }
}