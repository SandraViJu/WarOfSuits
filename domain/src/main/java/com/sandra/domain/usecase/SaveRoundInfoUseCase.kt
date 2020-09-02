package com.sandra.domain.usecase

import com.sandra.data.entity.RoundInfoEntity
import com.sandra.data.repository.DataBaseRepository
import com.sandra.domain.mapper.Mapper
import com.sandra.domain.model.RoundInfoModel
import javax.inject.Inject

interface SaveRoundInfoUseCase {
    suspend operator fun invoke(roundInfoModel: RoundInfoModel)
}

class SaveRoundInfoUseCaseImpl @Inject constructor(
    private val dataBaseRepository: DataBaseRepository,
    private val mapper: @JvmSuppressWildcards Mapper<RoundInfoModel, RoundInfoEntity>
) :
    SaveRoundInfoUseCase {
    override suspend fun invoke(roundInfoModel: RoundInfoModel) {
        dataBaseRepository.saveRoundInfo(roundInfoEntity = mapper.map(roundInfoModel))
    }

}
