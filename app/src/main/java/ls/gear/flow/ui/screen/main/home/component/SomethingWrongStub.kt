package ls.gear.flow.ui.screen.main.home.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import ls.gear.flow.R
import ls.gear.flow.ui.theme.ErrorStubColor

@Composable
fun SomethingWrongStub(onClick: () -> Unit) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        ErrorStub(
            iconResId = R.drawable.ic_attention,
            titleResId = R.string.something_went_wrong,
            buttonTextResId = R.string.try_again,
            titleColor = ErrorStubColor,
            buttonBackgroundColor = ErrorStubColor,
            modifier = Modifier.align(Alignment.Center),
            onClick = onClick
        )
    }
}
