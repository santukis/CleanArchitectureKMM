package com.santukis.uievents.events

import com.santukis.uievents.OnUiEvent

class RequestDecorFitsSystemWindowsChange(
    val decorFitsSystemWindows: Boolean,
    val statusBarColor: Int
): OnUiEvent