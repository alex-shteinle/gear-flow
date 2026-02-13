package ls.gear.flow.ui.screen.main.auth.nouser.state

data class NoUserState(
    val loading: Boolean = false,
    val reloadState: ReloadState = ReloadState.NO_USER
)
