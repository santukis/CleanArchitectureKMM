package com.santukis.repositories.configuration.sources

import com.santukis.entities.configuration.Region

interface GetRegionDataSource {

    suspend fun getRegion(): Region
}