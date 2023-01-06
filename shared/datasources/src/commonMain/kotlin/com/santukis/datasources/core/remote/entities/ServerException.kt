package com.santukis.datasources.core.remote.entities

import com.santukis.datasources.core.remote.entities.ErrorDto

class ServerException(val error: ErrorDto): Exception()