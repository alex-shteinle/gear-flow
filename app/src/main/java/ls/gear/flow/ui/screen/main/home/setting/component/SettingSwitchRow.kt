package ls.gear.flow.ui.screen.main.home.setting.component

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Switch
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import ls.gear.flow.ui.theme.BlackAlpha56

@Composable
fun SettingSwitchRow(
    @DrawableRes iconResId: Int,
    @StringRes titleResId: Int,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        Icon(
            painter = painterResource(id = iconResId),
            contentDescription = null,
            tint = BlackAlpha56,
            modifier = Modifier
                .size(36.dp)
                .padding(8.dp)
        )
        SettingTitle(titleResId = titleResId, modifier = Modifier.weight(1f))
        Switch(
            checked = checked,
            onCheckedChange = onCheckedChange,
            modifier = Modifier.padding(12.dp)
        )
    }
}
