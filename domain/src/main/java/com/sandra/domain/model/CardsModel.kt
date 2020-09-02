package com.sandra.domain.model

data class CardsModel(val cardNumber: String, val cardValue: Int, val cardType: SuitsModel)

enum class SuitsModel(val suitColor: ColorSuitModel, val suitValue: Int) {
    CLUBS(ColorSuitModel.BLACK, 3),
    DIAMONDS(ColorSuitModel.BLACK, 2),
    HEARTS(ColorSuitModel.RED, 1),
    SPADES(ColorSuitModel.RED, 0)
}

enum class ColorSuitModel {
    RED,
    BLACK
}