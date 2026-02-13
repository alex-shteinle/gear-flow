package ls.gear.flow.ui.screen.main.home.component

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ls.gear.flow.ui.screen.base.component.SpacerVertical

private const val IMAGE_WIDTH_FRACTION = 0.5f

@Composable
fun ErrorStub(
    @DrawableRes iconResId: Int,
    @StringRes titleResId: Int,
    @StringRes buttonTextResId: Int,
    titleColor: Color,
    buttonBackgroundColor: Color,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.padding(horizontal = 16.dp)
    ) {
        Image(
            painter = painterResource(id = iconResId),
            contentDescription = null,
            modifier = Modifier.fillMaxWidth(IMAGE_WIDTH_FRACTION)
        )
        SpacerVertical(height = 16.dp)
        Text(
            text = stringResource(id = titleResId),
            fontSize = 24.sp,
            color = titleColor,
            textAlign = TextAlign.Center
        )
        SpacerVertical(height = 64.dp)
        ErrorButton(
            text = stringResource(id = buttonTextResId),
            backgroundColor = buttonBackgroundColor,
        ) {
            onClick()
        }
    }
}
