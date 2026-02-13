package ls.gear.flow.ui.screen.main.splash

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.koin.androidx.compose.koinViewModel
import org.koin.compose.koinInject
import ls.gear.flow.R
import ls.gear.flow.navigation.Destination
import ls.gear.flow.navigation.NavOptionType
import ls.gear.flow.navigation.navigator.GearFlowNavigator
import ls.gear.flow.ui.screen.main.splash.state.SplashAction
import ls.gear.flow.ui.screen.main.splash.state.SplashState
import ls.gear.flow.ui.theme.robotoFlexFontFamily
import java.util.Locale

@Composable
fun SplashScreen(
    viewModel: SplashViewModel = koinViewModel(),
    navigator: GearFlowNavigator = koinInject()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(uiState) {
        when (uiState) {
            SplashState.Initial -> viewModel.dispatch(SplashAction.CheckIfUserPresent)
            SplashState.NoPinCode -> {
                navigator.navigate(Destination.PinCodeSetup, NavOptionType.ClearStack)
            }
            SplashState.NoUser -> navigator.navigate(Destination.NoUser, NavOptionType.ClearStack)
            SplashState.PinCodeIsPresent -> {
                navigator.navigate(Destination.PinCodeLogin, NavOptionType.ClearStack)
            }
            SplashState.UserIsPresent -> viewModel.dispatch(SplashAction.CheckIfPinCodeExists)
        }
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Box(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = stringResource(id = R.string.app_name),
                fontSize = 64.sp,
                fontFamily = robotoFlexFontFamily,
                color = MaterialTheme.colorScheme.onBackground,
                textAlign = TextAlign.Center
            )
        }
    }
}
