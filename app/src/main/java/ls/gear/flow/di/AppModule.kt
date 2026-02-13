package ls.gear.flow.di

import android.content.ClipboardManager
import android.content.Context
import android.content.Context.CLIPBOARD_SERVICE
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ls.gear.flow.navigation.navigator.GearFlowNavigator
import ls.gear.flow.navigation.navigator.GearFlowNavigatorImpl
import ls.gear.flow.ui.screen.main.MainViewModel
import ls.gear.flow.ui.screen.main.auth.nouser.NoUserViewModel
import ls.gear.flow.ui.screen.main.auth.pincodelogin.PinCodeLoginViewModel
import ls.gear.flow.ui.screen.main.auth.pincodesetup.PinSetupViewModel
import ls.gear.flow.ui.screen.main.home.groupdetail.ItemGroupDetailViewModel
import ls.gear.flow.ui.screen.main.home.order.OrderViewModel
import ls.gear.flow.ui.screen.main.home.personalsizes.SizesViewModel
import ls.gear.flow.ui.screen.main.home.personalsizes.select.SizePickerViewModel
import ls.gear.flow.ui.screen.main.home.property.PropertyCardViewModel
import ls.gear.flow.ui.screen.main.home.setting.SettingsViewModel
import ls.gear.flow.ui.screen.main.splash.SplashViewModel
import ls.gear.flow.ui.snackbar.SnackbarManager
import ls.gear.flow.ui.snackbar.SnackbarManagerImpl
import ls.gear.flow.util.biometric.BioMetricAuthManager
import ls.gear.flow.util.biometric.BioMetricAuthManagerImpl

val appModule = module {
    single<GearFlowNavigator> { GearFlowNavigatorImpl(get()) }
    single<SnackbarManager> { SnackbarManagerImpl(get()) }
    single<BioMetricAuthManager> { BioMetricAuthManagerImpl(get()) }
    single { provideClipboardManager(get()) }
    viewModel { MainViewModel(get()) }
    viewModel { SplashViewModel(get(), get()) }
    viewModel { PinSetupViewModel(get(), get(), get(), get(), get()) }
    viewModel { PinCodeLoginViewModel(get(), get(), get()) }
    viewModel { SizesViewModel(get(), get()) }
    viewModel { PropertyCardViewModel(get(), get(), get()) }
    viewModel { SizePickerViewModel() }
    viewModel { OrderViewModel(get(), get()) }
    viewModel { ItemGroupDetailViewModel(get(), get()) }
    viewModel { NoUserViewModel(get(), get()) }
    viewModel { SettingsViewModel(get(), get(), get(), get(), get(), get()) }
}

private fun provideClipboardManager(context: Context): ClipboardManager {
    return context.getSystemService(CLIPBOARD_SERVICE) as ClipboardManager
}
