package ls.gear.flow.data.repoimpl

import android.content.Context
import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import android.os.Build
import ls.gear.flow.data.BuildConfig
import ls.gear.flow.domain.model.AppVersion
import ls.gear.flow.domain.repository.AppVersionRepository
import ls.gear.flow.log.GearFlowLogger

class AppVersionRepositoryImpl(
    private val context: Context,
    private val logger: GearFlowLogger
) : AppVersionRepository {

    override fun get() = AppVersion(
        name = getAppVersion(),
        debug = isDebug(),
        gitBranchName = BuildConfig.GIT_BRANCH,
        lastGitCommitMessage = BuildConfig.GIT_LAST_COMMIT_MESSAGE,
        lastGitCommitHash = BuildConfig.GIT_LAST_COMMIT_HASH
    )

    private fun getAppVersion(): String {
        return try {
            val packageManager = context.packageManager
            val packageInfo = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                packageManager.getPackageInfo(
                    context.packageName,
                    PackageManager.PackageInfoFlags.of(0)
                )
            } else {
                packageManager.getPackageInfo(context.packageName, 0)
            }
            packageInfo.versionName
        } catch (exception: Exception) {
            logger.logError(klass = this, error = exception)
            ""
        }
    }

    private fun isDebug(): Boolean {
        return 0 != context.applicationInfo.flags and ApplicationInfo.FLAG_DEBUGGABLE
    }
}
