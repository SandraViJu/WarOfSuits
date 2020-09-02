package com.sandra.domain.mapper

import com.nhaarman.mockitokotlin2.given
import com.nhaarman.mockitokotlin2.mock
import com.sandra.data.entity.CardsEntity
import com.sandra.data.entity.RoundInfoEntity
import com.sandra.data.entity.Suits
import com.sandra.domain.model.CardsModel
import com.sandra.domain.model.RoundInfoModel
import com.sandra.domain.model.SuitsModel
import junit.framework.Assert.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class RoundInfoEntityMapperTest {

    private val cardEntityMapper: Mapper<CardsModel, CardsEntity> = mock()
    private lateinit var mapper: Mapper<RoundInfoModel, RoundInfoEntity>

    @BeforeEach
    internal fun setUp() {
        mapper = RoundInfoEntityMapper(cardEntityMapper)
    }

    @Test
    internal fun `Should map model to entity`() {
        //given
        givenCardMapper()
        val model = modelModel()
        //when
        val entity = mapper.map(model)
        //Then
        assertEquals(expectedEntity(), entity)
    }

    private fun givenCardMapper() {
        given(cardEntityMapper.map(
            CardsModel(cardNumber = "3", cardValue = 3, cardType = SuitsModel.CLUBS)
        )
        ).willReturn(
            CardsEntity(cardNumber = "3", cardValue = 3, cardType = Suits.CLUBS)
        )
        given(cardEntityMapper.map(
            CardsModel(cardNumber = "1", cardValue = 1, cardType = SuitsModel.CLUBS)
            )
        ).willReturn(
            CardsEntity(cardNumber = "1", cardValue = 1, cardType = Suits.CLUBS)
        )
    }

    private fun modelModel() =
        RoundInfoModel(
            winner = "PLAYER_A_WIN",
            cardA = CardsModel(cardNumber = "3", cardValue = 3, cardType = SuitsModel.CLUBS),
            cardB = CardsModel(cardNumber = "1", cardValue = 1, cardType = SuitsModel.CLUBS)
        )

    private fun expectedEntity() =
        RoundInfoEntity(
            winner = "PLAYER_A_WIN",
            cardA = CardsEntity(cardNumber = "3", cardValue = 3, cardType = Suits.CLUBS),
            cardB = CardsEntity(cardNumber = "1", cardValue = 1, cardType = Suits.CLUBS)
        )
}