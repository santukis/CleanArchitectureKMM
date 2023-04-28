package com.santukis.viewmodels.core

import com.santukis.usecases.UseCase
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

inline fun <reified Request, reified Response> ViewModel.executeUseCase(
    useCase: UseCase<Request, Flow<Response>>,
    request: Request,
    crossinline onError: (Throwable) -> Unit = {},
    crossinline onSuccess: (Response) -> Unit = {}
) {
    viewModelScope.launch(Dispatchers.Main) {
        useCase(request)
            .flowOn(Dispatchers.Default)
            .catch { onError(it) }
            .collect { onSuccess(it) }
    }
}