package ls.gear.flow.ui.screen.main.home.order.component

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.ui.graphics.vector.ImageVector

enum class QuantityButtonType(val icon: ImageVector) {
    PLUS(icon = Icons.Filled.Add),
    MINUS(icon = Icons.Filled.Remove)
}
