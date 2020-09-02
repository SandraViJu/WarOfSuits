package com.sandra.data.mapper

import com.sandra.data.entity.RoundInfoEntity
import com.sandra.data.entity.RoundInfoDB
import com.sandra.data.entity.typeconverter.DataTypeConverter
import javax.inject.Inject

class RoundInfoDBMapper @Inject constructor() :
    Mapper<RoundInfoEntity, RoundInfoDB> {
    override fun map(input: RoundInfoEntity): RoundInfoDB =
        RoundInfoDB(
            winner = input.winner,
            cardA = DataTypeConverter.cardToString(input.cardA),
            cardB = DataTypeConverter.cardToString(input.cardB)
        )
}

class RoundInfoEntityMapper @Inject constructor() :
    Mapper<RoundInfoDB, RoundInfoEntity> {
    override fun map(input: RoundInfoDB): RoundInfoEntity =
        RoundInfoEntity(
            id = input.id,
            winner = input.winner,
            cardA = DataTypeConverter.stringToCard(input.cardA),
            cardB = DataTypeConverter.stringToCard(input.cardB)
        )

}
