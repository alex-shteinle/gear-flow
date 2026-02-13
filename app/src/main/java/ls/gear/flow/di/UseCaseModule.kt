package ls.gear.flow.di

import android.content.ClipData
import android.content.ClipboardManager
import org.koin.dsl.module
import ls.gear.flow.data.util.connection.ConnectionUtil
import ls.gear.flow.domain.repository.AppVersionRepository
import ls.gear.flow.domain.repository.PinCodeRepository
import ls.gear.flow.domain.repository.SettingsRepository
import ls.gear.flow.domain.usecase.CheckConnectionUseCase
import ls.gear.flow.domain.usecase.setting.GetAppVersionUseCase
import ls.gear.flow.domain.usecase.setting.GetSettingsUseCase
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
import ls.gear.flow.domain.usecase.user.GetLocalUserUseCase
import ls.gear.flow.domain.usecase.user.GetRemoteUserUseCase
import ls.gear.flow.domain.usecase.user.GetSizesUseCase
import ls.gear.flow.domain.usecase.user.GetUserUseCase
import ls.gear.flow.domain.usecase.user.SaveSizesUseCase
import ls.gear.flow.util.biometric.BioMetricAuthManager

val useCaseModule = module {
    factory { GetRemoteUserUseCase(get(), get(), get(), get(), get(), get()) }
    factory { GetLocalUserUseCase(get()) }
    factory { GetUserUseCase(get(), get()) }
    factory { SaveSizesUseCase(get(), get()) }
    factory { GetSizesUseCase(get()) }
    factory { GetPinCodeUseCase(get<PinCodeRepository>()::get) }
    factory { CheckPinCodeExistsUseCase(get()) }
    factory { SavePinCodeUseCase(get<PinCodeRepository>()::save) }
    factory { SaveUseBiometricUseCase(get<SettingsRepository>()::save) }
    factory {
        IsBioMetricAvailableUseCase {
            get<BioMetricAuthManager>().canUseBioMetricAuth(get())
        }
    }
    factory {
        ShouldUseBioMetricUseCase {
            get<SettingsRepository>().get().getOrNull()?.useBioMetric ?: false
        }
    }
    factory { OrderItemsUseCase(get(), get()) }
    factory { GetItemGroupsUseCase(get()) }
    factory { GetItemGroupByTypeUseCase(get()) }
    factory { GetDemandItemsUseCase(get(), get(), get()) }
    factory { ValidatePinCodeUseCase() }
    factory { MatchPinCodesUseCase(get()) }
    factory { CheckConnectionUseCase(get<ConnectionUtil>()::hasConnection) }
    factory { GetAppVersionUseCase(get<AppVersionRepository>()::get) }
    factory { GetSettingsUseCase(get()) }
    factory {
        CopyToClipBoardUseCase {
            get<ClipboardManager>().setPrimaryClip(ClipData.newPlainText("text", it))
        }
    }
}
