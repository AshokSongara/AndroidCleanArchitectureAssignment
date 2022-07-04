package com.app.android_clean_architecture_assignment.presentation.meal

import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.app.android_clean_architecture_assignment.R
import com.app.android_clean_architecture_assignment.common.initViewModel
import com.app.android_clean_architecture_assignment.common.safeObserve
import com.app.android_clean_architecture_assignment.domain.model.MealModel
import com.app.android_clean_architecture_assignment.presentation.common.Resource
import com.app.android_clean_architecture_assignment.presentation.common.Status
import com.app.android_clean_architecture_assignment.presentation.common.base.BaseViewModelFragment
import kotlinx.android.synthetic.main.fragment_meal.*

class MealFragment : BaseViewModelFragment<MealViewModel>() {

    private val mealAdapter by lazy { MealAdapter(this::onItemClicked) }

    override fun getContentResource() = R.layout.fragment_meal

    override fun buildViewModel() = initViewModel<MealViewModel>()

    override fun initViews() {
        super.initViews()
        rvMeal.adapter = mealAdapter
    }

    override fun initLiveDataObservers() {
        super.initLiveDataObservers()
        viewModel.mealLiveEvent.safeObserve(
            this, this::handleMealResponse
        )
    }

    private fun onItemClicked(mealModel: MealModel) {
        //  mealViewModel.insertMealItem(mealModel)
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