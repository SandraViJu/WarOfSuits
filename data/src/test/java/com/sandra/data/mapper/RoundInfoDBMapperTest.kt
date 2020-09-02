package com.sandra.data.mapper

import com.sandra.data.entity.CardsEntity
import com.sandra.data.entity.RoundInfoDB
import com.sandra.data.entity.RoundInfoEntity
import com.sandra.data.entity.Suits
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class RoundInfoDBMapperTest {

    private lateinit var mapper: Mapper<RoundInfoEntity, RoundInfoDB>

    @BeforeEach
    internal fun setUp() {
        mapper = RoundInfoDBMapper()
    }

    @Test
    internal fun `Shoul map entity to DB`() {
        val entity = entityModel()
        val db = mapper.map(entity)
        assertEquals(expectedDB(), db)
    }

    private fun entityModel() =
        RoundInfoEntity(
            winner = "PLAYER_A_WIN",
            cardA = CardsEntity(cardNumber = "3", cardValue = 3, cardType = Suits.CLUBS),
            cardB = CardsEntity(cardNumber = "1", cardValue = 1, cardType = Suits.CLUBS)
        )

    private fun expectedDB() =
        RoundInfoDB(winner="PLAYER_A_WIN",
            cardA="""{"cardNumber":"3","cardValue":3,"cardType":"CLUBS"}""",
            cardB="""{"cardNumber":"1","cardValue":1,"cardType":"CLUBS"}""")


}