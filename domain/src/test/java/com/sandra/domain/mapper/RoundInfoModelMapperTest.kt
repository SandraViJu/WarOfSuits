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

class RoundInfoModelMapperTest {

    private val cardModelMapper: Mapper<CardsEntity, CardsModel> = mock()
    private lateinit var mapper: Mapper<RoundInfoEntity, RoundInfoModel>

    @BeforeEach
    internal fun setUp() {
        mapper = RoundInfoModelMapper(cardModelMapper)
    }

    @Test
    internal fun `Should map model to entity`() {
        //given
        givenCardMapper()
        val model = entityModel()
        //when
        val entity = mapper.map(model)
        //Then
        assertEquals(expectedModel(), entity)
    }

    private fun givenCardMapper() {
        given(cardModelMapper.map(
            CardsEntity(cardNumber = "3", cardValue = 3, cardType = Suits.CLUBS)
        )
        ).willReturn(
            CardsModel(cardNumber = "3", cardValue = 3, cardType = SuitsModel.CLUBS)
        )
        given(cardModelMapper.map(
            CardsEntity(cardNumber = "1", cardValue = 1, cardType = Suits.CLUBS)
        )
        ).willReturn(
            CardsModel(cardNumber = "1", cardValue = 1, cardType = SuitsModel.CLUBS)
        )
    }

    private fun entityModel() =
        RoundInfoEntity(
            winner = "PLAYER_A_WIN",
            cardA = CardsEntity(cardNumber = "3", cardValue = 3, cardType = Suits.CLUBS),
            cardB = CardsEntity(cardNumber = "1", cardValue = 1, cardType = Suits.CLUBS)
        )


    private fun expectedModel() =
        RoundInfoModel(
            winner = "PLAYER_A_WIN",
            cardA = CardsModel(cardNumber = "3", cardValue = 3, cardType = SuitsModel.CLUBS),
            cardB = CardsModel(cardNumber = "1", cardValue = 1, cardType = SuitsModel.CLUBS)
        )
}