package com.sandra.domain.usecase

import com.sandra.data.entity.RoundInfoEntity
import com.sandra.data.repository.DataBaseRepository
import com.sandra.domain.mapper.Mapper
import com.sandra.domain.model.RoundInfoModel
import javax.inject.Inject

interface GetRoundInfoUseCase {
    suspend operator fun invoke(): List<RoundInfoModel>
}

class GetRoundInfoUseCaseImpl @Inject constructor(
    private val dataBaseRepository: DataBaseRepository,
    private val mapper: @JvmSuppressWildcards Mapper<RoundInfoEntity, RoundInfoModel>
) : GetRoundInfoUseCase {

    override suspend fun invoke(): List<RoundInfoModel> =
        dataBaseRepository.getRoundInfo().map {
            mapper.map(it)
        }

}