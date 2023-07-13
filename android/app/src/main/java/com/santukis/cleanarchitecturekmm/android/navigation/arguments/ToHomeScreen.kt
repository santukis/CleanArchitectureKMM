package com.santukis.cleanarchitecturekmm.android.navigation.arguments

import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Movie
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.santukis.cleanarchitecturekmm.android.R
import com.santukis.navigation.BottomNavigationDestinationArguments

object ToHomeScreen : BottomNavigationDestinationArguments {

    override fun getRoute(): String = "home"

    @Composable
    override fun BottomNavigationItemIcon() {
        Icon(
            imageVector = Icons.Filled.Movie,
            contentDescription = ""
        )
    }

    @Composable
    override fun BottomNavigationItemTitle() {
        Text(
            text =  stringResource(id = R.string.movies)
        )
    }
}