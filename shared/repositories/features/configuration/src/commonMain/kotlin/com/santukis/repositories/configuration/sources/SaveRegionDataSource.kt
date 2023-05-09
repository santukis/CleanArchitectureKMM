package com.santukis.repositories.configuration.sources

import com.santukis.entities.configuration.Region

interface SaveRegionDataSource {

    suspend fun saveRegion(region: Region)
}