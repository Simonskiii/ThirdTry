package com.example.thirdtry.ui.fragment.scheme

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.thirdtry.R
import com.example.thirdtry.ui.fragment.scheme.subFragment.CommoditiesFragment
import com.example.thirdtry.ui.fragment.scheme.subFragment.TipsFragment

class SchemeViewPagerAdapter(
    private val context: Context,
    fragmentManager: FragmentManager
) : FragmentPagerAdapter(
    fragmentManager,
    BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
) {
    enum class SubFragments(val titleRes: String) {
        Tips("日常方案"),
        Commodities("食物方案"),
    }
    override fun getCount(): Int = SubFragments.values().size

    private fun getItemType(position: Int): SubFragments {
        return SubFragments.values()[position]
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return getItemType(position).titleRes
    }

    override fun getItem(position: Int): Fragment {
        return when (getItemType(position)) {
            SubFragments.Tips -> TipsFragment()
            SubFragments.Commodities -> CommoditiesFragment()
        }
    }
}