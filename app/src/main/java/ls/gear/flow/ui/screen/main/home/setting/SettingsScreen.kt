package ls.gear.flow.ui.screen.main.home.setting

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.koin.androidx.compose.koinViewModel
import org.koin.compose.koinInject
import ls.gear.flow.R
import ls.gear.flow.ext.orFalse
import ls.gear.flow.ui.screen.base.MessageEffect
import ls.gear.flow.ui.screen.base.component.SpacerVertical
import ls.gear.flow.ui.screen.base.component.noRippleClickable
import ls.gear.flow.ui.screen.base.component.noRippleLongClickable
import ls.gear.flow.ui.screen.main.home.component.HomeScreenHeader
import ls.gear.flow.ui.screen.main.home.setting.component.SettingCard
import ls.gear.flow.ui.screen.main.home.setting.component.SettingInfoRow
import ls.gear.flow.ui.screen.main.home.setting.component.SettingSwitchRow
import ls.gear.flow.ui.screen.main.home.setting.state.SettingsAction
import ls.gear.flow.ui.snackbar.SnackbarManager
import ls.gear.flow.ui.snackbar.toSnackbarMessage

private const val APP_VERSION_CLICK_COUNT = 4

@Composable
fun SettingsScreen(
    viewModel: SettingsViewModel = koinViewModel(),
    snackbarManager: SnackbarManager = koinInject()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val context = LocalContext.current
    var clicked by remember { mutableStateOf(0) }

    MessageEffect(
        message = uiState.message,
        onConsumed = { viewModel.dispatch(SettingsAction.ClearMessage) }
    ) {
        snackbarManager.showSnackbar(it.toSnackbarMessage(context))
    }

    Column(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.background)
            .fillMaxSize()
    ) {
        HomeScreenHeader(stringResId = R.string.settings)
        SpacerVertical(height = 24.dp)
        SettingCard {
            Column {
                SettingInfoRow(
                    iconResId = R.drawable.ic_user_id,
                    titleResId = R.string.user,
                    infoText = uiState.user?.id ?: stringResource(id = R.string.user_absent_title),
                    modifier = Modifier.noRippleLongClickable {
                        viewModel.dispatch(SettingsAction.CopyUserId)
                    }
                )
                Divider(thickness = 0.5.dp)
                SettingInfoRow(
                    iconResId = R.drawable.ic_version,
                    titleResId = R.string.app_version,
                    infoText = uiState.appVersion.name,
                    modifier = Modifier.noRippleClickable {
                        if (clicked < APP_VERSION_CLICK_COUNT) {
                            clicked++
                        } else {
                            viewModel.dispatch(SettingsAction.ShowDebugInfo)
                        }
                    }
                )
            }
        }
        SpacerVertical(height = 24.dp)
        if (uiState.biometricAvailable) {
            SettingCard {
                SettingSwitchRow(
                    iconResId = R.drawable.ic_touch_id,
                    titleResId = R.string.use_touch_id,
                    checked = uiState.settings?.useBioMetric.orFalse()
                ) {
                    viewModel.dispatch(SettingsAction.ChangeUseBioMetric(it))
                }
            }
        }
        if (uiState.showDebugInfo) {
            SpacerVertical(height = 24.dp)
            SettingCard {
                SettingInfoRow(
                    iconResId = R.drawable.ic_github,
                    titleResId = R.string.branch_name,
                    infoText = uiState.appVersion.gitBranchName
                )
                Divider(thickness = 0.5.dp)
                SettingInfoRow(
                    iconResId = R.drawable.ic_pull_request,
                    titleResId = R.string.last_commit_message,
                    infoText = uiState.appVersion.lastGitCommitMessage
                )
                Divider(thickness = 0.5.dp)
                SettingInfoRow(
                    iconResId = R.drawable.ic_hashcode,
                    titleResId = R.string.last_commit_hash,
                    infoText = uiState.appVersion.lastGitCommitHash
                )
            }
        }
    }
}
