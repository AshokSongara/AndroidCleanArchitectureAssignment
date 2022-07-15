package com.app.android_clean_architecture_assignment.domain.character.usecase

import com.app.android_clean_architecture_assignment.domain.character.repository.CharacterRepository
import com.app.android_clean_architecture_assignment.domain.common.ErrorTransformer
import com.app.android_clean_architecture_assignment.domain.common.base.BaseUseCase
import com.app.android_clean_architecture_assignment.domain.model.CharacterModel
import io.reactivex.Single
import javax.inject.Inject

class CharacterUseCase @Inject constructor(
    private val characterRepository: CharacterRepository,
    errorTransformer: ErrorTransformer<MutableList<CharacterModel>>
) : BaseUseCase<MutableList<CharacterModel>>(errorTransformer) {

    override fun buildSingle(data: Map<String, Any?>): Single<MutableList<CharacterModel>> {
        return characterRepository.getCharacterData()
    }

}