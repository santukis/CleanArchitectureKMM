package com.santukis.repositories.strategies

abstract class LocalStrategy<Input, Output>: RepositoryStrategy<Input, Output> {

    protected abstract suspend fun loadFromLocal(input: Input): Output

    override suspend fun execute(input: Input): Output {
        return loadFromLocal(input)
    }
}