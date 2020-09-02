package com.sandra.domain.usecase

import com.nhaarman.mockitokotlin2.*
import com.sandra.data.entity.CardsEntity
import com.sandra.data.entity.Suits
import com.sandra.data.repository.SuitsRepository
import com.sandra.domain.mapper.Mapper
import com.sandra.domain.model.CardsModel
import com.sandra.domain.model.SuitsModel
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class GetCardListUseCaseTest {

    private val repository: SuitsRepository = mock()
    private val mapper: Mapper<CardsEntity, CardsModel> = mock()
    private lateinit var getCardListUseCase: GetCardListUseCase



    @BeforeEach
    internal fun setUp() {
        getCardListUseCase = GetCardListUseCaseImpl(repository, mapper)
    }


    @Test
    internal fun `Should call repository`() {
        runBlocking {
//            given
            getRepositoryMock()
            getMapperMock()
//            when
            getCardListUseCase.invoke()
//            then
            verify(repository).getCardsList()
        }
    }

    @Test
    internal fun `Should return card list`() {
        runBlocking {
            getRepositoryMock()
            getMapperMock()
            val result= getCardListUseCase.invoke()
            assertEquals(expectedResult(), result)
        }
    }

    private suspend fun getRepositoryMock() {
        given(repository.getCardsList()).willReturn(
            listOf(
                CardsEntity(cardNumber = "2", cardValue = 1, cardType = Suits.HEARTS)
            )
        )
    }

    private fun getMapperMock() {
        given(mapper.map(
            CardsEntity(cardNumber = "2", cardValue = 1, cardType = Suits.HEARTS)
        )).willReturn(
            CardsModel(cardNumber = "2", cardValue = 1, cardType = SuitsModel.HEARTS)
        )
    }

    private fun expectedResult() =
        listOf(
            CardsModel(cardNumber = "2", cardValue = 1, cardType = SuitsModel.HEARTS)
        )

}
