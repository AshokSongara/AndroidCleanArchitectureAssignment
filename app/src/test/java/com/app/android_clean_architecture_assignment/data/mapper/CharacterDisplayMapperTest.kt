package com.app.android_clean_architecture_assignment.data.mapper

import com.app.android_clean_architecture_assignment.data.remote.entity.CharacterEntity
import io.kotlintest.shouldBe
import org.junit.Test

class CharacterDisplayMapperTest {

    @Test
    fun `to test data character entity to domain to character model mapping`() {
        val response1 = CharacterEntity(
            id = "1",
            name = "Olu Mel",
            imageUrl = "https://static.wikia.nocookie.net/disney/images/6/61/Olu_main.png",
            url = "https://static.wikia.nocookie.net/disney/images/6/61/Olu_main.png"
        )

        val characterEntity = listOf(response1)

        val characterModel = CharacterDisplayMapper().toCharacterDataDomainList(characterEntity)
        characterModel[0].id shouldBe "1"
        characterModel[0].name shouldBe "Olu Mel"
        characterModel[0].imageUrl shouldBe "https://static.wikia.nocookie.net/disney/images/6/61/Olu_main.png"
        characterModel[0].url shouldBe "https://static.wikia.nocookie.net/disney/images/6/61/Olu_main.png"
    }
}