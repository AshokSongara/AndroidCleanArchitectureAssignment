package com.app.android_clean_architecture_assignment.data.remote.respository

import com.app.android_clean_architecture_assignment.data.mapper.CharacterDisplayMapper
import com.app.android_clean_architecture_assignment.domain.character.repository.CharacterRepository
import com.app.android_clean_architecture_assignment.domain.model.CharacterModel
import io.reactivex.Single
import javax.inject.Inject

class CharacterRepositoryImpl @Inject constructor(
    private val characterApi: CharacterApi,
) : CharacterRepository {

    override fun getCharacterData(): Single<MutableList<CharacterModel>> {
        return CharacterDisplayMapper().toCharacterList(characterApi.getCharacters())
    }
}