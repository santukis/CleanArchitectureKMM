package com.santukis.home.stateholders

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import com.santukis.theme.selectedDotColor
import com.santukis.theme.unselectedDotColor

@Composable
fun rememberDotColorState(
    selectedColor: Color = MaterialTheme.selectedDotColor(),
    unselectedColor: Color = MaterialTheme.unselectedDotColor()
) = remember(
    selectedColor,
    unselectedColor
) {
    DotColorHolder(
        selectedColor = selectedColor,
        unselectedColor = unselectedColor
    )
}

class DotColorHolder(
    val selectedColor: Color,
    val unselectedColor: Color
)