package ls.gear.flow.ui.screen.main.home.order

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.collections.immutable.toImmutableList
import org.koin.androidx.compose.koinViewModel
import org.koin.compose.koinInject
import ls.gear.flow.R
import ls.gear.flow.navigation.Destination
import ls.gear.flow.navigation.NavOptionType
import ls.gear.flow.navigation.navigator.GearFlowNavigator
import ls.gear.flow.ui.screen.base.MessageEffect
import ls.gear.flow.ui.screen.base.component.SpacerVertical
import ls.gear.flow.ui.screen.main.home.component.HomeButton
import ls.gear.flow.ui.screen.main.home.component.HomeScreenHeader
import ls.gear.flow.ui.screen.main.home.component.LoadingStub
import ls.gear.flow.ui.screen.main.home.component.NoInternetStub
import ls.gear.flow.ui.screen.main.home.component.NoItemsToOrderStub
import ls.gear.flow.ui.screen.main.home.order.component.OrderGroupRow
import ls.gear.flow.ui.screen.main.home.component.SomethingWrongStub
import ls.gear.flow.ui.screen.main.home.order.state.OrderAction
import ls.gear.flow.ui.screen.main.home.order.state.OrderItem
import ls.gear.flow.ui.screen.main.home.order.state.groupsWithSelectedItems
import ls.gear.flow.ui.screen.main.home.order.state.hasSelectedItems
import ls.gear.flow.ui.screen.main.home.orderconfirm.ConfirmOrderScreen
import ls.gear.flow.ui.snackbar.SnackbarManager
import ls.gear.flow.ui.snackbar.toSnackbarMessage

@Composable
fun OrderScreen(
    viewModel: OrderViewModel = koinViewModel(),
    navigator: GearFlowNavigator = koinInject(),
    snackbarManager: SnackbarManager = koinInject()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val context = LocalContext.current
    val onCheckedChanged: (OrderItem, Boolean) -> Unit = { item, checked ->
        viewModel.dispatch(OrderAction.Update.Checked(item, checked))
    }
    val onPlusClick: (OrderItem) -> Unit = { item ->
        viewModel.dispatch(OrderAction.Update.Plus(item))
    }
    val onMinusClick: (OrderItem) -> Unit = { item ->
        viewModel.dispatch(OrderAction.Update.Minus(item))
    }

    MessageEffect(
        message = uiState.message,
        onConsumed = { viewModel.dispatch(OrderAction.ClearMessage) }
    ) {
        snackbarManager.showSnackbar(it.toSnackbarMessage(context))
    }

    if (uiState.showConfirm) {
        ConfirmOrderScreen(
            selectedItems = uiState.groups.groupsWithSelectedItems().toImmutableList(),
            onConfirmClick = {
                viewModel.dispatch(
                    OrderAction.HideConfirmDialog,
                    OrderAction.OrderItems
                )
            },
            onCancelClick = { viewModel.dispatch(OrderAction.HideConfirmDialog) }
        )
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        Column {
            HomeScreenHeader(stringResId = R.string.order)
            if (uiState.showContent) {
                LazyColumn(modifier = Modifier.weight(1f)) {
                    items(items = uiState.groups, key = { it.name }) {
                        OrderGroupRow(
                            group = it,
                            onGroupClick = { group ->
                                viewModel.dispatch(
                                    OrderAction.ChangeExpand(group.name, !group.expanded)
                                )
                            },
                            onCheckedChanged = onCheckedChanged,
                            onPlusClick = onPlusClick,
                            onMinusClick = onMinusClick
                        )
                    }

                    item { SpacerVertical(height = 16.dp) }
                }
                if (uiState.showContent) {
                    HomeButton(
                        enabled = uiState.groups.hasSelectedItems() && !uiState.showConfirm,
                        modifier = Modifier.padding(bottom = 20.dp),
                        text = stringResource(R.string.order),
                        onClick = {
                            viewModel.dispatch(OrderAction.ShowConfirmDialog)
                        }
                    )
                }
            }
        }
        when {
            uiState.showLoading -> LoadingStub()
            uiState.showNoInternetStub -> NoInternetStub {
                viewModel.dispatch(OrderAction.FetchItems)
            }
            uiState.showSomethingWrongStub -> SomethingWrongStub {
                viewModel.dispatch(OrderAction.FetchItems)
            }

            uiState.showNoItemsStub -> NoItemsToOrderStub {
                navigator.navigate(Destination.PropertyCard, NavOptionType.BottomItem)
            }
        }
    }
}
