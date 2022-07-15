package com.app.android_clean_architecture_assignment.presentation.common

import androidx.recyclerview.widget.DiffUtil
import com.app.android_clean_architecture_assignment.domain.model.CharacterModel

class CharacterDiffUtilCallback(
    private val oldList: MutableList<CharacterModel>,
    private val newList: MutableList<CharacterModel>
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return when {
            oldList[oldItemPosition].id == newList[newItemPosition].id -> true
            oldList[oldItemPosition].name == newList[newItemPosition].name -> true
            else -> false
        }
    }

    override fun getChangePayload(oldItemPosition: Int, newItemPosition: Int): Any? {
        return super.getChangePayload(oldItemPosition, newItemPosition)
    }

}