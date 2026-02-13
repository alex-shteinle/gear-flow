package ls.gear.flow.domain.model

data class AppVersion(
    val name: String = "",
    val debug: Boolean = false,
    val gitBranchName: String = "",
    val lastGitCommitMessage: String = "",
    val lastGitCommitHash: String = ""
)
