package com.alucard.notes.foundations

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class BaseRecyclerAdapter<T: Any>(protected val dataList: MutableList<T>)
    : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    fun updateList(updatedList: List<T>) {
        dataList.clear()
        dataList.addAll(updatedList)
        notifyDataSetChanged()
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == 0) {
            TYPE_ADD_BUTTON
        } else {
            TYPE_INFO
        }
    }

    override fun getItemCount(): Int = dataList.size + 1

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is AddButtonViewHolder) {
            holder.onBind(Unit)
        } else {
            (holder as BaseViewHolder<T>).onBind(dataList[position - 1])
        }
    }

    abstract class BaseViewHolder<E>(val view: View) : RecyclerView.ViewHolder(view) {
        abstract fun onBind(modelEntity: E)
    }

    abstract class AddButtonViewHolder(view: View): BaseViewHolder<Unit>(view)

    companion object {
        const val TYPE_ADD_BUTTON = 0
        const val TYPE_INFO = 1
    }
}