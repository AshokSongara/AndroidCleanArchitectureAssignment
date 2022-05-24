package com.app.android_clean_architecture_assignment.presentation.meal

import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.app.android_clean_architecture_assignment.R
import com.app.android_clean_architecture_assignment.presentation.common.Resource
import com.app.android_clean_architecture_assignment.presentation.common.Status
import com.app.android_clean_architecture_assignment.presentation.common.base.BaseViewModelFragment
import com.app.android_clean_architecture_assignment.presentation.common.base.SafeObserver
import com.app.android_clean_architecture_assignment.presentation.model.MealModel
import kotlinx.android.synthetic.main.fragment_meal.*

class MealFragment : BaseViewModelFragment<MealViewModel>() {

    private val mealAdapter by lazy { MealAdapter(this::onItemClicked) }

    override fun getContentResource() = R.layout.fragment_meal

    override fun injectDagger() {
        initPresenterComponent()?.inject(this)
    }

    override fun buildViewModel(): MealViewModel {
        return ViewModelProviders.of(this, viewModelFactory)[MealViewModel::class.java]
    }

    override fun initLiveDataObservers() {
        super.initLiveDataObservers()
        viewModel.mealLiveEvent.observe(
            viewLifecycleOwner,
            SafeObserver(this::handleMealResponse)
        )
    }

    override fun initViews() {
        super.initViews()
        rvMeal.adapter = mealAdapter
    }

    private fun onItemClicked(mealModel: MealModel) {
        val bundle = bundleOf("meal" to mealModel.mealUrl)
        findNavController().navigate(R.id.action_mealFragment_to_mealDetailFragment, bundle)
    }

    private fun handleMealResponse(response: Resource<ArrayList<MealModel>>) {
        when (response.status) {
            Status.LOADING -> progressBar.show()
            Status.ERROR -> handleMealError(response)
            Status.SUCCESS -> handleRandomSuccess(response.data)
        }
    }

    private fun handleRandomSuccess(response: ArrayList<MealModel>?) {
        progressBar.hide()
        response?.let {
            // handle success here
            mealAdapter.setItems(it)
        }
    }

    private fun handleMealError(response: Resource<ArrayList<MealModel>>) {
        progressBar.hide()
        response.throwable?.let {
            Toast.makeText(requireContext(), it.message, Toast.LENGTH_LONG).show()
        }
    }
}