package com.santukis.datasources.remote.entities

import com.santukis.datasources.remote.entities.ErrorDto

class ServerException(val error: ErrorDto): Exception()