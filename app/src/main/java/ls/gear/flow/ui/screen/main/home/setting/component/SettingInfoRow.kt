package ls.gear.flow.ui.screen.main.home.setting.component

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ls.gear.flow.ui.screen.base.component.SpacerHorizontal
import ls.gear.flow.ui.theme.BlackAlpha38
import ls.gear.flow.ui.theme.BlackAlpha56

@Composable
fun SettingInfoRow(
    @StringRes titleResId: Int,
    infoText: String,
    modifier: Modifier = Modifier,
    @DrawableRes iconResId: Int? = null,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier.fillMaxWidth()
    ) {
        if (iconResId == null) {
            SpacerHorizontal(width = 44.dp)
        } else {
            Icon(
                painter = painterResource(id = iconResId),
                contentDescription = null,
                tint = BlackAlpha56,
                modifier = Modifier
                    .size(36.dp)
                    .padding(8.dp)
            )
        }
        Column(modifier = Modifier.padding(vertical = 6.dp)) {
            SettingTitle(titleResId)
            Text(
                text = infoText,
                color = BlackAlpha38,
                fontSize = 12.sp,
                letterSpacing = 0.4.sp
            )
        }
    }
}
