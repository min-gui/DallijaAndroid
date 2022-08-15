package com.mingui.dallija.domain.util

sealed class OrderType {
    object Ascending: OrderType()
    object Descending: OrderType()
}
