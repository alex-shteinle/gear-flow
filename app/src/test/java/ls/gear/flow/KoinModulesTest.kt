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
import ls.gear.flow.domain.repository.NormRepository
import ls.gear.flow.domain.repository.UserRepository
import ls.gear.flow.domain.usecase.biometric.IsBioMetricAvailableUseCase
import ls.gear.flow.domain.usecase.biometric.SaveUseBiometricUseCase
import ls.gear.flow.domain.usecase.biometric.ShouldUseBioMetricUseCase
import ls.gear.flow.domain.usecase.item.GetItemGroupByTypeUseCase
import ls.gear.flow.domain.usecase.item.GetItemGroupsUseCase
import ls.gear.flow.domain.usecase.order.GetDemandItemsUseCase
import ls.gear.flow.domain.usecase.order.OrderItemsUseCase
import ls.gear.flow.domain.usecase.pincode.CheckPinCodeExistsUseCase
import ls.gear.flow.domain.usecase.pincode.GetPinCodeUseCase
import ls.gear.flow.domain.usecase.pincode.MatchPinCodesUseCase
import ls.gear.flow.domain.usecase.pincode.SavePinCodeUseCase
import ls.gear.flow.domain.usecase.pincode.ValidatePinCodeUseCase
import ls.gear.flow.domain.usecase.setting.CopyToClipBoardUseCase
import ls.gear.flow.domain.usecase.setting.GetAppVersionUseCase
import ls.gear.flow.domain.usecase.setting.GetSettingsUseCase
import ls.gear.flow.domain.usecase.user.GetLocalUserUseCase
import ls.gear.flow.domain.usecase.user.GetRemoteUserUseCase
import ls.gear.flow.domain.usecase.user.GetSizesUseCase
import ls.gear.flow.domain.usecase.user.GetUserUseCase
import ls.gear.flow.domain.usecase.user.SaveSizesUseCase
import ls.gear.flow.log.GearFlowLogger
import org.koin.core.annotation.KoinExperimentalAPI

class KoinModulesTest {

    @OptIn(KoinExperimentalAPI::class)
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
            LoginRepository::class,
            NormRepository::class,
            CheckPinCodeExistsUseCase::class,
            GetUserUseCase::class,
            SavePinCodeUseCase::class,
            SaveUseBiometricUseCase::class,
            ValidatePinCodeUseCase::class,
            MatchPinCodesUseCase::class,
            IsBioMetricAvailableUseCase::class,
            ShouldUseBioMetricUseCase::class,
            GetPinCodeUseCase::class,
            GetSizesUseCase::class,
            SaveSizesUseCase::class,
            GetItemGroupsUseCase::class,
            GetItemGroupByTypeUseCase::class,
            GetRemoteUserUseCase::class,
            GetDemandItemsUseCase::class,
            OrderItemsUseCase::class,
            GetLocalUserUseCase::class,
            GetSettingsUseCase::class,
            GetAppVersionUseCase::class,
            CopyToClipBoardUseCase::class
        )
        dataModule.verify(extraTypes = extraTypes)
        useCaseModule.verify(extraTypes = extraTypes)
        appModule.verify(extraTypes = extraTypes)
    }
}
