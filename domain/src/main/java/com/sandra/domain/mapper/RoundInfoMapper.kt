package com.sandra.domain.mapper

import com.sandra.data.entity.CardsEntity
import com.sandra.data.entity.RoundInfoEntity
import com.sandra.domain.model.CardsModel
import com.sandra.domain.model.RoundInfoModel
import javax.inject.Inject

class RoundInfoEntityMapper @Inject constructor(
    private val mapper: @JvmSuppressWildcards Mapper<CardsModel, CardsEntity>
) : Mapper<RoundInfoModel, RoundInfoEntity> {
    override fun map(input: RoundInfoModel): RoundInfoEntity =
        RoundInfoEntity(
            winner = input.winner,
            cardA = mapper.map(input.cardA),
            cardB = mapper.map(input.cardB)
        )
}

class RoundInfoModelMapper @Inject constructor(
    private val mapper: @JvmSuppressWildcards Mapper<CardsEntity, CardsModel>
) : Mapper<RoundInfoEntity, RoundInfoModel> {
    override fun map(input: RoundInfoEntity): RoundInfoModel =
        RoundInfoModel(
            winner = input.winner,
            cardA = mapper.map(input.cardA),
            cardB = mapper.map(input.cardB)
        )
}
