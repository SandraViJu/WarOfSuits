package com.sandra.data.mapper

import com.sandra.data.entity.CardsEntity
import com.sandra.data.entity.RoundInfoDB
import com.sandra.data.entity.RoundInfoEntity
import com.sandra.data.entity.Suits
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class RoundInfoEntityMapperTest {

    private lateinit var mapper: Mapper<RoundInfoDB, RoundInfoEntity>

    @BeforeEach
    internal fun setUp() {
        mapper = RoundInfoEntityMapper()
    }

    @Test
    internal fun `Shoul map entity to DB`() {
        val db = dbModel()
        val entity = mapper.map(db)
        assertEquals(expectedEntity(), entity)
    }

    private fun expectedEntity() =
        RoundInfoEntity(
            winner = "PLAYER_A_WIN",
            cardA = CardsEntity(cardNumber = "3", cardValue = 3, cardType = Suits.CLUBS),
            cardB = CardsEntity(cardNumber = "1", cardValue = 1, cardType = Suits.CLUBS)
        )

    private fun dbModel() =
        RoundInfoDB(winner="PLAYER_A_WIN",
            cardA="""{"cardNumber":"3","cardValue":3,"cardType":"CLUBS"}""",
            cardB="""{"cardNumber":"1","cardValue":1,"cardType":"CLUBS"}""")


}