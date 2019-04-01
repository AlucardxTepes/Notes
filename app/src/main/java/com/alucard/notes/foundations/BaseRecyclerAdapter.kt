package com.alucard.notes.foundations

import android.view.View
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

abstract class BaseRecyclerAdapter<T: Any>(protected val dataList: MutableList<T>)
    : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    fun updateList(updatedList: List<T>) {
        val differences = DiffUtil.calculateDiff(DiffUtilImpl(dataList, updatedList))
        dataList.clear()
        dataList.addAll(updatedList)
        differences.dispatchUpdatesTo(this)
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
            holder.onBind(Unit, position - 1)
        } else {
            (holder as BaseViewHolder<T>).onBind(dataList[position - 1], position - 1)
        }
    }

    abstract class BaseViewHolder<E>(val view: View) : RecyclerView.ViewHolder(view) {
        abstract fun onBind(modelEntity: E, listIndex: Int)
    }

    abstract class AddButtonViewHolder(view: View): BaseViewHolder<Unit>(view)

    companion object {
        const val TYPE_ADD_BUTTON = 0
        const val TYPE_INFO = 1
    }

    class DiffUtilImpl<T>(val oldList: List<T>, val newList: List<T>) : DiffUtil.Callback() {

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean = oldList[oldItemPosition] === newList[newItemPosition]

        override fun getOldListSize(): Int = oldList.size

        override fun getNewListSize(): Int = newList.size

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean = oldList[oldItemPosition] === newList[newItemPosition]

    }
}