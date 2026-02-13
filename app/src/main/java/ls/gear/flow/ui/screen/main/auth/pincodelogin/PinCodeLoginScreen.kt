package ls.gear.flow.ui.screen.main.auth.pincodelogin

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.koin.androidx.compose.koinViewModel
import org.koin.compose.koinInject
import ls.gear.flow.R
import ls.gear.flow.ui.screen.base.MessageEffect
import ls.gear.flow.ui.screen.main.auth.PinConst
import ls.gear.flow.ui.screen.main.auth.pincodelogin.component.PinCodeKeypad
import ls.gear.flow.ui.screen.main.auth.pincodelogin.component.PinRow
import ls.gear.flow.ui.screen.main.auth.pincodelogin.state.PinCodeLoginAction
import ls.gear.flow.ui.screen.main.auth.pincodelogin.state.PinCodeLoginState
import ls.gear.flow.ui.snackbar.SnackbarManager
import ls.gear.flow.ui.snackbar.toSnackbarMessage
import ls.gear.flow.ui.theme.GearFlowTheme
import ls.gear.flow.ui.theme.robotoFlexFontFamily
import ls.gear.flow.util.biometric.BioMetricAuthManager
import java.util.Locale

@Composable
fun PinCodeLoginScreen(
    viewModel: PinCodeLoginViewModel = koinViewModel(),
    bioMetricAuthManager: BioMetricAuthManager = koinInject(),
    snackbarManager: SnackbarManager = koinInject(),
    onLoginSuccess: () -> Unit
) {
    val uiState: PinCodeLoginState by viewModel.uiState.collectAsStateWithLifecycle()
    val context = LocalContext.current
    val activity = context as? FragmentActivity

    LaunchedEffect(uiState) {
        if (uiState.showBiometric && activity is FragmentActivity) {
            bioMetricAuthManager.initBioMetricPrompt(activity) {
                if (it.isSuccess) {
                    onLoginSuccess()
                } else {
                    viewModel.dispatch(PinCodeLoginAction.BioMetricCancel)
                }
            }
        }
        if (uiState.pinIsFilled) viewModel.dispatch(PinCodeLoginAction.PinCodeFilled)
        if (uiState.matched) onLoginSuccess()
    }

    MessageEffect(
        message = uiState.message,
        onConsumed = { viewModel.dispatch(PinCodeLoginAction.ClearMessage) }
    ) {
        snackbarManager.showSnackbar(it.toSnackbarMessage(context))
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 45.dp)
    ) {
        Spacer(modifier = Modifier.height(86.dp))
        Text(
            text = stringResource(id = R.string.app_name).uppercase(Locale.getDefault()),
            fontSize = 48.sp,
            lineHeight = 56.sp,
            fontFamily = robotoFlexFontFamily,
            color = MaterialTheme.colorScheme.onBackground,
        )
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = stringResource(id = R.string.input_your_pin),
            fontSize = 16.sp,
            color = MaterialTheme.colorScheme.onBackground,
        )
        Spacer(modifier = Modifier.height(20.dp))
        PinRow(value = uiState.pin, length = PinConst.PINCODE_LENGTH, rowWidth = 84.dp)
        Spacer(modifier = Modifier.height(48.dp))
        PinCodeKeypad(hasBiometric = uiState.biometricAvailable, onNumberClick = {
            if (!uiState.pinIsFilled) {
                viewModel.dispatch(PinCodeLoginAction.NumberClick(it))
            }
        }, onBackSpaceClick = {
            if (uiState.pin.isNotEmpty()) {
                viewModel.dispatch(PinCodeLoginAction.BackSpaceClick)
            }
        }, onBioMetricClick = { viewModel.dispatch(PinCodeLoginAction.BioMetricClick) })
    }
}

@Preview(showBackground = true)
@Composable
fun PinCodeLoginScreenPreview() {
    GearFlowTheme {
        PinCodeLoginScreen {}
    }
}
