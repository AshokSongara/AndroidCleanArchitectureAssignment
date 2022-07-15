package com.app.android_clean_architecture_assignment.domain.mapper

import com.app.android_clean_architecture_assignment.domain.model.CharacterModel
import io.kotlintest.shouldBe
import org.junit.Test

class CharacterDomainMapperTest {

    @Test
    fun `to test characterModel to domain to Local mapping`() {
        val response = CharacterModel(
            id = "1",
            name = "Olu Mel",
            imageUrl = "https://static.wikia.nocookie.net/disney/images/6/61/Olu_main.png",
            url = "https://static.wikia.nocookie.net/disney/images/6/61/Olu_main.png"
        )

        val characterModel = response.toRoomModel()
        characterModel.id shouldBe "1".toLong()
        characterModel.name shouldBe "Olu Mel"
        characterModel.imageUrl shouldBe "https://static.wikia.nocookie.net/disney/images/6/61/Olu_main.png"
        characterModel.url shouldBe "https://static.wikia.nocookie.net/disney/images/6/61/Olu_main.png"
    }
}