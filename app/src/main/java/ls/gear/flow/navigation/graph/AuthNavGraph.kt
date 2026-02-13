package ls.gear.flow.navigation.graph

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.navigation.NavGraphBuilder
import androidx.navigation.navigation
import com.google.accompanist.navigation.animation.composable
import ls.gear.flow.navigation.Destination
import ls.gear.flow.ui.screen.main.auth.nouser.NoUserScreen
import ls.gear.flow.ui.screen.main.auth.pincodelogin.PinCodeLoginScreen
import ls.gear.flow.ui.screen.main.auth.pincodesetup.PinCodeSetupScreen
import ls.gear.flow.ui.screen.main.splash.SplashScreen

fun NavGraphBuilder.authNavGraph(
    onAuthSuccess: () -> Unit,
    onUserLoaded: (Boolean) -> Unit
) {
    navigation(
        route = GraphConst.AUTH,
        startDestination = Destination.Splash.route
    ) {
        splashScreen()
        noUserScreen { onUserLoaded(it) }
        pinCodeSetupScreen { onAuthSuccess() }
        pinCodeLoginScreen { onAuthSuccess() }
    }
}

@OptIn(ExperimentalAnimationApi::class)
private fun NavGraphBuilder.splashScreen() {
    composable(
        route = Destination.Splash.route,
        enterTransition = { fadeIn() },
        exitTransition = { fadeOut() }
    ) {
        SplashScreen()
    }
}

@OptIn(ExperimentalAnimationApi::class)
private fun NavGraphBuilder.pinCodeSetupScreen(onPinSetupSuccess: () -> Unit) {
    composable(
        route = Destination.PinCodeSetup.route,
        enterTransition = { fadeIn() },
        exitTransition = { fadeOut() }
    ) {
        PinCodeSetupScreen { onPinSetupSuccess() }
    }
}

@OptIn(ExperimentalAnimationApi::class)
private fun NavGraphBuilder.pinCodeLoginScreen(onLoginSuccess: () -> Unit) {
    composable(
        route = Destination.PinCodeLogin.route,
        enterTransition = { fadeIn() },
        exitTransition = { fadeOut() }
    ) {
        PinCodeLoginScreen { onLoginSuccess() }
    }
}

@OptIn(ExperimentalAnimationApi::class)
private fun NavGraphBuilder.noUserScreen(onUserLoaded: (Boolean) -> Unit) {
    composable(
        route = Destination.NoUser.route,
        enterTransition = { fadeIn() },
        exitTransition = { fadeOut() }
    ) {
        NoUserScreen { onUserLoaded(it) }
    }
}
