package com.inis.acaziatest.ui.base


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.inis.acaziatest.BR

abstract class BaseRecyclerViewAdapter<T, ItemDataBinding : ViewDataBinding>(itemCallback: DiffUtil.ItemCallback<T>) :
    ListAdapter<T, BaseViewHolder<ItemDataBinding>>(itemCallback) {
    abstract val layoutRes: Int
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<ItemDataBinding> =
        BaseViewHolder(createItemBinding(parent).apply {
            bindFirstTime(this)
        })

    open val itemVariable = BR.item

    open fun bindFirstTime(itemBinding: ItemDataBinding) {}

    private fun createItemBinding(parent: ViewGroup): ItemDataBinding =
        DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            layoutRes, parent, false
        )

    override fun submitList(list: List<T>?) {
        super.submitList(ArrayList<T>(list ?: listOf()))
    }

    override fun onBindViewHolder(holder: BaseViewHolder<ItemDataBinding>, position: Int) {
        holder.apply {
            itemBinding.setVariable(itemVariable, getItem(adapterPosition))
            bindData(itemBinding, adapterPosition)
        }
    }

    open fun bindData(itemBinding: ItemDataBinding, position: Int) {

    }
}