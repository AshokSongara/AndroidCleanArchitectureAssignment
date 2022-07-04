package com.app.android_clean_architecture_assignment.presentation.meal

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.app.android_clean_architecture_assignment.R
import com.app.android_clean_architecture_assignment.common.inflate
import com.app.android_clean_architecture_assignment.domain.model.MealModel
import kotlinx.android.synthetic.main.row_meal.view.*

class MealAdapter(private val onItemClicked: (meal: MealModel) -> Unit) :
    RecyclerView.Adapter<MealAdapter.RecyclerViewHolder>() {

    private var arrayList = ArrayList<MealModel>()

    fun setItems(results: ArrayList<MealModel>) {
        arrayList.clear()
        arrayList.addAll(results)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        return RecyclerViewHolder(parent.inflate(R.layout.row_meal))
    }

    override fun getItemCount() = arrayList.size

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        holder.bind(arrayList[position])
    }

    inner class RecyclerViewHolder(itemView: View) :

        RecyclerView.ViewHolder(itemView) {
        fun bind(meal: MealModel) {

            meal.apply {
                itemView.tvMeal.text = name
                Glide.with(itemView.context).load(mealUrl).into(itemView.ivMeal)
            }
            itemView.setOnClickListener { onItemClicked(arrayList[adapterPosition]) }
        }
    }
}