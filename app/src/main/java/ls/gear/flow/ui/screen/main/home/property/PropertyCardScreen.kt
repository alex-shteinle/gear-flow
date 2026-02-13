package ls.gear.flow.ui.screen.main.home.property

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.collections.immutable.toImmutableList
import org.koin.androidx.compose.koinViewModel
import org.koin.compose.koinInject
import ls.gear.flow.R
import ls.gear.flow.navigation.Destination
import ls.gear.flow.navigation.NavOptionType
import ls.gear.flow.navigation.navigator.GearFlowNavigator
import ls.gear.flow.ui.screen.base.MessageEffect
import ls.gear.flow.ui.screen.main.home.component.HomeScreenHeader
import ls.gear.flow.ui.screen.main.home.component.NoInternetStub
import ls.gear.flow.ui.screen.main.home.component.SomethingWrongStub
import ls.gear.flow.ui.screen.main.home.property.component.NoItemsStub
import ls.gear.flow.ui.screen.main.home.property.component.PropertyCardItems
import ls.gear.flow.ui.screen.main.home.property.state.PropertyCardAction
import ls.gear.flow.ui.snackbar.SnackbarManager
import ls.gear.flow.ui.snackbar.toSnackbarMessage

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun PropertyCardScreen(
    viewModel: PropertyCardViewModel = koinViewModel(),
    navigator: GearFlowNavigator = koinInject(),
    snackbarManager: SnackbarManager = koinInject()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val pullRefreshState = rememberPullRefreshState(
        refreshing = uiState.loading,
        onRefresh = {
            viewModel.dispatch(PropertyCardAction.Reload)
        }
    )
    val context = LocalContext.current

    MessageEffect(
        message = uiState.message,
        onConsumed = { viewModel.dispatch(PropertyCardAction.ClearMessage) },
    ) {
        snackbarManager.showSnackbar(it.toSnackbarMessage(context))
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .pullRefresh(pullRefreshState)
    ) {
        LazyColumn {
            item { HomeScreenHeader(stringResId = R.string.property_card) }
            item {
                if (uiState.showContent) {
                    PropertyCardItems(
                        groups = uiState.groups.toImmutableList(),
                        allPropertyGroup = uiState.allProperty,
                        onGroupClick = { navigator.navigate(Destination.ItemGroupDetails(it.type)) }
                    )
                }
            }
        }
        when {
            uiState.showNoInternetStub -> NoInternetStub {
                viewModel.dispatch(PropertyCardAction.Reload)
            }

            uiState.showSomethingWrongStub -> SomethingWrongStub {
                viewModel.dispatch(PropertyCardAction.Reload)
            }

            uiState.showNoItemsStub -> NoItemsStub(modifier = Modifier.align(Alignment.Center)) {
                navigator.navigate(Destination.Order, NavOptionType.BottomItem)
            }
        }
        PullRefreshIndicator(
            refreshing = uiState.loading,
            state = pullRefreshState,
            modifier = Modifier.align(Alignment.TopCenter)
        )
    }
}
