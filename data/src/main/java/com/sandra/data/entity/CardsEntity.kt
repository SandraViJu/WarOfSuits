package com.sandra.data.entity

data class CardsEntity(val cardNumber: String = "", val cardValue: Int = 0, val cardType: Suits = Suits.SPADES)

enum class Suits(val suitColor: ColorSuit) {
    CLUBS(ColorSuit.BLACK),
    DIAMONDS(ColorSuit.BLACK),
    HEARTS(ColorSuit.RED),
    SPADES(ColorSuit.RED)
}

enum class ColorSuit {
    RED,
    BLACK
}