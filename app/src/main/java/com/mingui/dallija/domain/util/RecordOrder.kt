package com.mingui.dallija.domain.util

sealed class RecordOrder(val orderType: OrderType) {
    class StartTimeStamp(orderType: OrderType): RecordOrder(orderType)
    class RunFace(orderType: OrderType): RecordOrder(orderType)
    class Distance(orderType: OrderType): RecordOrder(orderType)

    fun copy(orderType: OrderType): RecordOrder{

        return when(this){
            is StartTimeStamp -> StartTimeStamp(orderType)
            is RunFace -> RunFace(orderType)
            is Distance -> Distance(orderType)
        }
    }

}
