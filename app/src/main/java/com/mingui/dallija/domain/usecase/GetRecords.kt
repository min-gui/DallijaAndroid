package com.mingui.dallija.domain.usecase

import com.mingui.dallija.domain.model.Record
import com.mingui.dallija.domain.repository.RecordRepository
import com.mingui.dallija.domain.util.OrderType
import com.mingui.dallija.domain.util.RecordOrder
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetRecords(
    private val repository: RecordRepository
) {
    //스프링에서 서비스 같은 느낌

    operator fun invoke(
        recordOrder: RecordOrder = RecordOrder.StartTimeStamp(OrderType.Ascending)
    ): Flow<List<Record>> {
        return repository.getRecords().map { records ->
            when (recordOrder.orderType) {
                is OrderType.Ascending -> {
                    when(recordOrder){
                        is RecordOrder.StartTimeStamp -> records.sortedBy {
                            it.startTimeStamp
                        }
                        is RecordOrder.Distance -> records.sortedBy {
                            it.distance
                        }
                        is RecordOrder.RunFace -> records.sortedBy {
                            it.runface
                        }
                    }
                }
                is OrderType.Descending -> {
                    when(recordOrder){
                        is RecordOrder.StartTimeStamp -> records.sortedByDescending {
                            it.startTimeStamp
                        }
                        is RecordOrder.Distance -> records.sortedByDescending {
                            it.distance
                        }
                        is RecordOrder.RunFace -> records.sortedByDescending {
                            it.runface
                        }
                    }
                }
            }
        }
    }

}