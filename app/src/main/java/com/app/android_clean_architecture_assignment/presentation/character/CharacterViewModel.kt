package com.app.android_clean_architecture_assignment.presentation.character

import androidx.lifecycle.viewModelScope
import com.app.android_clean_architecture_assignment.common.setError
import com.app.android_clean_architecture_assignment.common.setLoading
import com.app.android_clean_architecture_assignment.common.setSuccess
import com.app.android_clean_architecture_assignment.domain.character.usecase.CharacterUseCase
import com.app.android_clean_architecture_assignment.domain.character.usecase.FetchLocalDataUseCase
import com.app.android_clean_architecture_assignment.domain.model.CharacterModel
import com.app.android_clean_architecture_assignment.presentation.common.Resource
import com.app.android_clean_architecture_assignment.presentation.common.base.BaseViewModel
import com.app.android_clean_architecture_assignment.presentation.common.base.SingleLiveEvent
import com.app.android_clean_architecture_assignment.presentation.mapper.CharacterDisplayMapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class CharacterViewModel @Inject constructor(
    private var characterUseCase: CharacterUseCase,
    private var fetchLocalUseCase: FetchLocalDataUseCase,
) : BaseViewModel() {

    private val _characterLiveEvent = SingleLiveEvent<Resource<ArrayList<CharacterModel>>>()
    val characterLiveEvent: SingleLiveEvent<Resource<ArrayList<CharacterModel>>> =
        _characterLiveEvent

    var dataList = ArrayList<CharacterModel>()

    override fun loadPage(multipleTimes: Boolean): Boolean {
        fetchCharacters()
        return super.loadPage(multipleTimes)
    }

    private fun fetchCharacters() {
        _characterLiveEvent.setLoading()
        characterUseCase.execute()
            .subscribe({
                it.data.map { character ->
                    dataList.add(CharacterDisplayMapper().transformCharacterDisplay(character))
                }
                _characterLiveEvent.setSuccess(dataList)
            }, {
                _characterLiveEvent.setError(it)
            }).collect()
    }

    fun searchFilter(text: String) {
        val filteredList: ArrayList<CharacterModel> = ArrayList()
        for (item in dataList) {
            if (item.name?.lowercase()?.contains(text.lowercase()) == true) {
                filteredList.add(item)
            }
        }
        if (filteredList.isEmpty()) {
            _characterLiveEvent.setError(Throwable("No Data Found.."))
        } else {
            _characterLiveEvent.setSuccess(filteredList)
        }
    }

    //fetch all data from local database if required
    fun getCharactersFromDB() {
        viewModelScope.launch {
            fetchLocalUseCase.invoke(
                scope = viewModelScope,
                params = Unit
            ) { result ->
                result.result({
                    Timber.i("Total Size${it.size}")
                }, {
                    Timber.e("Error")
                })
            }
        }
    }

}