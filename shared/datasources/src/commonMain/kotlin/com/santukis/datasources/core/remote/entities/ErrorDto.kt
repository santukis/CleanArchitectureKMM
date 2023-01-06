package com.santukis.datasources.core.remote.entities

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ErrorDto(
    @SerialName("status_code")
    val statusCode: Int? = null,
    @SerialName("status_message")
    val statusMessage: String? = null,
    @SerialName("success")
    val success: Boolean? = null
)