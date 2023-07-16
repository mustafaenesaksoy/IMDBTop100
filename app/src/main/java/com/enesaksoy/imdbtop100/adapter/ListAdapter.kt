package com.enesaksoy.imdbtop100.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.enesaksoy.imdbtop100.databinding.ListRowBinding
import com.enesaksoy.imdbtop100.response.Result
import javax.inject.Inject

class ListAdapter @Inject constructor(private val glide : RequestManager): RecyclerView.Adapter<ListAdapter.ListHolder>() {

    private var OnItemClicked : ((Result) -> Unit)? = null
    private val diffutil = object : DiffUtil.ItemCallback<Result>() {
        override fun areItemsTheSame(oldItem: Result, newItem: Result): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Result, newItem: Result): Boolean {
            return oldItem == newItem
        }
    }

    private val recyclerList = AsyncListDiffer(this,diffutil)
    var movies : List<Result>
    get() = recyclerList.currentList
    set(value) = recyclerList.submitList(value)
    class ListHolder(val binding : ListRowBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListHolder {
        val binding = ListRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ListHolder(binding)
    }

    override fun getItemCount(): Int {
        return movies.size
    }
    fun onItemClickListener(listener : (Result) -> Unit){
        OnItemClicked = listener
    }
    override fun onBindViewHolder(holder: ListHolder, position: Int) {
        val movie = movies.get(position)
        glide.load(movie.image).into(holder.binding.listRowImage)
        holder.binding.ListRowId.text = movie.rank.toString()
        holder.binding.listRowName.text = movie.title
        holder.itemView.setOnClickListener {
            OnItemClicked?.let {
                it(movie)
            }
        }
    }
}