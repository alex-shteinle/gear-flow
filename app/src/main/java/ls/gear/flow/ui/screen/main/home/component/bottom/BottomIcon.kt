package ls.gear.flow.ui.screen.main.home.component.bottom

import androidx.annotation.DrawableRes
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource

@Composable
fun BottomIcon(@DrawableRes iconResId: Int) {
    Icon(
        painter = painterResource(id = iconResId),
        contentDescription = null,
    )
}
