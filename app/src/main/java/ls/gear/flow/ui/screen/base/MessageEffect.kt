package ls.gear.flow.ui.screen.base

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.NonRestartableComposable

@Composable
@NonRestartableComposable
fun MessageEffect(message: Message?, onConsumed: () -> Unit, action: (Message) -> Unit) {
    LaunchedEffect(key1 = message, key2 = onConsumed) {
        message?.let {
            action(it)
            onConsumed()
        }
    }
}
