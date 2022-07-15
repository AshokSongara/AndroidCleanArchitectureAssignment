package com.app.android_clean_architecture_assignment.domain.mapper

import com.app.android_clean_architecture_assignment.data.local.model.CharacterLocal
import com.app.android_clean_architecture_assignment.domain.model.CharacterModel

fun CharacterModel.toRoomModel() = CharacterLocal(
    id = id.toLong(),
    name = name,
    imageUrl = imageUrl,
    url = url
)

