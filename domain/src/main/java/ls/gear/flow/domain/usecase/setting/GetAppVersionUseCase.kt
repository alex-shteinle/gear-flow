package ls.gear.flow.domain.usecase.setting

import ls.gear.flow.domain.model.AppVersion

fun interface GetAppVersionUseCase : () -> AppVersion
