package ls.gear.flow.ui.screen.main.home.property.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlinx.collections.immutable.ImmutableList
import ls.gear.flow.domain.model.StuffItemGroup

@Composable
fun GroupList(groups: ImmutableList<StuffItemGroup>, onGroupClick: (StuffItemGroup) -> Unit) {
    Card(
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            groups.forEachIndexed { index, group ->
                GroupRow(
                    group = group,
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .clickable { onGroupClick(group) }
                )
                if (index < groups.lastIndex) {
                    Divider()
                }
            }
        }
    }
}
