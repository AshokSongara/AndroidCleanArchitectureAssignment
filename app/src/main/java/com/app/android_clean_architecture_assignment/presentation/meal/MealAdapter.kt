package com.app.android_clean_architecture_assignment.presentation.meal

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.app.android_clean_architecture_assignment.R
import com.app.android_clean_architecture_assignment.common.inflate
import com.app.android_clean_architecture_assignment.domain.meal.entity.Meal
import kotlinx.android.synthetic.main.row_meal.view.*

class MealAdapter : RecyclerView.Adapter<MealAdapter.RecyclerViewHolder>() {

    private var arrayList = ArrayList<Meal>()

    fun setItems(results: ArrayList<Meal>) {
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

    inner class RecyclerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(meal: Meal) {
            itemView.tvMeal.text = meal.name
            Glide.with(itemView.context)
                .load(meal.mealUrl)
                .into(itemView.ivMeal)
        }
    }
}