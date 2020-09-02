package com.sandra.domain.usecase

import com.nhaarman.mockitokotlin2.given
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.sandra.data.repository.DataBaseRepository
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class ClearRoundInfoUseCasdTest {

    private val dataBaseRepositoryImpl: DataBaseRepository = mock()
    private lateinit var clearRoundInfoUseCase: ClearRoundInfoUseCase

    @BeforeEach
    internal fun setUp() {
        clearRoundInfoUseCase = ClearRoundInfoUseCaseImpl(dataBaseRepositoryImpl)
    }

    @Test
    internal fun `Should call repository`() {
        runBlocking {
            //given
            getRepositoryClearRoundInfo()
            //when
            clearRoundInfoUseCase.invoke()
            //then
            verify(dataBaseRepositoryImpl).clearRoundInfo()
        }
    }

    private suspend fun getRepositoryClearRoundInfo() {
        given(dataBaseRepositoryImpl.clearRoundInfo()).willReturn(Unit)
    }

}
