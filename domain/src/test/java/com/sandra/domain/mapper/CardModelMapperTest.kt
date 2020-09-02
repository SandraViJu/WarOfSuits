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

class CardModelMapperTest {

    private var suitModelMapper : Mapper<Suits, SuitsModel> = mock()
    private lateinit var mapper: Mapper<CardsEntity, CardsModel>

    @BeforeEach
    internal fun setUp() {
        mapper = CardModelMapper(suitModelMapper)
    }

    @Test
    internal fun `Should map entity to model`() {
        //given
        callSuitstModelMapper()
        val entity = entityModel()
        //when
        val model = mapper.map(entity)
        //then
        assertEquals(expectedModel(), model)

    }

    private fun callSuitstModelMapper() {
        given(suitModelMapper.map(Suits.CLUBS)).willReturn(
            SuitsModel.CLUBS
        )
    }

    private fun entityModel() =
        CardsEntity(cardNumber = "1", cardValue = 1, cardType = Suits.CLUBS)

    private fun expectedModel() =
        CardsModel(cardNumber = "1", cardValue = 1, cardType = SuitsModel.CLUBS)
}