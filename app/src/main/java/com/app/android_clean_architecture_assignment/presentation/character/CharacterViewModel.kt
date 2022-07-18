package com.app.android_clean_architecture_assignment.presentation.character

import androidx.lifecycle.LiveData
import com.app.android_clean_architecture_assignment.common.setError
import com.app.android_clean_architecture_assignment.common.setLoading
import com.app.android_clean_architecture_assignment.common.setSuccess
import com.app.android_clean_architecture_assignment.domain.character.usecase.CharacterUseCase
import com.app.android_clean_architecture_assignment.domain.model.CharacterModel
import com.app.android_clean_architecture_assignment.presentation.common.Resource
import com.app.android_clean_architecture_assignment.presentation.common.base.BaseViewModel
import com.app.android_clean_architecture_assignment.presentation.common.base.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CharacterViewModel @Inject constructor(
    private var characterUseCase: CharacterUseCase,
) : BaseViewModel() {

    private val _characterLiveEvent = SingleLiveEvent<Resource<MutableList<CharacterModel>>>()
    val characterLiveEvent: LiveData<Resource<MutableList<CharacterModel>>> =
        _characterLiveEvent

    private lateinit var dataList: MutableList<CharacterModel>

    override fun loadPage(multipleTimes: Boolean): Boolean {
        fetchCharacters()
        return super.loadPage(multipleTimes)
    }

    private fun fetchCharacters() {
        _characterLiveEvent.setLoading()
        characterUseCase.execute()
            .subscribe({
                dataList = it
                _characterLiveEvent.setSuccess(it)
            }, {
                _characterLiveEvent.setError(it)
            }).collect()
    }

    fun searchCharacters(text: String) {
        val filteredList: MutableList<CharacterModel> = dataList.filter {
            it.name?.lowercase()?.contains(text.lowercase().trim()) == true
        } as MutableList<CharacterModel>

        if (filteredList.isEmpty()) {
            _characterLiveEvent.setError(Throwable("No Data Found.."))
        } else {
            _characterLiveEvent.setSuccess(filteredList)
        }
    }

}