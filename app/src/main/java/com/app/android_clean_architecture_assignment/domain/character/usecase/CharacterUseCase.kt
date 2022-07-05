package com.app.android_clean_architecture_assignment.domain.character.usecase

import com.app.android_clean_architecture_assignment.data.remote.entity.CharacterApiResponse
import com.app.android_clean_architecture_assignment.domain.character.repository.CharacterRepository
import com.app.android_clean_architecture_assignment.domain.common.ErrorTransformer
import com.app.android_clean_architecture_assignment.domain.common.base.BaseUseCase
import io.reactivex.Single
import javax.inject.Inject

class CharacterUseCase @Inject constructor(
    private val characterRepository: CharacterRepository,
    errorTransformer: ErrorTransformer<CharacterApiResponse>
) : BaseUseCase<CharacterApiResponse>(errorTransformer) {

    override fun buildSingle(data: Map<String, Any?>): Single<CharacterApiResponse> {
        return characterRepository.getCharacterData()
    }

}