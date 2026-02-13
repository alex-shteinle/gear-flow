package ls.gear.flow.navigation.graph

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.AnimatedNavHost
import org.koin.compose.koinInject
import ls.gear.flow.navigation.Destination
import ls.gear.flow.navigation.NavOptionType
import ls.gear.flow.navigation.navigator.GearFlowNavigator

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun MainNavGraph(
    navController: NavHostController,
    navigator: GearFlowNavigator = koinInject()
) {
    val onAuthSuccess = { navigator.navigate(Destination.HomeGraph, NavOptionType.ClearStack) }
    val onUserLoaded: (Boolean) -> Unit = { pinCodeExists ->
        val destination = if (pinCodeExists) Destination.PinCodeLogin else Destination.PinCodeSetup
        navigator.navigate(destination, NavOptionType.ClearStack)
    }

    AnimatedNavHost(
        navController = navController,
        startDestination = GraphConst.AUTH
    ) {
        authNavGraph(onAuthSuccess = onAuthSuccess, onUserLoaded = onUserLoaded)
        homeNavGraph()
    }
}
