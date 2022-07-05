package com.app.android_clean_architecture_assignment.presentation.mapper

import com.app.android_clean_architecture_assignment.data.local.model.CharacterLocal
import com.app.android_clean_architecture_assignment.data.remote.entity.CharacterEntity
import com.app.android_clean_architecture_assignment.domain.model.CharacterModel

class CharacterDisplayMapper {
    fun transformCharacterDisplay(characterEntity: CharacterEntity): CharacterModel {
        return CharacterModel(
            characterEntity.id,
            characterEntity.name,
            characterEntity.imageUrl,
            characterEntity.url
        )
    }

    fun transformCharacterLocalToDomain(characterLocal: CharacterLocal): CharacterModel {
        return CharacterModel(
            characterLocal.id.toString(), characterLocal.name, characterLocal.imageUrl, characterLocal.url
        )
    }

    fun transformCharacterDomainToLocal(characterDataItem: CharacterModel): CharacterLocal {
        return CharacterLocal(
            characterDataItem.id.toLong(),
            characterDataItem.name,
            characterDataItem.imageUrl,
            characterDataItem.url
        )
    }
}