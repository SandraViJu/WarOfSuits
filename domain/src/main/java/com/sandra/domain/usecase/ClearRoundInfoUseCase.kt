package com.sandra.domain.usecase

import com.sandra.data.repository.DataBaseRepository
import javax.inject.Inject

interface ClearRoundInfoUseCase {
    suspend operator fun invoke()
}

class ClearRoundInfoUseCaseImpl @Inject constructor(private val dataBaseRepository: DataBaseRepository) :
    ClearRoundInfoUseCase {
    override suspend fun invoke() {
        dataBaseRepository.clearRoundInfo()
    }

}