package com.sandra.warofsuits.viewmodeltest

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.given
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.sandra.domain.model.CardsModel
import com.sandra.domain.model.SuitsModel
import com.sandra.domain.usecase.ClearRoundInfoUseCase
import com.sandra.domain.usecase.GetCardListUseCase
import com.sandra.domain.usecase.SaveRoundInfoUseCase
import com.sandra.warofsuits.utils.PLAYER_A_WINS
import com.sandra.warofsuits.utils.PLAYER_B_WINS
import com.sandra.warofsuits.view.MainViewModel
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
class MainViewModelTest {

    @get:Rule
    var testRule = InstantTaskExecutorRule()

    private val testDispatcher = TestCoroutineDispatcher()
    private val getCardListUseCase: GetCardListUseCase = mock()
    private val saveRoundInfoUseCase: SaveRoundInfoUseCase = mock()
    private val clearRoundInfoUseCase: ClearRoundInfoUseCase = mock()
    private lateinit var mainViewModel: MainViewModel

    @Before
    internal fun setUp() {
        mainViewModel = MainViewModel(
            getCardListUseCase = getCardListUseCase,
            saveRoundInfoUseCase = saveRoundInfoUseCase,
            clearRoundInfoUseCase = clearRoundInfoUseCase,
            ioDispatcher = testDispatcher
        )
        Dispatchers.setMain(testDispatcher)
    }

    @After
    internal fun tearDown() {
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }

    @Test
    internal fun `Should get card list`() {
        testDispatcher.runBlockingTest {
            callCardListUseCaseList()
            mainViewModel.getCardList()
            verify(getCardListUseCase).invoke()
        }
    }

    @Test
    fun `Should call clear round`() {
        testDispatcher.runBlockingTest {
            //given
            callCardListUseCaseList()
            //when
            mainViewModel.getCardList()
            //then
            verify(clearRoundInfoUseCase).invoke()
        }
    }

    @Test
    fun `Player A winns round`() {
        testDispatcher.runBlockingTest {
            mainViewModel.manageCards(getWinnerLastCard(), getLoserLastCard())
            mainViewModel.winner.observeForever {
                assertEquals(it, PLAYER_A_WINS)
            }
        }
    }

    @Test
    fun `Player B winns round`() {
        testDispatcher.runBlockingTest {
            mainViewModel.manageCards(getLoserLastCard(), getWinnerLastCard())
            mainViewModel.winner.observeForever {
                assertEquals(it, PLAYER_B_WINS)
            }
        }
    }

    @Test
    fun `Players play last card`() {
        testDispatcher.runBlockingTest {
            mainViewModel.manageCards(getWinnerLastCard(), getLoserLastCard())
            mainViewModel.lastCardA.observeForever {
                assert(it)
            }
            mainViewModel.lastCardB.observeForever {
                assert(it)
            }
        }
    }

    @Test
    fun `Game is finish`() {
        testDispatcher.runBlockingTest {
            mainViewModel.manageCards(listOf(), listOf())
            mainViewModel.finishGame.observeForever(Observer {
                assert(it)
            })
        }
    }

    private suspend fun callCardListUseCaseList() {
        given(getCardListUseCase.invoke()).willReturn(
            listOf(
                CardsModel(cardNumber = "J", cardValue = 10, cardType = SuitsModel.CLUBS),
                CardsModel(cardNumber = "J", cardValue = 10, cardType = SuitsModel.CLUBS)
            )
        )
    }

    private fun getWinnerLastCard() =
        listOf(
            CardsModel(cardNumber = "J", cardValue = 10, cardType = SuitsModel.CLUBS)
        )

    private fun getLoserLastCard() =
        listOf(
            CardsModel(cardNumber = "1", cardValue = 1, cardType = SuitsModel.CLUBS)
        )

}
