package com.francisco.task.ui.adapter

import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import java.util.ArrayList

class ViewPageAdapter(fragmentActivity: FragmentActivity) :
    FragmentStateAdapter(fragmentActivity){

        private val fragmentList: MutableList<Fragment> = ArrayList()
        private val titleList: MutableList<Int> = ArrayList()

    fun getTitle(position: Int): Int{
        return titleList[position]
    }

    fun addFragment(fragment: Fragment, title: Int){
        fragmentList.add(fragment)
        titleList.add(title)
    }

    override fun createFragment(position: Int): Fragment {
        return fragmentList[position]
    }

    override fun getItemCount(): Int {
        return fragmentList.size
    }

    }
