package ls.gear.flow.ui.screen.main.home.property.component

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import ls.gear.flow.R
import ls.gear.flow.ui.screen.main.home.component.ErrorStub
import ls.gear.flow.ui.theme.NoItemsTextColor
import ls.gear.flow.ui.theme.GearFlowTheme

@Composable
fun NoItemsStub(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    ErrorStub(
        iconResId = R.drawable.ic_no_records,
        titleResId = R.string.records_are_absent,
        buttonTextResId = R.string.order,
        titleColor = NoItemsTextColor,
        buttonBackgroundColor = MaterialTheme.colorScheme.primary,
        modifier = modifier,
        onClick = onClick
    )
}

@Preview(showBackground = true)
@Composable
fun NoItemsStubPreview() {
    GearFlowTheme {
        NoItemsStub(onClick = { })
    }
}
