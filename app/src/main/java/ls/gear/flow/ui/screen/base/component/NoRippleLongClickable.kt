package ls.gear.flow.ui.screen.base.component

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed

@OptIn(ExperimentalFoundationApi::class)
inline fun Modifier.noRippleLongClickable(
    noinline onLongClick: () -> Unit
): Modifier = composed {
    combinedClickable(
        indication = null,
        interactionSource = remember { MutableInteractionSource() },
        onClick = { },
        onLongClick = onLongClick
    )
}
