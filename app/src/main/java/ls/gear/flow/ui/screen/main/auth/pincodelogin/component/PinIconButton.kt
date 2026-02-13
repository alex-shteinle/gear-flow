package ls.gear.flow.ui.screen.main.auth.pincodelogin.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import ls.gear.flow.ui.screen.main.auth.PinConst

@Composable
fun PinCodeButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    content: @Composable () -> Unit
) {
    Box(modifier = Modifier.padding(12.dp)) {
        Box(
            modifier = modifier
                .clip(CircleShape)
                .background(color = MaterialTheme.colorScheme.background)
                .aspectRatio(1f)
                .size(PinConst.PIN_KEY_SIZE.dp)
                .align(Alignment.Center)
                .clickable(
                    onClick = onClick,
                    enabled = enabled,
                    role = Role.Button,
                    interactionSource = interactionSource,
                    indication = rememberRipple(
                        bounded = false,
                        radius = PinConst.PIN_KEY_SIZE.dp / 2,
                        color = MaterialTheme.colorScheme.primary.copy(alpha = 0.25f)
                    )
                ),
            contentAlignment = Alignment.Center
        ) {
            content()
        }
    }
}
