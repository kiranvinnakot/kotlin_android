package com.example.samplelivedata

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.samplelivedata.databinding.RecylerviewRowBinding

class RecylerviewAdapter : RecyclerView.Adapter<RecylerviewAdapter.myViewHolder>() {
    var items =java.util.ArrayList<RecylerData>()
    fun setDataList(data: ArrayList<RecylerData>) {
        this.items = data
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecylerviewAdapter.myViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = RecylerviewRowBinding.inflate(layoutInflater)
        return myViewHolder(binding)
    }

    override fun onBindViewHolder(holder: myViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class myViewHolder(val binding: RecylerviewRowBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: RecylerData) {
            binding.recylerData = data
            binding.executePendingBindings()
        }
    }

    companion object {
        @JvmStatic
        @BindingAdapter("loadImage")
        fun loadImage(thumbImage: ImageView, url: String) {
            Glide.with(thumbImage)
                .load(url)
                .circleCrop()
                .placeholder(R.drawable.ic_launcher_foreground)
                .error(R.drawable.ic_launcher_foreground)
                .fallback(R.drawable.ic_launcher_foreground)
                .into(thumbImage)
        }

    }
}