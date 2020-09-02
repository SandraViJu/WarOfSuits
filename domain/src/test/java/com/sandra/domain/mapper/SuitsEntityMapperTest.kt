package com.sandra.domain.mapper

import com.sandra.data.entity.Suits
import com.sandra.domain.model.SuitsModel
import junit.framework.Assert.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class SuitsEntityMapperTest {

    private lateinit var mapper: Mapper<SuitsModel, Suits>

    @BeforeEach
    internal fun setUp() {
        mapper = SuitsEntityMapper()
    }

    @Test
    internal fun `Should map model to entity`() {
        //given
        val model = modelModel()
        //when
        val entity = mapper.map(model)
        //then
        assertEquals(expectedEntity(), entity)
    }

    private fun expectedEntity() = Suits.CLUBS

    private fun modelModel() = SuitsModel.CLUBS

}