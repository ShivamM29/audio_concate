package com.mytask

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.mytask.databinding.SingleItemViewBinding

class MainRecyclerAdapter(private val context: Context, private val itemList: List<List<Items>>): RecyclerView.Adapter<MainRecyclerAdapter.MainViewHolder>() {
    var pagerAdapter: ViewPagerAdapter? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val inflater = LayoutInflater.from(context)
        val binding = SingleItemViewBinding.inflate(inflater, parent, false)
        return MainViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val images = itemList[position]

//        pagerAdapter = ViewPagerAdapter(context, images)
//        holder.binding.viewPager.adapter = pagerAdapter

        holder.binding.viewPager.currentItem = 1

        onPageChangeCallback(holder.binding.viewPager, images.size + 2)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    inner class MainViewHolder(val binding: SingleItemViewBinding): RecyclerView.ViewHolder(binding.root)

    private fun onPageChangeCallback(viewPager: ViewPager2, listSize: Int){
        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
            override fun onPageScrollStateChanged(state: Int) {
                super.onPageScrollStateChanged(state)
                when(viewPager.currentItem){
                    listSize - 1 -> viewPager.setCurrentItem(1, false)
                    0 -> viewPager.setCurrentItem(listSize-2, false)
                }
            }
        })

    }
}