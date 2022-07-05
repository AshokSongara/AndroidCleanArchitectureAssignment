package com.app.android_clean_architecture_assignment.presentation.character

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.app.android_clean_architecture_assignment.R
import com.app.android_clean_architecture_assignment.common.inflate
import com.app.android_clean_architecture_assignment.domain.model.CharacterModel
import kotlinx.android.synthetic.main.row_character.view.*

class CharacterAdapter(private val onItemClicked: (character: CharacterModel) -> Unit) :
    RecyclerView.Adapter<CharacterAdapter.RecyclerViewHolder>() {

    private var arrayList = ArrayList<CharacterModel>()

    fun setItems(results: ArrayList<CharacterModel>) {
        arrayList.clear()
        arrayList.addAll(results)
        notifyDataSetChanged()
    }

    fun clear(){
        arrayList.clear()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        return RecyclerViewHolder(parent.inflate(R.layout.row_character))
    }

    override fun getItemCount() = arrayList.size

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        holder.bind(arrayList[position])
    }

    inner class RecyclerViewHolder(itemView: View) :

        RecyclerView.ViewHolder(itemView) {
        fun bind(character: CharacterModel) {

            character.apply {
                itemView.tvCharacter.text = name
                Glide.with(itemView.context).load(imageUrl).placeholder(R.drawable.ic_placeholder)
                    .fitCenter()
                    .into(itemView.ivCharacter)
            }
            itemView.setOnClickListener { onItemClicked(arrayList[adapterPosition]) }
        }
    }
}