package com.app.android_clean_architecture_assignment.data.remote.repository

import com.app.android_clean_architecture_assignment.data.local.dao.CharacterDao
import com.app.android_clean_architecture_assignment.data.local.model.CharacterLocal
import com.app.android_clean_architecture_assignment.data.remote.entity.CharacterApiResponse
import com.app.android_clean_architecture_assignment.data.remote.entity.CharacterEntity
import com.app.android_clean_architecture_assignment.data.remote.respository.CharacterApi
import com.app.android_clean_architecture_assignment.data.remote.respository.CharacterRepositoryImpl
import com.app.android_clean_architecture_assignment.domain.character.repository.CharacterRepository
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Single
import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class CharacterRepositoryImplTest {

    //Test subject
    private lateinit var characterRepository: CharacterRepository

    //Collaborators
    private lateinit var characterApi: CharacterApi
    private lateinit var characterDao: CharacterDao

    //Utilities
    private lateinit var localList: List<CharacterLocal>
    private lateinit var localResponseList: List<CharacterLocal>
    private lateinit var entityList: List<CharacterEntity>
    private lateinit var characterApiResponse: Single<CharacterApiResponse>


    @Before
    fun setUp() {
        characterApi = mock()

        val character1 = CharacterEntity(
            id = "1",
            name = "Olu Mel",
            imageUrl = "https://static.wikia.nocookie.net/disney/images/6/61/Olu_main.png",
            url = "https://static.wikia.nocookie.net/disney/images/6/61/Olu_main.png"
        )
        val character2 = CharacterEntity(
            id = "2",
            name = "9-Eye",
            imageUrl = "https://static.wikia.nocookie.net/disney/images/7/77/9-eye.jpg",
            url = "https://static.wikia.nocookie.net/disney/images/7/77/9-eye.jpg"
        )

        val char1 = CharacterLocal(
            id = 1,
            name = "Olu Mel",
            imageUrl = "https://static.wikia.nocookie.net/disney/images/6/61/Olu_main.png",
            url = "https://static.wikia.nocookie.net/disney/images/6/61/Olu_main.png"
        )
        val char2 = CharacterLocal(
            id = 2,
            name = "9-Eye",
            imageUrl = "https://static.wikia.nocookie.net/disney/images/7/77/9-eye.jpg",
            url = "https://static.wikia.nocookie.net/disney/images/7/77/9-eye.jpg"
        )

        entityList = listOf(character1, character2)
        characterApiResponse = Single.just(CharacterApiResponse(entityList))
        whenever(characterApi.getCharacters()).thenReturn(characterApiResponse)


        //Mocking UserDao
        characterDao = mock()
        localList = listOf(char1, char2)
        localResponseList = characterDao.getAllCharacters()
        whenever(characterDao.getAllCharacters()).thenReturn(localResponseList)

        //Test subject initialization
        characterRepository = CharacterRepositoryImpl(characterApi, characterDao)
    }

    @Test
    fun `Test Repository when API gives successful response`() {
        whenever(characterApi.getCharacters())
            .thenReturn(characterApiResponse)

        characterRepository.getCharacterData().test()
            .assertSubscribed()
            .assertValue {
                it.isNotEmpty()
            }
            .assertComplete()
    }
}
