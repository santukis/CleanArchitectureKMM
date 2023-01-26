package com.santukis.cleanarchitecturekmm.android.core.events


interface OnUiEvent

class RequestDecorFitsSystemWindowsChange(
    val decorFitsSystemWindows: Boolean,
    val statusBarColor: Int
): OnUiEvent