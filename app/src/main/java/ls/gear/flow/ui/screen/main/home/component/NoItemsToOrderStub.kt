package ls.gear.flow.ui.screen.main.home.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import ls.gear.flow.R
import ls.gear.flow.ui.theme.ErrorStubColor

@Composable
fun NoItemsToOrderStub(onClick: () -> Unit) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        ErrorStub(
            iconResId = R.drawable.ic_all_get,
            titleResId = R.string.all_property_received,
            buttonTextResId = R.string.to_property_card,
            titleColor = ErrorStubColor,
            buttonBackgroundColor = ErrorStubColor,
            modifier = Modifier.align(Alignment.Center),
            onClick = onClick
        )
    }
}
