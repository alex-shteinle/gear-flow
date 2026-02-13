package ls.gear.flow.ui.screen.main.auth.pincodesetup

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
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.koin.androidx.compose.koinViewModel
import org.koin.compose.koinInject
import ls.gear.flow.R
import ls.gear.flow.ui.screen.base.MessageEffect
import ls.gear.flow.ui.screen.main.auth.PinConst
import ls.gear.flow.ui.screen.main.auth.pincodesetup.component.DecoratedTextField
import ls.gear.flow.ui.screen.main.auth.pincodesetup.state.PinSetupAction
import ls.gear.flow.ui.screen.main.auth.pincodesetup.state.SetupState.CONFIRMED
import ls.gear.flow.ui.screen.main.auth.pincodesetup.state.SetupState.HIDE_TOUCH_ID
import ls.gear.flow.ui.screen.main.auth.pincodesetup.state.SetupState.INITIAL
import ls.gear.flow.ui.screen.main.auth.pincodesetup.state.SetupState.NEED_CONFIRM
import ls.gear.flow.ui.screen.main.auth.pincodesetup.state.SetupState.SHOW_TOUCH_ID
import ls.gear.flow.ui.screen.main.auth.pincodesetup.state.SetupState.SUCCESS
import ls.gear.flow.ui.screen.main.auth.touchid.TouchIdDialog
import ls.gear.flow.ui.toast.shortToast
import ls.gear.flow.util.biometric.BioMetricAuthManager

@Composable
fun PinCodeSetupScreen(
    viewModel: PinSetupViewModel = koinViewModel(),
    bioMetricAuthManager: BioMetricAuthManager = koinInject(),
    onPinSetupSuccess: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val context = LocalContext.current
    val focusRequester = FocusRequester()
    val bioMetricAvailable = bioMetricAuthManager.canUseBioMetricAuth(context)

    LaunchedEffect(uiState) {
        focusRequester.requestFocus()
        when (uiState.setupState) {
            INITIAL -> viewModel.dispatch(PinSetupAction.ValidatePin)
            NEED_CONFIRM -> viewModel.dispatch(PinSetupAction.CheckPinConfirm)
            CONFIRMED -> if (bioMetricAvailable) {
                viewModel.dispatch(PinSetupAction.ShowTouchIdDialog)
            } else {
                onPinSetupSuccess()
            }

            SHOW_TOUCH_ID, HIDE_TOUCH_ID -> Unit
            SUCCESS -> onPinSetupSuccess()
        }
    }

    MessageEffect(
        message = uiState.message,
        onConsumed = { viewModel.dispatch(PinSetupAction.ClearMessage) }
    ) {
        context.shortToast(it.messageResId)
    }

    if (uiState.setupState == SHOW_TOUCH_ID) {
        TouchIdDialog {
            viewModel.dispatch(
                PinSetupAction.HideTouchIdDialog,
                PinSetupAction.SaveTouchIdPref(it)
            )
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 32.dp, horizontal = 16.dp)
    ) {
        Text(
            text = stringResource(
                id = if (uiState.setupState == INITIAL) {
                    R.string.setup_pin
                } else {
                    R.string.confirm_pin_code
                }
            ),
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = stringResource(id = R.string.input_pin_to_secure_data),
            color = MaterialTheme.colorScheme.secondary,
            fontSize = 13.sp,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        Spacer(modifier = Modifier.height(16.dp))
        DecoratedTextField(
            value = uiState.actualPin,
            length = PinConst.PINCODE_LENGTH,
            onValueChange = {
                if (uiState.isNotOverFilled) viewModel.dispatch(PinSetupAction.UpdatePin(pin = it))
            },
            onSaved = {},
            modifier = Modifier
                .focusRequester(focusRequester)
                .align(Alignment.CenterHorizontally)
                .padding(vertical = 20.dp, horizontal = 8.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PinSetupScreenPreview() {
    PinCodeSetupScreen(onPinSetupSuccess = { })
}
