package com.app.android_clean_architecture_assignment.presentation.mealDetail

import androidx.navigation.fragment.navArgs
import com.app.android_clean_architecture_assignment.R
import com.app.android_clean_architecture_assignment.presentation.common.base.BaseFragment
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fragment_meal_detail.*

class MealDetailFragment : BaseFragment() {

    private val args by navArgs<MealDetailFragmentArgs>()

    override fun getContentResource() = R.layout.fragment_meal_detail

    override fun initViews() {
        super.initViews()
        Glide.with(requireContext())
            .load(args.meal)
            .into(ivMeal)

    }
}