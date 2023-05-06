package com.santukis.navigation.destination

import androidx.compose.runtime.Composable

interface DecoratedDestination: Destination {

    @Composable
    fun DestinationLabel()

    @Composable
    fun DestinationIcon()
}