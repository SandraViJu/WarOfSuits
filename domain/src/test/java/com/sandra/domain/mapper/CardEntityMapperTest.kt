package com.sandra.domain.mapper

import com.nhaarman.mockitokotlin2.given
import com.nhaarman.mockitokotlin2.mock

import com.sandra.data.entity.CardsEntity
import com.sandra.data.entity.Suits
import com.sandra.domain.model.CardsModel
import com.sandra.domain.model.SuitsModel
import junit.framework.Assert.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class CardEntityMapperTest {

    private val suitsEntityMapper: Mapper<SuitsModel, Suits> = mock()
    private lateinit var mapper: Mapper<CardsModel, CardsEntity>

    @BeforeEach
    internal fun setUp() {
        mapper = CardEntityMapper(suitsEntityMapper)
    }

    @Test
    internal fun `Should map model to entity`() {
        //given
        callSuitMapper()
        val model = modelModel()
        //when
        val entity = mapper.map(model)
        //Then
        assertEquals(expectedEntity(), entity)
    }

    private fun callSuitMapper() {
        given(suitsEntityMapper.map(SuitsModel.CLUBS)).willReturn(Suits.CLUBS)
    }

    private fun expectedEntity() =
        CardsEntity(cardNumber = "1", cardValue = 1, cardType = Suits.CLUBS)

    private fun modelModel() =
        CardsModel(cardNumber = "1", cardValue = 1, cardType = SuitsModel.CLUBS)

}