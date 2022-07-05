package com.app.android_clean_architecture_assignment.data.remote.entity

import com.app.android_clean_architecture_assignment.domain.model.CharacterModel
import com.google.gson.annotations.SerializedName

class CharacterEntity {

    @SerializedName("_id")
    lateinit var id: String

    @SerializedName("name")
    lateinit var name: String

    @SerializedName("imageUrl")
    lateinit var imageUrl: String

    @SerializedName("url")
    lateinit var url: String
}

fun ArrayList<CharacterEntity>.transformCharacterDisplayList(): ArrayList<CharacterModel> {
    val characterList = ArrayList<CharacterModel>()
    this.forEach {
        characterList.add(
            CharacterModel(
                it.id,
                it.name,
                it.imageUrl,
                it.url
            )
        )
    }
    return characterList
}