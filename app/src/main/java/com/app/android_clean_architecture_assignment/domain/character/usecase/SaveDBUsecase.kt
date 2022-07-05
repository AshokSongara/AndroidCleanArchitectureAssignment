package com.app.android_clean_architecture_assignment.domain.character.usecase

import com.app.android_clean_architecture_assignment.data.local.model.CharacterLocal
import com.app.android_clean_architecture_assignment.domain.common.base.BaseCoroutineUsecase
import com.app.android_clean_architecture_assignment.domain.character.repository.CharacterRepository
import javax.inject.Inject

class SaveDBUsecase @Inject constructor(
    private val characterRepository: CharacterRepository
) : BaseCoroutineUsecase<Unit, CharacterLocal>() {

    override suspend fun execute(params: CharacterLocal){
        return characterRepository.saveAllLocalData(params)
    }

}