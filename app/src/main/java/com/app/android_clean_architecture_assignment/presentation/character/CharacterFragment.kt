package com.app.android_clean_architecture_assignment.presentation.character

import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.app.android_clean_architecture_assignment.R
import com.app.android_clean_architecture_assignment.common.initViewModel
import com.app.android_clean_architecture_assignment.common.safeObserve
import com.app.android_clean_architecture_assignment.databinding.FragmentCharacterBinding
import com.app.android_clean_architecture_assignment.domain.model.CharacterModel
import com.app.android_clean_architecture_assignment.presentation.common.Resource
import com.app.android_clean_architecture_assignment.presentation.common.Status
import com.app.android_clean_architecture_assignment.presentation.common.base.BaseViewModelFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharacterFragment : BaseViewModelFragment<CharacterViewModel>() {

    private lateinit var binding: FragmentCharacterBinding

    private val characterAdapter by lazy { CharacterAdapter(this::onItemClicked) }

    override fun getContentResource() = R.layout.fragment_character

    override fun getClassName(): String {
        return this::class.java.simpleName
    }

    override fun buildViewModel() = initViewModel<CharacterViewModel>()

    override fun initViews() {
        super.initViews()
        setHasOptionsMenu(true)
        binding =
            DataBindingUtil.setContentView(requireActivity(), R.layout.fragment_character)
        binding.rvCharacter.adapter = characterAdapter
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

    private fun handleCharacterResponse(response: Resource<MutableList<CharacterModel>>) {
        when (response.status) {
            Status.LOADING -> binding.progressBar.show()
            Status.ERROR -> handleError(response)
            Status.SUCCESS -> handleRandomSuccess(response.data)
        }
    }

    private fun handleRandomSuccess(response: MutableList<CharacterModel>?) {
        binding.progressBar.hide()
        response?.let {
            // handle success here
            characterAdapter.setData(it)
        }
    }

    private fun handleError(response: Resource<MutableList<CharacterModel>>) {
        binding.progressBar.hide()
        characterAdapter.clear()
        response.throwable?.let {
            Toast.makeText(
                requireContext(),
                it.message ?: getString(R.string.no_data_found),
                Toast.LENGTH_LONG
            )
                .show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        requireActivity().menuInflater.inflate(R.menu.menu_main, menu)
        val searchItem: MenuItem = menu.findItem(R.id.search)
        val searchView: SearchView = searchItem.actionView as SearchView

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            android.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(text: String): Boolean {
                viewModel.searchCharacters(text)
                return false
            }
        })
    }


}