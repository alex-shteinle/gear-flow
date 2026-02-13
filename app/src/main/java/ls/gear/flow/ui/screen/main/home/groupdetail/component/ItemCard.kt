package ls.gear.flow.ui.screen.main.home.groupdetail.component

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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import kotlinx.collections.immutable.toImmutableList
import ls.gear.flow.R
import ls.gear.flow.domain.model.StuffItem
import ls.gear.flow.ui.screen.base.component.SpacerVertical
import ls.gear.flow.util.ext.toStringByPattern
import java.util.Collections

@Composable
fun ItemCard(item: StuffItem) {
    Card(
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp)
        ) {
            ItemNameText(name = item.name, modifier = Modifier.padding(horizontal = 16.dp))
            SpacerVertical(height = 8.dp)
            Divider()
            ItemDetailRow(
                name = stringResource(id = R.string.standard),
                value = item.normQuantity.toString(),
                modifier = Modifier.padding(horizontal = 16.dp)
            )
            ItemDetailRow(
                name = stringResource(id = R.string.granted),
                value = item.quantity.toString(),
                modifier = Modifier.padding(horizontal = 16.dp)
            )
            Divider()
            ItemFinalDateRow(
                name = stringResource(id = R.string.final_usage_date),
                dates = Collections.nCopies(
                    item.quantity,
                    item.finalUsageDate.toStringByPattern("dd.MM.yyyy")
                ).toImmutableList(),
                modifier = Modifier.padding(horizontal = 16.dp)
            )
        }
    }
}
