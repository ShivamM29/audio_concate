package com.mytask

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mytask.databinding.SingleItemViewBinding
import com.mytask.databinding.SingleStackItemBinding

class ViewPagerAdapter(private val context: Context, private val images: List<Items>): RecyclerView.Adapter<ViewPagerAdapter.PagerViewHolder>() {
    private val newList = listOf(images.last()) + images + listOf(images.first())

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PagerViewHolder {
        val inflater = LayoutInflater.from(context)
        val binding = SingleStackItemBinding.inflate(inflater, parent, false)
        return PagerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PagerViewHolder, position: Int) {
        holder.binding.ibStack.setImageResource(newList[position].images!!)
    }

    override fun getItemCount(): Int {
        return newList.size
    }

    inner class PagerViewHolder(val binding: SingleStackItemBinding): RecyclerView.ViewHolder(binding.root){
        init {

        }
    }
}