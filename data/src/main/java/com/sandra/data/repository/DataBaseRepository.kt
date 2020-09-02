package com.sandra.data.repository

import com.sandra.data.entity.RoundInfoEntity

interface DataBaseRepository {

    suspend fun saveRoundInfo(roundInfoEntity: RoundInfoEntity)
    suspend fun getRoundInfo(): List<RoundInfoEntity>
    suspend fun clearRoundInfo()

}