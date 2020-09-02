package com.sandra.warofsuits.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sandra.domain.model.CardsModel
import com.sandra.domain.model.RoundInfoModel
import com.sandra.domain.usecase.ClearRoundInfoUseCase
import com.sandra.domain.usecase.GetCardListUseCase
import com.sandra.domain.usecase.SaveRoundInfoUseCase
import com.sandra.warofsuits.utils.PLAYER_A_WINS
import com.sandra.warofsuits.utils.PLAYER_B_WINS
import kotlinx.coroutines.*
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val getCardListUseCase: GetCardListUseCase,
    private val saveRoundInfoUseCase: SaveRoundInfoUseCase,
    private val clearRoundInfoUseCase: ClearRoundInfoUseCase,
    private val ioDispatcher: CoroutineDispatcher
) :
    ViewModel() {

    var _playerACards: List<CardsModel>? = listOf()
    var _playerBCards: List<CardsModel>? = listOf()

    private val _cardA = MutableLiveData<CardsModel>()
    val cardA: LiveData<CardsModel>
        get() = _cardA

    private val _cardB = MutableLiveData<CardsModel>()
    val cardB: LiveData<CardsModel>
        get() = _cardB

    private val _winner = MutableLiveData<String>()
    val winner: LiveData<String>
        get() = _winner

    private val _finishGame = MutableLiveData<Boolean>()
    val finishGame: LiveData<Boolean>
        get() = _finishGame

    private val _lastCardA = MutableLiveData<Boolean>()
    val lastCardA: LiveData<Boolean>
        get() = _lastCardA

    private val _lastCardB = MutableLiveData<Boolean>()
    val lastCardB: LiveData<Boolean>
        get() = _lastCardB

    fun getCardList() {
        viewModelScope.launch {
            val result = withContext(ioDispatcher) {
                clearRoundInfoUseCase()
                getCardListUseCase()
            }
            splitCards(result)
        }
    }

    fun manageCards(playerACards: List<CardsModel>?, playersBCards: List<CardsModel>?) {
        viewModelScope.launch {
            delay(500)
            playerACards?.getOrNull(0)?.let { cardA ->
                playersBCards?.getOrNull(0)?.let {
                    compareCards(cardA = cardA, cardB = it)
                    _playerACards = playerACards.getUpperCard(_cardA)
                    _playerBCards = playersBCards.getUpperCard(_cardB)
                }
            }
            _playerACards?.let {
                if (it.isEmpty()) {
                    _finishGame.postValue(true)
                }
                if (it.size == 1)
                    _lastCardA.postValue(true)
            }

            _playerBCards?.let {
                if (it.size == 1)
                    _lastCardB.postValue(true)
            }
        }
    }

    private fun splitCards(cardList: List<CardsModel>) {
        val shuffledCards = cardList.shuffled().chunked(cardList.size / 2)
        _playerACards = shuffledCards[0]
        _playerBCards = shuffledCards[1]
    }

    private fun compareCards(cardA: CardsModel, cardB: CardsModel) {
        when {
            cardA.cardValue > cardB.cardValue -> saveRound(PLAYER_A_WINS, cardA, cardB)
            cardA.cardValue < cardB.cardValue -> saveRound(PLAYER_B_WINS, cardA, cardB)
            cardA.cardType.suitValue > cardB.cardType.suitValue -> saveRound(
                PLAYER_A_WINS,
                cardA,
                cardB
            )
            else -> saveRound(PLAYER_A_WINS, cardA, cardB)
        }
    }

    private fun List<CardsModel>?.getUpperCard(card: MutableLiveData<CardsModel>): List<CardsModel>? {
        this?.let {
            card.postValue(it[0])
            return it.minus(it[0])
        }
        return this
    }

    private fun saveRound(winner: String, cardA: CardsModel, cardB: CardsModel) {
        _winner.postValue(winner)
        viewModelScope.launch {
            withContext(ioDispatcher) {
                saveRoundInfoUseCase(
                    RoundInfoModel(
                        winner = winner,
                        cardA = cardA,
                        cardB = cardB
                    )
                )
            }
        }
    }

}
