package com.santukis.repositories.movies.sources

import com.santukis.entities.movies.Keyword

interface GetMostFrequentlyKeywordsDataSource {

    suspend fun getMostFrequentlyKeywords(): List<Keyword>
}