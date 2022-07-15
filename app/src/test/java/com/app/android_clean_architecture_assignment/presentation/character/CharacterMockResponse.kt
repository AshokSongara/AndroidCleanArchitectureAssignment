package com.app.android_clean_architecture_assignment.presentation.character

import com.app.android_clean_architecture_assignment.data.mapper.CharacterDisplayMapper
import com.app.android_clean_architecture_assignment.data.remote.entity.CharacterApiResponse
import com.app.android_clean_architecture_assignment.domain.model.CharacterModel
import com.google.gson.Gson

const val CHARACTER_JSON = " {\n" +
        "     \"data\": [{\n" +
        "         \"_id\": 1,\n" +
        "         \"name\": \"Olu Mel\",\n" +
        "         \"imageUrl\": \"https://static.wikia.nocookie.net/disney/images/6/61/Olu_main.png\",\n" +
        "         \"url\": \"https://static.wikia.nocookie.net/disney/images/6/61/Olu_main.png\"\n" +
        "     }, {\n" +
        "         \"_id\": 2,\n" +
        "         \"name\": \".GIFfany\",\n" +
        "         \"imageUrl\": \"https://static.wikia.nocookie.net/disney/images/5/51/Giffany.png\",\n" +
        "         \"url\": \"https://static.wikia.nocookie.net/disney/images/5/51/Giffany.png\"\n" +
        "     }, {\n" +
        "         \"_id\": 3,\n" +
        "         \"name\": \"9-Eye\",\n" +
        "         \"imageUrl\": \"https://static.wikia.nocookie.net/disney/images/7/77/9-eye.jpg\",\n" +
        "         \"url\": \"https://static.wikia.nocookie.net/disney/images/7/77/9-eye.jpg\"\n" +
        "     }, {\n" +
        "         \"_id\": 4,\n" +
        "         \"name\": \"90's Adventure Bear\",\n" +
        "         \"imageUrl\": \"https://static.wikia.nocookie.net/disney/images/3/3f/90%27s_Adventure_Bear_profile.png\",\n" +
        "         \"url\": \"https://static.wikia.nocookie.net/disney/images/3/3f/90%27s_Adventure_Bear_profile.png\"\n" +
        "     }, {\n" +
        "         \"_id\": 5,\n" +
        "         \"name\": \"A.J. Arno\",\n" +
        "         \"imageUrl\": \"https://static.wikia.nocookie.net/disney/images/2/2c/A.J._Arno.jpg\",\n" +
        "         \"url\": \"https://static.wikia.nocookie.net/disney/images/2/2c/A.J._Arno.jpg\"\n" +
        "     }]\n" +
        " }"

val gson = Gson()

fun characterResponse(): CharacterApiResponse =
    gson.fromJson(CHARACTER_JSON, CharacterApiResponse::class.java)

fun chratcerDomainResponse(): MutableList<CharacterModel> =
    CharacterDisplayMapper().toCharacterDataDomainList(
        characterResponse().data
    ).toMutableList()