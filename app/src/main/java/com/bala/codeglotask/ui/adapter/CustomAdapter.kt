package com.bala.codeglotask.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bala.codeglotask.model.HeaderItem
import com.bala.codeglotask.model.RecyclerViewItem
import com.bala.codeglotask.model.SubItem
import com.bala.codeglotask.R


class CustomAdapter(
    private var dogBreedList: List<RecyclerViewItem>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val TYPE_HEADER = 0
    private val TYPE_ITEM = 1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            TYPE_HEADER -> {
                val itemView: View = LayoutInflater.from(parent.context)
                    .inflate(R.layout.header_item, parent, false)
                HeaderViewHolder(itemView)
            }
            TYPE_ITEM -> {
                val itemView: View = LayoutInflater.from(parent.context)
                    .inflate(R.layout.sub_items, parent, false)
                SubItemViewHolder(itemView)
            }
            else
            -> {
                val itemView: View = LayoutInflater.from(parent.context)
                    .inflate(R.layout.sub_items, parent, false)
                return SubItemViewHolder(itemView)
            }
        }
    }


    override fun getItemCount(): Int {
        return dogBreedList.size
    }

    override fun getItemViewType(position: Int): Int {
        return when (dogBreedList[position]) {
            is HeaderItem -> {
                TYPE_HEADER
            }
            is SubItem -> {
                TYPE_ITEM
            }
            else -> TYPE_ITEM
        }
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        if (holder is HeaderViewHolder) {
            val item: RecyclerViewItem = dogBreedList[position]
            holder.headerTextView.text = item.name
        } else if (holder is SubItemViewHolder) {
            val item: RecyclerViewItem = dogBreedList[position]
            holder.subHeaderTextView.text = item.name
        }

    }

    private class HeaderViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var headerTextView: TextView = view.findViewById(R.id.headerTextView)
    }

    private class SubItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var subHeaderTextView: TextView =
            view.findViewById(R.id.subHeaderTextView)
    }
}