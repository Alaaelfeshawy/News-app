package com.example.newsapp.ui.base

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

class BaseAdapter<T,Y:ViewBinding>(private val design :Int ,
                                   var itemClickListener :((T)->Unit)?=null,
                                   val viewHolderFactor :(Y)-> BaseViewHolder<Y,T>) : RecyclerView.Adapter<BaseViewHolder<Y, T>>() {
    private lateinit var binding:ViewBinding
    var list: List<T> = ArrayList()
    var x :String ?=null

    fun setDataList(list: List<T> , x:String ?= null){
        this.list = list
        this.x = x
        notifyDataSetChanged()
    }
    fun setDataList(list: ArrayList<T> , x:String ?= null){
        this.list = list
        this.x = x
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<Y, T> {
        binding = DataBindingUtil.inflate(LayoutInflater.from(parent.context),design,parent,false)
        return viewHolderFactor(binding as Y)
    }

    override fun onBindViewHolder(holder: BaseViewHolder<Y, T>, position: Int) {
        list[position]?.let { holder.onBind(position, it , x) }
        holder.itemView.setOnClickListener {
            list[position]?.let { it1 -> itemClickListener?.invoke(it1) }
        }
    }

    override fun getItemCount(): Int {
        return list?.size ?: 0
    }
}