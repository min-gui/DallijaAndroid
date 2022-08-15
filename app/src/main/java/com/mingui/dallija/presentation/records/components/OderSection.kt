package com.mingui.dallija.presentation.records.components

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.mingui.dallija.domain.util.OrderType
import com.mingui.dallija.domain.util.RecordOrder

@Composable
fun OderSection(
    modifier: Modifier = Modifier,
    recordOrder: RecordOrder = RecordOrder.StartTimeStamp(OrderType.Descending),
    onOrderChange: (RecordOrder) -> Unit
) {
    Column(
        modifier = modifier
    ) {
        DefaultRadioButton(
            text = "StratTime",
            selected = recordOrder is RecordOrder.StartTimeStamp,
            onSelect = { onOrderChange(RecordOrder.StartTimeStamp(recordOrder.orderType)) }
        )
        Spacer(modifier = Modifier.width(8.dp))
        DefaultRadioButton(
            text = "RunFace",
            selected = recordOrder is RecordOrder.RunFace,
            onSelect = { onOrderChange(RecordOrder.RunFace(recordOrder.orderType)) }
        )
        Spacer(modifier = Modifier.width(8.dp))
        DefaultRadioButton(
            text = "Distance",
            selected = recordOrder is RecordOrder.Distance,
            onSelect = { onOrderChange(RecordOrder.Distance(recordOrder.orderType)) }
        )
        Spacer(modifier = Modifier.width(16.dp))
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            DefaultRadioButton(
                text = "Ascending",
                selected = recordOrder.orderType is OrderType.Ascending,
                onSelect = {
                    onOrderChange(recordOrder.copy(OrderType.Ascending))
                }
            )
            Spacer(modifier = Modifier.width(8.dp))
            DefaultRadioButton(
                text = "Descending",
                selected = recordOrder.orderType is OrderType.Descending,
                onSelect = {
                    onOrderChange(recordOrder.copy(OrderType.Descending))
                }
            )
        }
    }
}