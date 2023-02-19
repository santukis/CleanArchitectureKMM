package com.santukis.repositories.movies.strategies

import com.santukis.entities.movies.Keyword
import com.santukis.repositories.movies.sources.GetMostFrequentlyKeywordsDataSource
import com.santukis.repositories.strategies.LocalStrategy

class GetMostFrequentlyKeywordsFromLocalStrategy(
    private val getMostFrequentlyKeywordsFromLocal: GetMostFrequentlyKeywordsDataSource
): LocalStrategy<Unit, List<Keyword>>() {

    override suspend fun loadFromLocal(input: Unit): List<Keyword> =
        getMostFrequentlyKeywordsFromLocal.getMostFrequentlyKeywords()

}