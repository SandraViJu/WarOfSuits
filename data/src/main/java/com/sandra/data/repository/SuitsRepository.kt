package com.sandra.data.repository

import com.sandra.data.entity.CardsEntity

interface SuitsRepository {

    suspend fun getCardsList(): List<CardsEntity>

}
