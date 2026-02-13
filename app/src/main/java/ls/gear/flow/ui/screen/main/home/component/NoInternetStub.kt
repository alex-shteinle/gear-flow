package ls.gear.flow.ui.screen.main.home.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import ls.gear.flow.R
import ls.gear.flow.ui.theme.ErrorStubColor

@Composable
fun NoInternetStub(onClick: () -> Unit) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        ErrorStub(
            iconResId = R.drawable.ic_no_internet,
            titleResId = R.string.no_internet,
            buttonTextResId = R.string.try_again,
            titleColor = ErrorStubColor,
            buttonBackgroundColor = ErrorStubColor,
            modifier = Modifier.align(Alignment.Center),
            onClick = onClick
        )
    }
}
