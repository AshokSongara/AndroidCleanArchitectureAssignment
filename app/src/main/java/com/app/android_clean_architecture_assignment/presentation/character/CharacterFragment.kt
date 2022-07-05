package com.app.android_clean_architecture_assignment.presentation.character

import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.navigation.fragment.findNavController
import com.app.android_clean_architecture_assignment.R
import com.app.android_clean_architecture_assignment.common.initViewModel
import com.app.android_clean_architecture_assignment.common.safeObserve
import com.app.android_clean_architecture_assignment.domain.model.CharacterModel
import com.app.android_clean_architecture_assignment.presentation.common.Resource
import com.app.android_clean_architecture_assignment.presentation.common.Status
import com.app.android_clean_architecture_assignment.presentation.common.base.BaseViewModelFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_character.*

@AndroidEntryPoint
class CharacterFragment : BaseViewModelFragment<CharacterViewModel>() {

    private val characterAdapter by lazy { CharacterAdapter(this::onItemClicked) }

    override fun getContentResource() = R.layout.fragment_character

    override fun getClassName(): String {
        return this::class.java.simpleName
    }

    override fun buildViewModel() = initViewModel<CharacterViewModel>()

    override fun initViews() {
        super.initViews()
        setHasOptionsMenu(true)
        rvCharacter.adapter = characterAdapter
    }

    override fun initLiveDataObservers() {
        super.initLiveDataObservers()
        viewModel.characterLiveEvent.safeObserve(
            this, this::handleCharacterResponse
        )
    }

    private fun onItemClicked(characterModel: CharacterModel) {
        val action = CharacterFragmentDirections.actionCharacterFragmentToCharacterDetailFragment(
            characterModel
        )
        findNavController().navigate(action)
    }

    private fun handleCharacterResponse(response: Resource<ArrayList<CharacterModel>>) {
        when (response.status) {
            Status.LOADING -> progressBar.show()
            Status.ERROR -> handleError(response)
            Status.SUCCESS -> handleRandomSuccess(response.data)
        }
    }

    private fun handleRandomSuccess(response: ArrayList<CharacterModel>?) {
        progressBar.hide()
        response?.let {
            // handle success here
            characterAdapter.setItems(it)
        }
    }

    private fun handleError(response: Resource<ArrayList<CharacterModel>>) {
        progressBar.hide()
        characterAdapter.clear()
        response.throwable?.let {
            Toast.makeText(requireContext(), it.message, Toast.LENGTH_LONG).show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        // below line is to get our inflater
        val inflater = activity?.menuInflater
        inflater?.inflate(R.menu.menu_main, menu)
        val searchItem: MenuItem = menu.findItem(R.id.search)
        val searchView: SearchView = searchItem.actionView as SearchView

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            android.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(msg: String): Boolean {
                viewModel.searchFilter(msg)
                return false
            }
        })
    }


}