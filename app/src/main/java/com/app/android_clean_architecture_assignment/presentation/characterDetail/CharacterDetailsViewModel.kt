package com.app.android_clean_architecture_assignment.presentation.characterDetail

import androidx.lifecycle.viewModelScope
import com.app.android_clean_architecture_assignment.domain.character.usecase.SaveDBUsecase
import com.app.android_clean_architecture_assignment.domain.model.CharacterModel
import com.app.android_clean_architecture_assignment.presentation.common.base.BaseViewModel
import com.app.android_clean_architecture_assignment.domain.mapper.toRoomModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class CharacterDetailsViewModel @Inject constructor(
    private var saveDBUseCase: SaveDBUsecase
) : BaseViewModel() {

    fun insertCharacter(characterModel: CharacterModel) {
        viewModelScope.launch {
            saveDBUseCase.invoke(
                scope = viewModelScope,
                params = characterModel.toRoomModel()
            ) { result ->
                result.result({
                    Timber.i("Successfully Saved Data")
                }, {
                    Timber.e("Error")
                })
            }
        }
    }
}