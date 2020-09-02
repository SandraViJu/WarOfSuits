package com.sandra.domain.usecase

import com.sandra.data.entity.CardsEntity
import com.sandra.data.repository.SuitsRepository
import com.sandra.domain.mapper.Mapper
import com.sandra.domain.model.CardsModel
import javax.inject.Inject

interface GetCardListUseCase {
    suspend operator fun invoke(): List<CardsModel>
}

class GetCardListUseCaseImpl @Inject constructor(
    private val repository: SuitsRepository,
    private val mapper: @JvmSuppressWildcards Mapper<CardsEntity, CardsModel>
) :
    GetCardListUseCase {
    override suspend fun invoke(): List<CardsModel> =
        repository.getCardsList().map {
            mapper.map(it)
        }
}
