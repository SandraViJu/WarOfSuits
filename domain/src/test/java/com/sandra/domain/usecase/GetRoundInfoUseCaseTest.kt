package com.sandra.domain.usecase

import com.nhaarman.mockitokotlin2.given
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.sandra.data.entity.CardsEntity
import com.sandra.data.entity.RoundInfoEntity
import com.sandra.data.entity.Suits
import com.sandra.data.repository.DataBaseRepository
import com.sandra.domain.mapper.Mapper
import com.sandra.domain.model.CardsModel
import com.sandra.domain.model.RoundInfoModel
import com.sandra.domain.model.SuitsModel
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class GetRoundInfoUseCaseTest {

    private val mapper: Mapper<RoundInfoEntity, RoundInfoModel> = mock()
    private val dataBaseRepository: DataBaseRepository = mock()
    private lateinit var getRoundInfoUseCase: GetRoundInfoUseCase

    @BeforeEach
    internal fun setUp() {
        getRoundInfoUseCase = GetRoundInfoUseCaseImpl(dataBaseRepository, mapper)
    }

    @Test
    internal fun `Should call repository`() {
        runBlocking {
            //given
            getRepositoryRoundInfo()
            givenMapper()
            //when
            getRoundInfoUseCase.invoke()
            //then
            verify(dataBaseRepository).getRoundInfo()
        }
    }

    @Test
    internal fun `Should return round info list`() {
        runBlocking {
            //given
            getRepositoryRoundInfo()
            givenMapper()
            //when
            val result = getRoundInfoUseCase.invoke()
            //then
            assertEquals(expectedResult(), result)
        }
    }

    private suspend fun getRepositoryRoundInfo() {
        given(dataBaseRepository.getRoundInfo()).willReturn(
            listOf(
                RoundInfoEntity(
                    winner = "pedrito",
                    cardA = CardsEntity(cardNumber = "A", cardValue = 3, cardType = Suits.DIAMONDS),
                    cardB = CardsEntity(cardNumber = "A", cardValue = 3, cardType = Suits.DIAMONDS)
                )
            )
        )
    }

    private fun givenMapper() {
        given(mapper.map(getRoundInfoEntity())).willReturn(getRoundInfoModel())
    }

    private fun getRoundInfoEntity() =
        RoundInfoEntity(
            winner = "pedrito",
            cardA = CardsEntity(cardNumber = "A", cardValue = 3, cardType = Suits.DIAMONDS),
            cardB = CardsEntity(cardNumber = "A", cardValue = 3, cardType = Suits.DIAMONDS)
        )

    private fun getRoundInfoModel() =
        RoundInfoModel(
            winner = "pedrito",
            cardA = CardsModel(cardNumber = "A", cardValue = 3, cardType = SuitsModel.DIAMONDS),
            cardB = CardsModel(cardNumber = "A", cardValue = 3, cardType = SuitsModel.DIAMONDS)
        )

    private fun expectedResult() =
        listOf(
            RoundInfoModel(
                winner = "pedrito",
                cardA = CardsModel(cardNumber = "A", cardValue = 3, cardType = SuitsModel.DIAMONDS),
                cardB = CardsModel(cardNumber = "A", cardValue = 3, cardType = SuitsModel.DIAMONDS)
            )
        )

}
