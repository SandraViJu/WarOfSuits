package com.sandra.warofsuits.viewmodeltest

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockitokotlin2.given
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.sandra.domain.model.CardsModel
import com.sandra.domain.model.RoundInfoModel
import com.sandra.domain.model.SuitsModel
import com.sandra.domain.usecase.GetRoundInfoUseCase
import com.sandra.warofsuits.utils.PLAYER_A_WINS
import com.sandra.warofsuits.view.summary.SummaryViewModel
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class SummaryViewModelTest {

    @get:Rule
    var testRule = InstantTaskExecutorRule()

    private var testDispatcher = TestCoroutineDispatcher()
    private var getRoundInfoUseCase: GetRoundInfoUseCase = mock()
    private lateinit var summaryViewModel: SummaryViewModel

    @Before
    fun setUp() {
        summaryViewModel = SummaryViewModel(
            getRoundInfoUseCase = this.getRoundInfoUseCase,
            ioDispatcher = testDispatcher
        )
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }

    @Test
    fun `Should get round info`() {
        testDispatcher.runBlockingTest {
            //given
            callGetRoundInfoUseCase()
            //when
            summaryViewModel.getRoundInfo()
            //then
            verify(getRoundInfoUseCase).invoke()
        }
    }

    @Test
    fun `Observe round info data`() {
        testDispatcher.runBlockingTest {
            //given
            callGetRoundInfoUseCase()
            //when
            var result = summaryViewModel.getRoundInfo()
            //then
            summaryViewModel.roundInfo.observeForever {
                assertEquals(expectedResult(), result)
            }
        }
    }

    private suspend fun callGetRoundInfoUseCase() {
        given(getRoundInfoUseCase.invoke()).willReturn(
            listOf()
        )
    }

    private fun expectedResult() =
        listOf(
            RoundInfoModel(
                winner = PLAYER_A_WINS,
                cardA = CardsModel(cardNumber = "2", cardValue = 2, cardType = SuitsModel.SPADES),
                cardB = CardsModel(cardNumber = "1", cardValue = 1, cardType = SuitsModel.HEARTS)
            )
        )

}
