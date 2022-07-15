package com.app.android_clean_architecture_assignment.presentation.character

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.app.android_clean_architecture_assignment.R
import com.app.android_clean_architecture_assignment.databinding.RowCharacterBinding
import com.app.android_clean_architecture_assignment.domain.model.CharacterModel
import com.app.android_clean_architecture_assignment.presentation.common.CharacterDiffUtilCallback

class CharacterAdapter(private val onItemClicked: (character: CharacterModel) -> Unit) :
    RecyclerView.Adapter<CharacterAdapter.CharacterViewHolder>() {

    private var characterList = mutableListOf<CharacterModel>()
    private var diffResult: DiffUtil.DiffResult? = null

    fun setData(newCharacters: MutableList<CharacterModel>) {
        diffResult =
            DiffUtil.calculateDiff(CharacterDiffUtilCallback(characterList, newCharacters))
        characterList.clear()
        characterList.addAll(newCharacters)
        diffResult?.dispatchUpdatesTo(this)
    }

    fun clear() {
        characterList.clear()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val mDeveloperListItemBinding = DataBindingUtil.inflate<RowCharacterBinding>(
            LayoutInflater.from(parent.context),
            R.layout.row_character, parent, false
        )
        return CharacterViewHolder(mDeveloperListItemBinding)
    }

    override fun getItemCount() = characterList.size

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        holder.bind(characterList[position])
    }

    inner class CharacterViewHolder(private var rowCharacterBinding: RowCharacterBinding) :

        RecyclerView.ViewHolder(rowCharacterBinding.root) {
        fun bind(character: CharacterModel) {
            character.apply {
                rowCharacterBinding.tvCharacter.text = name
                Glide.with(itemView.context).load(imageUrl).placeholder(R.drawable.ic_placeholder)
                    .fitCenter()
                    .into(rowCharacterBinding.ivCharacter)
            }
            itemView.setOnClickListener { onItemClicked(characterList[adapterPosition]) }
        }
    }

}