package com.example.thirdtry.ui.activity.Main

import android.annotation.SuppressLint
import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.fragment.app.FragmentStatePagerAdapter
import com.example.thirdtry.R
import com.example.thirdtry.ui.fragment.article.ArticleFragment
import com.example.thirdtry.ui.fragment.scheme.SchemeFragment
import com.example.thirdtry.ui.fragment.user.UserFragment


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
            MainFragments.SCHEMES -> SchemeFragment()
            MainFragments.ARTICLES -> ArticleFragment()
            MainFragments.USERS -> UserFragment()
        }
    }
}