package com.santukis.datasources.configuration.local

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import com.santukis.datasources.configuration.defaultRegion
import com.santukis.datasources.configuration.remote.entities.RegionDto
import com.santukis.datasources.configuration.remote.mappers.toRegionDto
import com.santukis.entities.configuration.Region
import com.santukis.repositories.configuration.sources.GetRegionDataSource
import com.santukis.repositories.configuration.sources.SaveRegionDataSource
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.singleOrNull
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import kotlinx.serialization.serializer

class LocalConfigurationDataSource(
    private val dataStore: DataStore<Preferences>
):
    GetRegionDataSource,
    SaveRegionDataSource {

    private val regionKey = stringPreferencesKey("region")

    override suspend fun getRegion(): Region =
        dataStore.data.map {
            val dto = Json.decodeFromString<RegionDto>(it[regionKey].orEmpty())
            dto.toRegion()

        }.singleOrNull() ?: defaultRegion()

    override suspend fun saveRegion(region: Region) {
        dataStore.edit {
            val json = Json.encodeToString(serializer(),  region.toRegionDto())
            it[regionKey] = json
        }
    }
}