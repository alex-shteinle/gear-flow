package ls.gear.flow.ui.screen.main.home.property.component

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import ls.gear.flow.domain.model.StuffItemGroup

@Composable
fun PropertyCardItems(
    groups: ImmutableList<StuffItemGroup>,
    allPropertyGroup: StuffItemGroup?,
    onGroupClick: (StuffItemGroup) -> Unit
) {
    GroupList(groups = groups) {
        onGroupClick(it)
    }
    Spacer(modifier = Modifier.height(24.dp))
    allPropertyGroup?.let { all ->
        GroupList(persistentListOf(all)) {
            onGroupClick(all)
        }
    }
}
