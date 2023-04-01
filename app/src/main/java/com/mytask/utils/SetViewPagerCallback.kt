package com.mytask.utils

import androidx.viewpager2.widget.ViewPager2
import com.mytask.Items
import com.mytask.databinding.ActivityMainBinding

class SetViewPagerCallback(val viewPager: ViewPager2): ViewPager2.OnPageChangeCallback() {

    fun setPagerCallback(binding: ActivityMainBinding, viewPager: ViewPager2, listSize: Int){
        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
            override fun onPageScrollStateChanged(state: Int) {
                super.onPageScrollStateChanged(state)
                when(viewPager.currentItem){
                    listSize - 1 -> viewPager.setCurrentItem(1, false)
                    0 -> viewPager.setCurrentItem(listSize-2, false)
                }
            }

            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)

            }
        })
    }

//    override fun onPageScrollStateChanged(state: Int) {
//        super.onPageScrollStateChanged(state)
//    }
}