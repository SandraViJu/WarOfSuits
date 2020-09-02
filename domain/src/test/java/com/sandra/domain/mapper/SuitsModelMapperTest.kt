package com.sandra.domain.mapper

import com.sandra.data.entity.Suits
import com.sandra.domain.model.SuitsModel
import junit.framework.Assert.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class SuitsModelMapperTest {

    private lateinit var mapper: Mapper<Suits, SuitsModel>

    @BeforeEach
    internal fun setUp() {
        mapper = SuitsModelMapper()
    }

    @Test
    internal fun `Shoul map model to entity`() {
        //given
        val entity = entityModel()
        //when
        val model = mapper.map(entity)
        //then
        assertEquals(expectedModel(), model)
    }

    private fun entityModel() = Suits.CLUBS

    private fun expectedModel() = SuitsModel.CLUBS
}