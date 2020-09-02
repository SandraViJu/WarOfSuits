package com.sandra.warofsuits.view.summary

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sandra.domain.model.RoundInfoModel
import com.sandra.domain.usecase.GetRoundInfoUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SummaryViewModel @Inject constructor(
    private val getRoundInfoUseCase: GetRoundInfoUseCase,
    private val ioDispatcher: CoroutineDispatcher
) : ViewModel() {

    private val _roundInfo = MutableLiveData<List<RoundInfoModel>>()
    val roundInfo: LiveData<List<RoundInfoModel>>
        get() = _roundInfo

    fun getRoundInfo() {
        viewModelScope.launch {
            val result = withContext(ioDispatcher) {
                getRoundInfoUseCase()
            }

            _roundInfo.postValue(result)
        }
    }

}