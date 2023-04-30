package com.santukis.viewmodels.core.strategies

import dev.icerock.moko.mvvm.viewmodel.ViewModel

interface ViewModelStrategy<Input, Output> {

    fun execute(
        viewModel: ViewModel,
        input: Input,
        onSuccess: (Output) -> Unit
    )

}