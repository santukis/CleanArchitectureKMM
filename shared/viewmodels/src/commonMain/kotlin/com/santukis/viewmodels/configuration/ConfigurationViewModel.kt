package com.santukis.viewmodels.configuration

import com.santukis.entities.configuration.Country
import com.santukis.entities.configuration.Language
import com.santukis.usecases.UseCase
import dev.icerock.moko.mvvm.viewmodel.ViewModel

class ConfigurationViewModel(
    private val getCountries: UseCase<Unit, List<Country>>,
    private val getLanguages: UseCase<Unit, List<Language>>,
): ViewModel() {


}