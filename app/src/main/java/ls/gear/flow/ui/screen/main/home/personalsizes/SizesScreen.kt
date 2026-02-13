package ls.gear.flow.ui.screen.main.home.personalsizes

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.koin.androidx.compose.koinViewModel
import org.koin.compose.koinInject
import ls.gear.flow.R
import ls.gear.flow.domain.model.TypicalSize
import ls.gear.flow.ui.screen.base.MessageEffect
import ls.gear.flow.ui.screen.main.home.component.HomeButton
import ls.gear.flow.ui.screen.main.home.component.HomeCard
import ls.gear.flow.ui.screen.main.home.component.HomeScreenHeader
import ls.gear.flow.ui.screen.main.home.personalsizes.component.SizeRow
import ls.gear.flow.ui.screen.main.home.personalsizes.select.SizeSelectDialog
import ls.gear.flow.ui.screen.main.home.personalsizes.state.SizesAction
import ls.gear.flow.ui.screen.main.home.personalsizes.state.sizeByTypical
import ls.gear.flow.ui.screen.main.home.personalsizes.state.toSizeAction
import ls.gear.flow.ui.snackbar.SnackbarManager
import ls.gear.flow.ui.snackbar.toSnackbarMessage

@Composable
fun SizesScreen(
    viewModel: SizesViewModel = koinViewModel(),
    snackbarManager: SnackbarManager = koinInject()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val context = LocalContext.current
    val onSizeClick: (TypicalSize) -> Unit = {
        viewModel.dispatch(SizesAction.ShowSizePicker(it))
    }

    uiState.typicalSizeToShow?.let {
        SizeSelectDialog(
            typicalSize = it,
            initialSize = uiState.sizeByTypical(it).value,
            onDoneClick = { typicalSize, selected ->
                viewModel.dispatch(typicalSize.toSizeAction(selected))
            },
            onBackClick = { viewModel.dispatch(SizesAction.HideSizePicker) }
        )
    }

    MessageEffect(
        message = uiState.message,
        onConsumed = { viewModel.dispatch(SizesAction.ClearMessage) }
    ) {
        snackbarManager.showSnackbar(it.toSnackbarMessage(context))
    }

    Column {
        HomeScreenHeader(stringResId = R.string.personal_sizes)
        Spacer(modifier = Modifier.height(16.dp))
        Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
            HomeCard {
                TypicalSize.instances
                    .map { uiState.sizeByTypical(it) }
                    .forEachIndexed { index, uiSize ->
                        SizeRow(uiSize) {
                            onSizeClick(it)
                        }
                        if (index < TypicalSize.instances.lastIndex) {
                            Divider(thickness = 0.5.dp)
                        }
                    }
            }
            Spacer(modifier = Modifier.height(36.dp))
            HomeButton(
                modifier = Modifier.padding(bottom = 20.dp),
                text = stringResource(R.string.save),
                enabled = uiState.changed
            ) {
                viewModel.dispatch(SizesAction.SaveSizes)
            }
        }
    }
}
