package com.app.android_clean_architecture_assignment.presentation.mealDetail

import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.navArgs
import com.app.android_clean_architecture_assignment.R
import com.app.android_clean_architecture_assignment.presentation.common.base.BaseViewModelFragment
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fragment_meal_detail.*

class MealDetailFragment : BaseViewModelFragment<MealDetailsViewModel>() {

    private val args by navArgs<MealDetailFragmentArgs>()

    override fun getContentResource() = R.layout.fragment_meal_detail

    override fun injectDagger() {
        initPresenterComponent()?.inject(this)
    }

    override fun buildViewModel(): MealDetailsViewModel {
        return ViewModelProviders.of(this, viewModelFactory)[MealDetailsViewModel::class.java]
    }

    override fun initViews() {
        super.initViews()
        Glide.with(requireContext())
            .load(args.meal)
            .into(ivMeal)

    }
}