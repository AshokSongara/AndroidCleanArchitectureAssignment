package com.app.android_clean_architecture_assignment.domain.character.repository

import com.app.android_clean_architecture_assignment.data.local.model.CharacterLocal
import com.app.android_clean_architecture_assignment.domain.model.CharacterModel
import io.reactivex.Single

interface CharacterRepository {
    fun getCharacterData(): Single<MutableList<CharacterModel>>
    suspend fun saveAllLocalData(characters: CharacterLocal)
}



