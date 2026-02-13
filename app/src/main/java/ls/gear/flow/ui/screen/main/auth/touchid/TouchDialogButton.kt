package ls.gear.flow.ui.screen.main.auth.touchid

import androidx.annotation.StringRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ls.gear.flow.R
import ls.gear.flow.ui.theme.BorderInactiveColor
import ls.gear.flow.ui.theme.GearFlowTheme

@Composable
fun TouchDialogButton(
    @StringRes stringId: Int,
    isAccented: Boolean,
    onClick: () -> Unit
) {
    val buttonColors = if (isAccented) {
        ButtonDefaults.buttonColors()
    } else {
        ButtonDefaults.buttonColors(containerColor = Color.Transparent)
    }
    val border = if (isAccented) null else BorderStroke(width = 1.dp, color = BorderInactiveColor)
    Button(
        colors = buttonColors,
        border = border,
        shape = RoundedCornerShape(16.dp),
        onClick = onClick,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = stringResource(stringId),
            color = if (isAccented) MaterialTheme.colorScheme.onPrimary else Color.Black,
            fontSize = 14.sp,
            lineHeight = 18.sp,
            modifier = Modifier.padding(4.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun TouchDialogButtonPreview() {
    GearFlowTheme {
        TouchDialogButton(
            stringId = R.string.enable_touch_id,
            isAccented = true,
            onClick = {}
        )
    }
}
