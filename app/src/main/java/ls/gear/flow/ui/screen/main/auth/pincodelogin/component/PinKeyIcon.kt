package ls.gear.flow.ui.screen.main.auth.pincodelogin.component

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp

@Composable
fun PinKeyIcon(
    @DrawableRes painterRes: Int,
    iconSize: Dp,
) {
    Icon(
        painter = painterResource(id = painterRes),
        tint = MaterialTheme.colorScheme.onBackground,
        contentDescription = null,
        modifier = Modifier.size(iconSize)
    )
}
