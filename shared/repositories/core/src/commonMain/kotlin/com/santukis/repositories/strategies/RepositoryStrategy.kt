package com.santukis.repositories.strategies

interface RepositoryStrategy<Input, Output> {
    suspend fun execute(input: Input): Output
}