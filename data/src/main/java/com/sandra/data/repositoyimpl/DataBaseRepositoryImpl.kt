package com.sandra.data.repositoyimpl

import com.sandra.data.database.WarOfSuitsDatabase
import com.sandra.data.entity.RoundInfoDB
import com.sandra.data.entity.RoundInfoEntity
import com.sandra.data.mapper.Mapper
import com.sandra.data.repository.DataBaseRepository
import javax.inject.Inject

class DataBaseRepositoryImpl @Inject constructor(private val warOfSuitsDatabase: WarOfSuitsDatabase,
                                                 private val mapperDB: @JvmSuppressWildcards Mapper<RoundInfoEntity, RoundInfoDB>,
                                                 private val mapperEntity: @JvmSuppressWildcards Mapper<RoundInfoDB, RoundInfoEntity>
) : DataBaseRepository {

    override suspend fun saveRoundInfo(roundInfoEntity: RoundInfoEntity) {
        warOfSuitsDatabase.warOfSuitsDao().insert(mapperDB.map(roundInfoEntity))
    }

    override suspend fun getRoundInfo(): List<RoundInfoEntity> =
        warOfSuitsDatabase.warOfSuitsDao().getRoundInfo().map {
            mapperEntity.map(it)
        }

    override suspend fun clearRoundInfo() {
        warOfSuitsDatabase.warOfSuitsDao().clear()
    }

}