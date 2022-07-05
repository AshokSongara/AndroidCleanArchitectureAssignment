package com.app.android_clean_architecture_assignment.domain.character.repository

import com.app.android_clean_architecture_assignment.data.local.model.CharacterLocal
import com.app.android_clean_architecture_assignment.data.remote.entity.CharacterApiResponse
import io.reactivex.Single

interface CharacterRepository {
    fun getCharacterData(): Single<CharacterApiResponse>
    fun getLocalCharacterData(): List<CharacterLocal>
    suspend fun saveAllLocalData(characters: CharacterLocal)
}
