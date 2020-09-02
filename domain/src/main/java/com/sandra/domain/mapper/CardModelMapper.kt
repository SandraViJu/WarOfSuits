package com.sandra.domain.mapper

import com.sandra.data.entity.CardsEntity
import com.sandra.data.entity.Suits
import com.sandra.domain.model.CardsModel
import com.sandra.domain.model.SuitsModel
import javax.inject.Inject

class CardModelMapper @Inject constructor(private val mapper: @JvmSuppressWildcards Mapper<Suits, SuitsModel>) :
    Mapper<CardsEntity, CardsModel> {

    override fun map(input: CardsEntity) =
        CardsModel(
            cardNumber = input.cardNumber,
            cardValue = input.cardValue,
            cardType = mapper.map(input.cardType)
        )

}

class CardEntityMapper @Inject constructor(private val mapper: @JvmSuppressWildcards Mapper<SuitsModel, Suits>) :
    Mapper<CardsModel, CardsEntity> {

    override fun map(input: CardsModel) =
        CardsEntity(
            cardNumber = input.cardNumber,
            cardValue = input.cardValue,
            cardType = mapper.map(input.cardType)
        )

}

class SuitsEntityMapper @Inject constructor() : Mapper<SuitsModel, Suits> {
    override fun map(input: SuitsModel) =
        when (input) {
            SuitsModel.CLUBS -> Suits.CLUBS
            SuitsModel.DIAMONDS -> Suits.DIAMONDS
            SuitsModel.HEARTS -> Suits.HEARTS
            SuitsModel.SPADES -> Suits.SPADES
        }

}

class SuitsModelMapper @Inject constructor() : Mapper<Suits, SuitsModel> {
    override fun map(input: Suits) =
        when (input) {
            Suits.CLUBS -> SuitsModel.CLUBS
            Suits.DIAMONDS -> SuitsModel.DIAMONDS
            Suits.HEARTS -> SuitsModel.HEARTS
            Suits.SPADES -> SuitsModel.SPADES
        }

}
