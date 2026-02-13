package ls.gear.flow

import androidx.lifecycle.SavedStateHandle
import org.junit.Test
import org.koin.test.verify.verify
import ls.gear.flow.data.di.dataModule
import ls.gear.flow.di.appModule
import ls.gear.flow.di.useCaseModule
import ls.gear.flow.domain.repository.UserCacheRepository
import ls.gear.flow.domain.repository.PersonalSizesRepository
import ls.gear.flow.domain.repository.PinCodeRepository
import ls.gear.flow.domain.repository.SettingsRepository
import ls.gear.flow.domain.repository.DemandItemRepository
import ls.gear.flow.domain.repository.LoginRepository
import ls.gear.flow.domain.repository.UserRepository
import ls.gear.flow.log.GearFlowLogger

class KoinModulesTest {

    @Test
    fun checkKoinModules() {
        val extraTypes = listOf(
            UserRepository::class,
            DemandItemRepository::class,
            UserCacheRepository::class,
            PersonalSizesRepository::class,
            PinCodeRepository::class,
            SettingsRepository::class,
            GearFlowLogger::class,
            Boolean::class,
            SavedStateHandle::class,
            LoginRepository::class
        )
        dataModule.verify(extraTypes = extraTypes)
        useCaseModule.verify(extraTypes = extraTypes)
        appModule.verify(extraTypes = extraTypes)
    }
}
