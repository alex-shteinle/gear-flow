package ls.gear.flow.ui.screen.main.home.groupdetail

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.koin.androidx.compose.koinViewModel
import org.koin.compose.koinInject
import ls.gear.flow.navigation.navigator.GearFlowNavigator
import ls.gear.flow.ui.screen.main.home.groupdetail.component.ItemCard
import ls.gear.flow.ui.screen.main.home.groupdetail.component.ItemGroupDetailsTopbar
import ls.gear.flow.ui.screen.main.home.property.state.nameRes

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ItemGroupDetailScreen(
    viewModel: ItemGroupDetailViewModel = koinViewModel(),
    navigator: GearFlowNavigator = koinInject()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    uiState.group?.let { group ->
        Scaffold(
            topBar = {
                ItemGroupDetailsTopbar(stringResource(group.type.nameRes)) { navigator.back() }
            }
        ) {
            LazyColumn(
                contentPadding = PaddingValues(vertical = 8.dp),
                modifier = Modifier.padding(it)
            ) {
                items(group.items.size) { index ->
                    ItemCard(item = group.items[index])
                }
            }
        }
    }
}
