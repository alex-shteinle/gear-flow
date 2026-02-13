package ls.gear.flow.ui.screen.main.home.groupdetail.component

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Composable
fun ItemValueText(name: String, color: Color, modifier: Modifier = Modifier) {
    Text(
        text = name,
        color = color,
        fontSize = 14.sp,
        fontWeight = FontWeight.W500,
        modifier = modifier
    )
}
