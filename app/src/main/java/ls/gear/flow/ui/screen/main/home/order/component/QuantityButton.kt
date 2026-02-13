package ls.gear.flow.ui.screen.main.home.order.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import ls.gear.flow.ui.theme.QuantityButtonBackgroundColor

@Composable
fun QuantityButton(type: QuantityButtonType, enabled: Boolean, onClick: () -> Unit) {
    val iconColor = when (type) {
        QuantityButtonType.PLUS -> MaterialTheme.colorScheme.primary
        QuantityButtonType.MINUS -> Color.Black.copy(alpha = 0.25f)
    }
    IconButton(onClick = { onClick() }, enabled = enabled) {
        Box(
            modifier = Modifier
                .size(24.dp)
                .clip(CircleShape)
                .background(QuantityButtonBackgroundColor),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = type.icon,
                contentDescription = null,
                tint = iconColor,
                modifier = Modifier.size(16.dp)
            )
        }
    }
}
