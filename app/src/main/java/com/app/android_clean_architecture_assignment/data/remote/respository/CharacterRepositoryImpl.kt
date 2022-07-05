package com.app.android_clean_architecture_assignment.data.remote.respository

import com.app.android_clean_architecture_assignment.data.local.dao.CharacterDao
import com.app.android_clean_architecture_assignment.data.local.model.CharacterLocal
import com.app.android_clean_architecture_assignment.data.remote.entity.CharacterApiResponse
import com.app.android_clean_architecture_assignment.domain.character.repository.CharacterRepository
import io.reactivex.Single
import javax.inject.Inject

class CharacterRepositoryImpl @Inject constructor(
    private val characterApi: CharacterApi,
    private val characterDao: CharacterDao
) : CharacterRepository {

    override fun getCharacterData(): Single<CharacterApiResponse> {
        return characterApi.getCharacters()
    }

    override fun getLocalCharacterData(): List<CharacterLocal> {
        return characterDao.getAllCharacters()
    }

    override suspend fun saveAllLocalData(characters: CharacterLocal) {
        characterDao.insertCharacters(characters)
    }

}