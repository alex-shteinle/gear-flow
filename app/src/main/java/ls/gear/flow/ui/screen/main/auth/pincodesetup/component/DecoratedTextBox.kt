package ls.gear.flow.ui.screen.main.auth.pincodesetup.component

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ls.gear.flow.ui.theme.BorderInactiveColor

@Composable
fun DecoratedTextBox(boxWidth: Dp, boxHeight: Dp, isFilled: Boolean) {
    Box(
        modifier = Modifier
            .size(boxWidth, boxHeight)
            .border(
                1.dp,
                color = if (isFilled) MaterialTheme.colorScheme.primary else BorderInactiveColor,
                shape = RoundedCornerShape(16.dp)
            ),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = if (isFilled) "•" else "",
            fontSize = 36.sp,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.bodySmall
        )
    }
}
