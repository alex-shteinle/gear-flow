package ls.gear.flow.ui.screen.main.auth.pincodelogin.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ls.gear.flow.ui.theme.BorderInactiveColor
import ls.gear.flow.ui.theme.GearFlowTheme

@Composable
fun PinIndicator(filled: Boolean) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .padding(4.dp)
            .size(12.dp)
            .clip(CircleShape)
            .background(Color.Transparent)
            .border(1.dp, BorderInactiveColor, CircleShape)
    ) {
        if (filled) {
            Box(
                modifier = Modifier
                    .size(8.dp)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.primary)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PinIndicatorPreview() {
    GearFlowTheme {
        PinIndicator(filled = true)
    }
}
