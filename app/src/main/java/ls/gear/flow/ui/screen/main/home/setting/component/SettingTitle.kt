package ls.gear.flow.ui.screen.main.home.setting.component

import androidx.annotation.StringRes
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import ls.gear.flow.ui.theme.BlackAlpha87

@Composable
fun SettingTitle(@StringRes titleResId: Int, modifier: Modifier = Modifier) {
    Text(
        text = stringResource(id = titleResId),
        color = BlackAlpha87,
        fontSize = 14.sp,
        letterSpacing = 0.17.sp,
        modifier = modifier
    )
}
