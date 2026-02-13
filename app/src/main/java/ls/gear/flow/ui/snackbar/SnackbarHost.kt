package ls.gear.flow.ui.snackbar

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ls.gear.flow.ui.screen.base.component.SpacerHorizontal

@Composable
fun MainSnackbarHost(hostState: SnackbarHostState) {
    SnackbarHost(hostState = hostState) { data ->
        val visuals = data.visuals as? GearFlowSnackbarVisuals
        visuals?.let {
            Card(
                shape = ShapeDefaults.Small,
                colors = CardDefaults.cardColors(containerColor = it.type.containerColor),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),

            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                ) {
                    visuals.drawableResId?.let { iconId ->
                        Icon(
                            painter = painterResource(id = iconId),
                            contentDescription = null,
                            tint = visuals.type.contentColor
                        )
                        SpacerHorizontal(width = 16.dp)
                    }
                    Text(
                        text = visuals.message,
                        fontSize = 16.sp,
                        color = visuals.type.contentColor
                    )
                }
            }
        } ?: Snackbar(snackbarData = data)
    }
}
