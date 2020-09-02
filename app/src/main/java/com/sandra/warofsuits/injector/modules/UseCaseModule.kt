package com.sandra.warofsuits.injector.modules

import com.sandra.domain.usecase.*
import dagger.Binds
import dagger.Module

@Module
interface UseCaseModule {

    @Binds
    fun getCardList(getCardListUseCase: GetCardListUseCaseImpl): GetCardListUseCase

    @Binds
    fun saveRoundInfo(saveRoundInfoUseCaseImpl: SaveRoundInfoUseCaseImpl): SaveRoundInfoUseCase

    @Binds
    fun getRoundInfo(getRoundInfoUseCaseImpl: GetRoundInfoUseCaseImpl): GetRoundInfoUseCase

    @Binds
    fun clearRoundInfo(clearRoundInfoUseCaseImpl: ClearRoundInfoUseCaseImpl): ClearRoundInfoUseCase

}