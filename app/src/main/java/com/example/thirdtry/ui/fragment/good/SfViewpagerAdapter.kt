package com.example.thirdtry.ui.fragment.good

import android.annotation.SuppressLint
import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.thirdtry.R
import com.example.thirdtry.ui.fragment.good.subFragment.SubFragment

@SuppressLint("WrongConstant")
class SfViewpagerAdapter(
    private val context: Context,
    fragmentManager: FragmentManager
) : FragmentPagerAdapter(
    fragmentManager,
    BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
) {
    enum class SubFragments(val titleRes: Int) {
        INSTRUCTIONS(R.string.bottom_nav_favorites_title),
        THEME_SUMMARY(R.string.bottom_app_bar_label_title),
        COMPONENTS(R.string.bottom_nav_label_title)
    }

    override fun getCount(): Int = SubFragments.values().size

    private fun getItemType(position: Int): SubFragments {
        return SubFragments.values()[position]
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return context.getString(getItemType(position).titleRes)
    }

    override fun getItem(position: Int): Fragment {
        return when (getItemType(position)) {
            SubFragments.INSTRUCTIONS -> SubFragment.newInstance("第一类")
            SubFragments.THEME_SUMMARY -> SubFragment.newInstance("第二类")
            SubFragments.COMPONENTS -> SubFragment.newInstance("第三类")
        }
    }
}