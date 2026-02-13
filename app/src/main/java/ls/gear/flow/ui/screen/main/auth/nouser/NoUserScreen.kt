package ls.gear.flow.ui.screen.main.auth.nouser

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.koin.androidx.compose.koinViewModel
import ls.gear.flow.R
import ls.gear.flow.ui.screen.base.component.SpacerVertical
import ls.gear.flow.ui.screen.main.auth.nouser.state.NoUserAction
import ls.gear.flow.ui.screen.main.auth.nouser.state.ReloadState

private const val IMAGE_WIDTH_FRACTION = 0.8f

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun NoUserScreen(
    viewModel: NoUserViewModel = koinViewModel(),
    onUserLoaded: (Boolean) -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val pullRefreshState = rememberPullRefreshState(
        refreshing = uiState.loading,
        onRefresh = { viewModel.dispatch(NoUserAction.ReloadUser) }
    )

    LaunchedEffect(uiState.reloadState) {
        when (uiState.reloadState) {
            ReloadState.NO_USER -> Unit
            ReloadState.NO_PINCODE -> onUserLoaded(false)
            ReloadState.PINCODE_IS_PRESENT -> onUserLoaded(true)
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .pullRefresh(pullRefreshState)
    ) {
        LazyColumn(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxHeight()
                .padding(16.dp)
        ) {
            item {
                SpacerVertical(height = 80.dp)
                Image(
                    modifier = Modifier.fillMaxWidth(IMAGE_WIDTH_FRACTION),
                    painter = painterResource(id = R.drawable.ic_dont_know),
                    contentDescription = null
                )
                Spacer(modifier = Modifier.height(24.dp))
                Text(
                    text = stringResource(id = R.string.user_absent_title),
                    fontSize = 36.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    lineHeight = 40.sp
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = stringResource(id = R.string.user_absent_subtitle),
                    fontSize = 20.sp,
                    textAlign = TextAlign.Center
                )
            }
        }
        PullRefreshIndicator(
            refreshing = uiState.loading,
            state = pullRefreshState,
            modifier = Modifier.align(Alignment.TopCenter)
        )
    }
}
