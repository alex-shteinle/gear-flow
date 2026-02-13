package ls.gear.flow.domain.repository

import ls.gear.flow.domain.model.AppVersion

interface AppVersionRepository {
    fun get(): AppVersion
}
