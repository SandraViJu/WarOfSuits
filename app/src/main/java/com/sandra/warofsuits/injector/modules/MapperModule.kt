package com.sandra.warofsuits.injector.modules

import com.sandra.data.entity.CardsEntity
import com.sandra.data.entity.RoundInfoDB
import com.sandra.data.entity.RoundInfoEntity
import com.sandra.data.entity.Suits
import com.sandra.data.mapper.RoundInfoDBMapper
import com.sandra.domain.mapper.*
import com.sandra.domain.model.CardsModel
import com.sandra.domain.model.RoundInfoModel
import com.sandra.domain.model.SuitsModel
import dagger.Binds
import dagger.Module

@Module
interface MapperModule {

    @Binds
    fun bindsCardModelMapper(cardModelMapper: CardModelMapper): Mapper<CardsEntity, CardsModel>

    @Binds
    fun bindsCardEntityMapper(cardEntityMapper: CardEntityMapper): Mapper<CardsModel, CardsEntity>

    @Binds
    fun bindsSuitsEntityMapper(suitsEntitMapper: SuitsEntityMapper): Mapper<SuitsModel, Suits>

    @Binds
    fun bindsSuitModelMapper(suitsModelMapper: SuitsModelMapper): Mapper<Suits, SuitsModel>

    @Binds
    fun bindsRoundInfoEntityMapper(roundInfoEntityMapper: RoundInfoEntityMapper): Mapper<RoundInfoModel, RoundInfoEntity>

    @Binds
    fun bindsRoundInfoModelMapper(roundInfoModelMapper: RoundInfoModelMapper): Mapper<RoundInfoEntity, RoundInfoModel>

    @Binds
    fun bindsRoundInfoEntityToDBMapper(roundInfoDBMapper: RoundInfoDBMapper): com.sandra.data.mapper.Mapper<RoundInfoEntity, RoundInfoDB>

    @Binds
    fun bindsRoundInfoDBToEntityMapper(roundInfoEntityMapper: com.sandra.data.mapper.RoundInfoEntityMapper): com.sandra.data.mapper.Mapper<RoundInfoDB, RoundInfoEntity>

}