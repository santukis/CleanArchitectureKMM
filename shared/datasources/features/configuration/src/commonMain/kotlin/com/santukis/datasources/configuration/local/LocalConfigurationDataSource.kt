package com.santukis.datasources.configuration.local

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import com.santukis.datasources.configuration.defaultRegion
import com.santukis.datasources.configuration.remote.mappers.toRegionDto
import com.santukis.entities.configuration.Region
import com.santukis.repositories.configuration.sources.GetRegionDataSource
import com.santukis.repositories.configuration.sources.SaveRegionDataSource
import kotlinx.serialization.json.Json
import kotlinx.serialization.serializer

class LocalConfigurationDataSource(
    private val dataStore: DataStore<Preferences>
):
    GetRegionDataSource,
    SaveRegionDataSource {

    private val regionKey = stringPreferencesKey("region")

    override suspend fun getRegion(): Region = defaultRegion()
//        dataStore.data.map {
//            it[regionKey]?.takeIf { value -> value.isNotEmpty() }?.let { json ->
//                Json.decodeFromString<RegionDto>(json).toRegion()
//            } ?: defaultRegion()
//
//        }.single()

    override suspend fun saveRegion(region: Region) {
        dataStore.edit {
            val json = Json.encodeToString(serializer(), region.toRegionDto())
            it[regionKey] = json
        }
    }
}