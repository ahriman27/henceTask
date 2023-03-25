package com.example.hencetest

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.hencetest.databinding.ItemListBinding

class ListAdapter(var ctx : Context, var list : ArrayList<MarkerModel>) : RecyclerView.Adapter<ListAdapter.ViewHold>()
{
    class ViewHold(var binding : ItemListBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHold {
        return ViewHold(ItemListBinding.inflate(LayoutInflater.from(ctx),parent,false))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHold, position: Int) {
        holder.binding.apply {
            item.text = list[position].label
            delete.setOnClickListener {
                list.removeAt(position)
                (ctx as MainActivity).mapInter.onEvent(position)
                notifyDataSetChanged()
            }
        }
    }

}