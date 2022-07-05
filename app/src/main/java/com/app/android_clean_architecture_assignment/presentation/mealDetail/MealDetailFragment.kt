package com.app.android_clean_architecture_assignment.presentation.mealDetail

import android.widget.Toast
import androidx.navigation.fragment.navArgs
import com.app.android_clean_architecture_assignment.R
import com.app.android_clean_architecture_assignment.common.initViewModel
import com.app.android_clean_architecture_assignment.presentation.common.base.BaseViewModelFragment
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_meal_detail.*

@AndroidEntryPoint
class MealDetailFragment : BaseViewModelFragment<MealDetailsViewModel>() {

    private val args by navArgs<MealDetailFragmentArgs>()

    override fun getContentResource() = R.layout.fragment_meal_detail

    override fun getClassName(): String {
        return this::class.java.simpleName
    }

    override fun buildViewModel() = initViewModel<MealDetailsViewModel>()

    override fun initViews() {
        super.initViews()
        Glide.with(requireContext())
            .load(args.meal.mealUrl)
            .into(ivMeal)

        tvTitle.text = args.meal.name

        tvDescription.text = args.meal.mealDescription

        btnAdd.setOnClickListener {
            viewModel.insertMealItem(args.meal)
            Toast.makeText(context, "Successfully Saved Data", Toast.LENGTH_SHORT).show()
        }
    }

}