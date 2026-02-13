package ls.gear.flow.ui.screen.main

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import org.koin.androidx.compose.koinViewModel
import org.koin.compose.koinInject
import ls.gear.flow.navigation.Destination
import ls.gear.flow.navigation.NavOptionType
import ls.gear.flow.navigation.graph.MainNavGraph
import ls.gear.flow.navigation.navigator.GearFlowNavigator
import ls.gear.flow.ui.screen.base.Message
import ls.gear.flow.ui.screen.base.MessageEffect
import ls.gear.flow.ui.screen.base.component.ComposableLifecycle
import ls.gear.flow.ui.screen.main.home.component.bottom.HomeBottomNavigation
import ls.gear.flow.ui.screen.main.state.MainAction
import ls.gear.flow.ui.snackbar.MainSnackbarHost
import ls.gear.flow.ui.snackbar.SnackbarManager
import ls.gear.flow.ui.theme.GearFlowTheme

@OptIn(
    ExperimentalAnimationApi::class,
    ExperimentalMaterial3Api::class
)
@Composable
fun MainScreen(
    viewModel: MainViewModel = koinViewModel(),
    navigator: GearFlowNavigator = koinInject(),
    snackbarManager: SnackbarManager = koinInject(),
) {
    GearFlowTheme {
        val navController = rememberAnimatedNavController()
        navigator.setupNavController(navController)

        val snackbarHostState = remember { SnackbarHostState() }

        snackbarManager.setup(
            hostState = snackbarHostState,
            scope = rememberCoroutineScope()
        )

        val uiState by viewModel.uiState.collectAsStateWithLifecycle()

        ComposableLifecycle { _, event ->
            event.toMainAction()?.let {
                viewModel.dispatch(it)
            }
        }

        MessageEffect(
            message = uiState.message,
            onConsumed = { viewModel.dispatch(MainAction.ClearMessage) }
        ) {
            if (it is Message.Error.BackgroundTimeExpired) {
                val destination = if (uiState.pinCodeExists) {
                    Destination.PinCodeLogin
                } else {
                    Destination.PinCodeSetup
                }
                navigator.navigate(destination, NavOptionType.ClearStack)
            }
        }

        DisposableEffect(Unit) {
            onDispose {
                navigator.clearNavController()
                snackbarManager.clear()
            }
        }

        Scaffold(
            bottomBar = {
                HomeBottomNavigation(
                    navigator = navigator,
                    navController = navController
                )
            },
            snackbarHost = { MainSnackbarHost(hostState = snackbarHostState) },
            modifier = Modifier.background(MaterialTheme.colorScheme.background)
        ) { innerPadding ->
            Box(modifier = Modifier.padding(innerPadding)) {
                MainNavGraph(navController = navController)
            }
        }
    }
}

private fun Lifecycle.Event.toMainAction() = when (this) {
    Lifecycle.Event.ON_START -> MainAction.HandleOnStart
    Lifecycle.Event.ON_STOP -> MainAction.HandleOnStop
    Lifecycle.Event.ON_DESTROY -> MainAction.HandleOnDestroy
    else -> null
}
