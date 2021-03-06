package com.app.android_clean_architecture_assignment.data.remote.respository

import com.app.android_clean_architecture_assignment.data.remote.entity.CharacterApiResponse
import io.reactivex.Single
import retrofit2.http.GET

interface CharacterApi {
    @GET("characters")
    fun getCharacters(): Single<CharacterApiResponse>
}
