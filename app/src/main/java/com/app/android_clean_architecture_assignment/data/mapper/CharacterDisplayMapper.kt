package com.app.android_clean_architecture_assignment.data.mapper

import com.app.android_clean_architecture_assignment.data.remote.entity.CharacterApiResponse
import com.app.android_clean_architecture_assignment.data.remote.entity.CharacterEntity
import com.app.android_clean_architecture_assignment.domain.model.CharacterModel
import io.reactivex.Single

@Suppress("UNCHECKED_CAST")
class CharacterDisplayMapper {
    fun toCharacterList(response: Single<CharacterApiResponse>): Single<MutableList<CharacterModel>> {
        return response.map { it1 ->
            it1.data.map {
                CharacterModel(
                    it.id,
                    it.name,
                    it.imageUrl,
                    it.url
                )
            }
        } as Single<MutableList<CharacterModel>>
    }


    private fun CharacterEntity.toCharacterModel() = CharacterModel(
        id = id,
        name = name,
        imageUrl = imageUrl,
        url = url
    )

    fun toCharacterDataDomainList(response: List<CharacterEntity>): List<CharacterModel> {
        return response.map {
            it.toCharacterModel()
        }
    }
}