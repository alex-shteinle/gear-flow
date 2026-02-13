package ls.gear.flow.ui.screen.main.home.component.bottom

import androidx.annotation.StringRes
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp

@Composable
fun BottomLabel(@StringRes stringResId: Int, selected: Boolean) {
    Text(
        text = stringResource(id = stringResId),
        fontSize = 9.sp,
        letterSpacing = 0.5.sp,
        lineHeight = 16.sp,
        fontWeight = if (selected) FontWeight.W600 else FontWeight.W500,
        textAlign = TextAlign.Center,
        softWrap = false
    )
}
