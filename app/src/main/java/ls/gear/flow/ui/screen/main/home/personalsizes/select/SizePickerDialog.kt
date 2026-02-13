package ls.gear.flow.ui.screen.main.home.personalsizes.select

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.collections.immutable.toImmutableList
import org.koin.androidx.compose.koinViewModel
import ls.gear.flow.R
import ls.gear.flow.domain.model.TypicalSize
import ls.gear.flow.ui.screen.main.home.personalsizes.select.state.SizePickerAction
import ls.gear.flow.ui.screen.main.home.personalsizes.component.SizeNumberPicker

private const val DEFAULT_START_INDEX = 2
private const val DEFAULT_ITEM_HEIGHT = 48

@Composable
fun SizeSelectDialog(
    typicalSize: TypicalSize,
    initialSize: String,
    onDoneClick: (TypicalSize, String) -> Unit,
    onBackClick: () -> Unit,
    viewModel: SizePickerViewModel = koinViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val startIndex = if (typicalSize.value.contains(initialSize)) {
        typicalSize.value.indexOf(initialSize)
    } else {
        DEFAULT_START_INDEX
    }
    if (uiState.selectedSize.isEmpty()) {
        viewModel.dispatch(SizePickerAction.Select(typicalSize.value[startIndex]))
    }

    DisposableEffect(Unit) {
        onDispose { viewModel.dispatch(SizePickerAction.Clear) }
    }

    Dialog(
        onDismissRequest = { },
        properties = DialogProperties(
            dismissOnBackPress = true,
            dismissOnClickOutside = false
        )
    ) {
        BackHandler(true) {
            onBackClick()
        }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(16.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(MaterialTheme.colorScheme.background)
        ) {
            Text(
                text = typicalSize.sizeRange(),
                modifier = Modifier.padding(top = 24.dp)
            )
            Spacer(modifier = Modifier.height(24.dp))
            SizeNumberPicker(
                startIndex = startIndex,
                sizes = typicalSize.value.toImmutableList(),
                itemHeight = DEFAULT_ITEM_HEIGHT.dp,
                onSelected = {
                    viewModel.dispatch(SizePickerAction.Select(typicalSize.value[it]))
                }
            )
            Box(modifier = Modifier.fillMaxWidth()) {
                TextButton(
                    onClick = { onDoneClick(typicalSize, uiState.selectedSize) },
                    modifier = Modifier
                        .align(Alignment.CenterEnd)
                        .padding(8.dp)
                ) {
                    Text(
                        text = stringResource(id = R.string.done),
                        color = MaterialTheme.colorScheme.primary,
                    )
                }
            }
        }
    }
}
