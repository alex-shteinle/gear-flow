package ls.gear.flow.ui.screen.main.home.groupdetail.component

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Composable
fun ItemNameText(name: String, modifier: Modifier = Modifier) {
    Text(
        text = name,
        color = MaterialTheme.colorScheme.onBackground,
        fontWeight = FontWeight.W500,
        fontSize = 20.sp,
        lineHeight = 32.sp,
        letterSpacing = 0.15.sp,
        modifier = modifier
    )
}
