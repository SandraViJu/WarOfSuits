package com.sandra.data.repositoyimpl

import com.sandra.data.entity.CardsEntity
import com.sandra.data.entity.Suits
import com.sandra.data.repository.SuitsRepository
import javax.inject.Inject

class SuitsRepositoryImpl @Inject constructor() : SuitsRepository {

    override suspend fun getCardsList(): List<CardsEntity> =
        getDummySuit(Suits.CLUBS)
            .plus(getDummySuit(Suits.DIAMONDS))
            .plus(getDummySuit(Suits.HEARTS))
            .plus(getDummySuit(Suits.SPADES))

    private fun getDummySuit(cardType: Suits): List<CardsEntity> =
        listOf(
            CardsEntity(cardNumber = "2", cardValue = 1, cardType = cardType),
            CardsEntity(cardNumber = "3", cardValue = 2, cardType = cardType),
            CardsEntity(cardNumber = "4", cardValue = 3, cardType = cardType),
            CardsEntity(cardNumber = "5", cardValue = 4, cardType = cardType),
            CardsEntity(cardNumber = "6", cardValue = 5, cardType = cardType),
            CardsEntity(cardNumber = "7", cardValue = 6, cardType = cardType),
            CardsEntity(cardNumber = "8", cardValue = 7, cardType = cardType),
            CardsEntity(cardNumber = "9", cardValue = 8, cardType = cardType),
            CardsEntity(cardNumber = "10", cardValue = 9, cardType = cardType),
            CardsEntity(cardNumber = "J", cardValue = 10, cardType = cardType),
            CardsEntity(cardNumber = "Q", cardValue = 11, cardType = cardType),
            CardsEntity(cardNumber = "K", cardValue = 12, cardType = cardType),
            CardsEntity(cardNumber = "A", cardValue = 13, cardType = cardType)
        )

}
