package ls.gear.flow.ui.screen.main.home.property.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import ls.gear.flow.R
import ls.gear.flow.domain.model.StuffItemGroup
import ls.gear.flow.ui.screen.base.component.SpacerVertical
import ls.gear.flow.ui.screen.main.home.component.ItemNameText
import ls.gear.flow.ui.screen.main.home.component.QuantityText
import ls.gear.flow.ui.screen.main.home.property.state.generalQuantity
import ls.gear.flow.ui.screen.main.home.property.state.iconRes
import ls.gear.flow.ui.screen.main.home.property.state.nameRes
import ls.gear.flow.ui.theme.BlackAlpha56
import java.util.Locale

@Composable
fun GroupRow(group: StuffItemGroup, modifier: Modifier = Modifier) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp)
    ) {
        Icon(
            painter = painterResource(id = group.type.iconRes),
            contentDescription = null,
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column(modifier = Modifier.weight(1f)) {
            ItemNameText(text = stringResource(group.type.nameRes).uppercase(Locale.getDefault()))
            SpacerVertical(4.dp)
            QuantityText(text = stringResource(id = R.string.items_quantity, group.generalQuantity))
        }
        Icon(
            imageVector = Icons.Default.ChevronRight,
            tint = BlackAlpha56,
            contentDescription = null
        )
    }
}
