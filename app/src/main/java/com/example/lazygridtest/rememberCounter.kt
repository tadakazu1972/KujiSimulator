package com.example.lazygridtest

import androidx.compose.runtime.*

data class CounterState(
    val count: Int,
    val increment: () -> Unit
)

@Composable
fun rememberCounter(): CounterState {
    var count by remember { mutableStateOf(0) }
    return remember(count) {
        CounterState(
            count = count,
            increment = { count += 1 }
        )
    }
}