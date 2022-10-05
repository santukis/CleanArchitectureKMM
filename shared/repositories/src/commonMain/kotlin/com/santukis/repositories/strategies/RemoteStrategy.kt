package com.santukis.repositories.strategies

abstract class RemoteStrategy<Input, Output>: RepositoryStrategy<Input, Output> {

    protected abstract suspend fun loadFromRemote(input: Input): Output

    protected abstract suspend fun saveIntoLocal(output: Output): Output

    override suspend fun execute(input: Input): Output {
        val output = loadFromRemote(input)
        return saveIntoLocal(output)
    }
}