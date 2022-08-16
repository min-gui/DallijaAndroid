package com.mingui.dallija.presentation.records

import androidx.compose.animation.*
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Sort
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.mingui.dallija.app.OnboardingScreen
import com.mingui.dallija.app.navigation.SetupNavGraph
import com.mingui.dallija.presentation.records.components.OderSection
import com.mingui.dallija.presentation.records.components.RecordItem
import com.mingui.dallija.presentation.theme.DallijaTheme
import kotlinx.coroutines.launch

@Composable
fun RecordScreen(
    navController: NavController,
    viewModel: RecordsViewModel = hiltViewModel()
) {
    val state = viewModel.state.value
    //remember 상태가 변화되도 데이터 초기화 안된다.
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {

                },
                backgroundColor = MaterialTheme.colors.primary
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add record")
            }
        },
        scaffoldState = scaffoldState

    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Your note",
                    style = MaterialTheme.typography.h4
                )
                IconButton(
                    onClick = {
                        viewModel.onEvent(RecordsEvent.ToggleOrderSection)

                    },
                ) {
                    Icon(
                        imageVector = Icons.Default.Sort,
                        contentDescription = "Sort"
                    )
                }
                AnimatedVisibility(
                    visible = state.isRecordSectionVisible,
                    enter = fadeIn() + slideInVertically(),
                    exit = fadeOut() + slideOutVertically()
                ) {
                    OderSection(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 16.dp),
                        recordOrder = state.recordOrder,
                        onOrderChange = {
                            viewModel.onEvent(RecordsEvent.Order(it))
                        }
                    )

                }
                Spacer(modifier = Modifier.height(16.dp))
                LazyColumn(modifier = Modifier.fillMaxSize()) {
                    items(state.records) { record ->
                        RecordItem(
                            record = record,
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    //아이템 클릭 했을경우 이동

                                },
                            onDeleteClick = {
                                viewModel.onEvent(RecordsEvent.DeleteRecord(record))
                                scope.launch {
                                    val result = scaffoldState.snackbarHostState.showSnackbar(
                                        message = "기록 삭제",
                                        actionLabel = "Undo"
                                    )
                                    if (result == SnackbarResult.ActionPerformed) {
                                        viewModel.onEvent(RecordsEvent.RestoreRecord)
                                    }
                                }
                            }
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                    }
                }

            }
        }
    }
    

}

@Preview(showBackground = true, widthDp = 320, heightDp = 320, name = "OnBoardingPreview")
@Composable
fun OnBoardingPreview() {
    DallijaTheme {
        val navController = rememberNavController()
                SetupNavGraph(navController = navController)
        RecordScreen(navController = navController)
    }
}