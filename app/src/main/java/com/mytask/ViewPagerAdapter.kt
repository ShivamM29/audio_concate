package com.mytask

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mytask.databinding.SingleItemViewBinding
import com.mytask.databinding.SingleStackItemBinding

class ViewPagerAdapter(private val context: Context, private val itemsList: List<Items>, val viewPagerUpperView: View, val onClickEvent: OnClickEvent): RecyclerView.Adapter<ViewPagerAdapter.PagerViewHolder>() {
    private val newList = listOf(itemsList.last()) + itemsList + listOf(itemsList.first())

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
            binding.ibStack.setOnClickListener{
                val audio = newList[adapterPosition].audio
                onClickEvent.onItemClicked(audio, viewPagerUpperView)
            }
        }
    }

    interface OnClickEvent{
        fun onItemClicked(audio: String, view: View)
    }
}