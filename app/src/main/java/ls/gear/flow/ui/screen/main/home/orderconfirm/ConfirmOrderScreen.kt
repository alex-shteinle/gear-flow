package ls.gear.flow.ui.screen.main.home.orderconfirm

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList
import ls.gear.flow.R
import ls.gear.flow.ui.screen.base.component.SpacerVertical
import ls.gear.flow.ui.screen.main.home.component.HomeButton
import ls.gear.flow.ui.screen.main.home.component.HomeScreenHeader
import ls.gear.flow.ui.screen.main.home.order.state.OrderGroup
import ls.gear.flow.ui.screen.main.home.orderconfirm.component.ConfirmOrderItemsList
import ls.gear.flow.ui.screen.main.home.orderconfirm.component.GroupNameText
import ls.gear.flow.ui.theme.BorderInactiveColor
import java.util.Locale

@Composable
fun ConfirmOrderScreen(
    selectedItems: ImmutableList<OrderGroup>,
    onConfirmClick: () -> Unit = { },
    onCancelClick: () -> Unit = { }
) {
    Dialog(
        onDismissRequest = {},
        properties = DialogProperties(
            dismissOnBackPress = true,
            dismissOnClickOutside = false
        )
    ) {
        Column(
            modifier = Modifier
                .padding(bottom = 20.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(MaterialTheme.colorScheme.background)
        ) {
            HomeScreenHeader(stringResId = R.string.your_order)
            SpacerVertical(height = 16.dp)
            LazyColumn(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .weight(1f)
            ) {
                items(items = selectedItems) { group ->
                    GroupNameText(text = group.name.uppercase(Locale.getDefault()))
                    SpacerVertical(height = 4.dp)
                    Card(
                        shape = RoundedCornerShape(10.dp),
                        colors = CardDefaults.cardColors(containerColor = Color.White),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        ConfirmOrderItemsList(items = group.items.toImmutableList())
                    }
                    SpacerVertical(height = 16.dp)
                }
            }
            HomeButton(text = stringResource(id = R.string.order)) {
                onConfirmClick()
            }
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                border = BorderStroke(width = 1.dp, color = BorderInactiveColor),
                shape = RoundedCornerShape(16.dp),
                onClick = { onCancelClick() },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, end = 20.dp, bottom = 20.dp)
            ) {
                Text(
                    text = stringResource(R.string.cancel),
                    color = Color.Black,
                    fontSize = 14.sp,
                    lineHeight = 18.sp,
                    modifier = Modifier.padding(4.dp)
                )
            }
        }
    }
}
