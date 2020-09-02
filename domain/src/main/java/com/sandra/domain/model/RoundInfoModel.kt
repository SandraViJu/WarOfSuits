package com.sandra.domain.model

data class RoundInfoModel(val id: Int? = null, val winner: String, var cardA: CardsModel, var cardB: CardsModel)
