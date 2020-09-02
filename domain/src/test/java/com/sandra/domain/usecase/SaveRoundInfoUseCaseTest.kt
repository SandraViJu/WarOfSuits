package com.sandra.domain.usecase

import com.nhaarman.mockitokotlin2.*
import com.sandra.data.entity.CardsEntity
import com.sandra.data.entity.RoundInfoEntity
import com.sandra.data.entity.Suits
import com.sandra.data.repository.DataBaseRepository
import com.sandra.domain.mapper.Mapper
import com.sandra.domain.model.CardsModel
import com.sandra.domain.model.RoundInfoModel
import com.sandra.domain.model.SuitsModel
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class SaveRoundInfoUseCaseTest {

    private val mapper: Mapper<RoundInfoModel, RoundInfoEntity> = mock()
    private val dataBaseRepository: DataBaseRepository = mock()
    private lateinit var saveRoundInfoUseCaseTest: SaveRoundInfoUseCase

    @BeforeEach
    internal fun setUp() {
        saveRoundInfoUseCaseTest = SaveRoundInfoUseCaseImpl(dataBaseRepository, mapper)
    }

    @Test
    internal fun `Shoul call repository`() {
        runBlocking {
            //given
            getRepositorySaveroundInfo()
            givenMapper()
            //when
            saveRoundInfoUseCaseTest.invoke(
                getRoundInfoModel()
            )
            //then
            verify(dataBaseRepository).saveRoundInfo(any())
        }
    }

    private suspend fun getRepositorySaveroundInfo() {
        given(dataBaseRepository.saveRoundInfo(
            getRoundInfoEntity()
        )).willReturn(Unit)
    }

    private fun givenMapper() {
        given(mapper.map(getRoundInfoModel())).willReturn(getRoundInfoEntity())
    }

    private fun getRoundInfoModel() =
        RoundInfoModel(
            winner = "player b",
            cardA = CardsModel(cardNumber = "2", cardValue = 1, cardType = SuitsModel.HEARTS),
            cardB = CardsModel(cardNumber = "2", cardValue = 1, cardType = SuitsModel.CLUBS)
        )

    private fun getRoundInfoEntity() =
        RoundInfoEntity(
            winner = "player b",
            cardA = CardsEntity(cardNumber = "2", cardValue = 1, cardType = Suits.HEARTS),
            cardB = CardsEntity(cardNumber = "2", cardValue = 1, cardType = Suits.CLUBS)
        )

}
