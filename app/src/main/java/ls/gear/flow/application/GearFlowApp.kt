package ls.gear.flow.application

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import ls.gear.flow.data.di.dataModule
import ls.gear.flow.di.appModule
import ls.gear.flow.di.useCaseModule

class GearFlowApp : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@GearFlowApp)
            androidLogger()
            modules(
                appModule,
                useCaseModule,
                dataModule
            )
        }
    }
}
