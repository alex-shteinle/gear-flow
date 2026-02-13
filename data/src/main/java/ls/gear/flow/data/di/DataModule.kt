package ls.gear.flow.data.di

import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import ls.gear.flow.data.BuildConfig
import ls.gear.flow.data.db.GearFlowDatabase
import ls.gear.flow.data.network.CookieRepository
import ls.gear.flow.data.network.CookieRepositoryImpl
import ls.gear.flow.data.network.api.AnalyticsApi
import ls.gear.flow.data.network.api.LoginApi
import ls.gear.flow.data.repoimpl.UserCacheRepositoryImpl
import ls.gear.flow.data.network.api.UserApi
import ls.gear.flow.data.network.interceptor.AddCookiesInterceptor
import ls.gear.flow.data.network.interceptor.ApiKeyInterceptor
import ls.gear.flow.data.network.interceptor.ReceivedCookiesInterceptor
import ls.gear.flow.data.repoimpl.AppVersionRepositoryImpl
import ls.gear.flow.data.repoimpl.DemandItemRepositoryImpl
import ls.gear.flow.data.repoimpl.LoginRepositoryImpl
import ls.gear.flow.data.repoimpl.NormRepositoryImpl
import ls.gear.flow.data.repoimpl.PersonalSizesRepositoryImpl
import ls.gear.flow.data.repoimpl.PinCodeRepositoryImpl
import ls.gear.flow.data.repoimpl.SettingsRepositoryImpl
import ls.gear.flow.data.repoimpl.UserRepositoryImpl
import ls.gear.flow.data.util.connection.ConnectionUtil
import ls.gear.flow.data.util.connection.ConnectionUtilImpl
import ls.gear.flow.data.util.log.GearFlowLoggerImpl
import ls.gear.flow.domain.repository.AppVersionRepository
import ls.gear.flow.domain.repository.UserCacheRepository
import ls.gear.flow.domain.repository.PersonalSizesRepository
import ls.gear.flow.domain.repository.PinCodeRepository
import ls.gear.flow.domain.repository.SettingsRepository
import ls.gear.flow.domain.repository.DemandItemRepository
import ls.gear.flow.domain.repository.LoginRepository
import ls.gear.flow.domain.repository.NormRepository
import ls.gear.flow.domain.repository.UserRepository
import ls.gear.flow.log.GearFlowLogger

private const val CRYPTO_PREF_NAME = "shared_prefs_1"
private const val SETTINGS_PREF_NAME = "shared_prefs_2"
private const val NETWORK_PREF_NAME = "shared_prefs_3"
private const val CRYPTO_TYPE = "crypto"
private const val SETTINGS_TYPE = "settings"
private const val NETWORK_TYPE = "network"
private const val BASE_URL_DEV = "https://provisioning-myno-dev.oak.in.ua"
private const val BASE_URL_TEST = "https://provisioning-myno-test.oak.in.ua"
private const val API_KEY_VALUE = "ebf51dee-98ca-4b82-a973-948f3bfb13b6"
private const val DB_NAME = "gear_flow_db"
private const val DISPATCHER_IO = "io"
private const val DISPATCHER_MAIN = "main"

val dataModule = module {
    single<LoginRepository> { LoginRepositoryImpl(get(named(DISPATCHER_IO)), get(), get()) }
    single<UserRepository> {
        UserRepositoryImpl(get(named(DISPATCHER_IO)), get(), get())
    }
    single(named(CRYPTO_TYPE)) { createEncryptedSharedPreferences(get()) }
    single(named(SETTINGS_TYPE)) { createSettingsSharedPreferences(get()) }
    single(named(NETWORK_TYPE)) { createNetworkSharedPreferences(get()) }
    single<PinCodeRepository> {
        PinCodeRepositoryImpl(get(named(DISPATCHER_IO)), get(named(CRYPTO_TYPE)))
    }
    single<PersonalSizesRepository> {
        PersonalSizesRepositoryImpl(get(named(DISPATCHER_IO)), get(), get(), get())
    }
    single<DemandItemRepository> { DemandItemRepositoryImpl(get(named(DISPATCHER_IO)), get()) }
    single<NormRepository> { NormRepositoryImpl(get(named(DISPATCHER_IO)), get(), get()) }
    single<SettingsRepository> {
        SettingsRepositoryImpl(get(named(SETTINGS_TYPE)))
    }
    single { provideRetrofit(get()) }
    single { provideUserApi(get()) }
    single { provideLoginApi(get()) }
    single { provideAnalyticsApi(get()) }
    single { createDb(get()) }
    single { get<GearFlowDatabase>().personalSizesDao() }
    single<UserCacheRepository> { UserCacheRepositoryImpl() }
    single<GearFlowLogger> { GearFlowLoggerImpl() }
    single<CookieRepository> { CookieRepositoryImpl(get(named(NETWORK_TYPE))) }
    factory<CoroutineDispatcher>(named(DISPATCHER_IO)) { Dispatchers.IO }
    factory<CoroutineDispatcher>(named(DISPATCHER_MAIN)) { Dispatchers.Main }
    single<ConnectionUtil> { ConnectionUtilImpl(get()) }
    single<AppVersionRepository> { AppVersionRepositoryImpl(get(), get()) }
}

// region shared prefs
private fun createEncryptedSharedPreferences(context: Context): SharedPreferences {
    val masterKeyAlias = MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC)
    return EncryptedSharedPreferences.create(
        CRYPTO_PREF_NAME,
        masterKeyAlias,
        context,
        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
    )
}

private fun createSettingsSharedPreferences(context: Context): SharedPreferences {
    return context.getSharedPreferences(SETTINGS_PREF_NAME, Context.MODE_PRIVATE)
}

private fun createNetworkSharedPreferences(context: Context): SharedPreferences {
    return context.getSharedPreferences(NETWORK_PREF_NAME, Context.MODE_PRIVATE)
}
// end region shared prefs

// region networking
private fun provideRetrofit(cookieRepository: CookieRepository): Retrofit {
    val loggingInterceptor = HttpLoggingInterceptor()
        .apply { level = HttpLoggingInterceptor.Level.BODY }

    val client = OkHttpClient.Builder().apply {
        addInterceptor(ApiKeyInterceptor(API_KEY_VALUE))
        addInterceptor(ReceivedCookiesInterceptor(cookieRepository))
        addInterceptor(AddCookiesInterceptor(cookieRepository))
        addNetworkInterceptor(loggingInterceptor)
        if (BuildConfig.DEBUG) ignoreAllSSLErrors()
    }
        .build()

    return Retrofit.Builder()
        .client(client)
        .baseUrl(BASE_URL_TEST)
        .addConverterFactory(MoshiConverterFactory.create())
        .build()
}

private fun provideUserApi(retrofit: Retrofit): UserApi {
    return retrofit.create(UserApi::class.java)
}

private fun provideLoginApi(retrofit: Retrofit): LoginApi {
    return retrofit.create(LoginApi::class.java)
}

private fun provideAnalyticsApi(retrofit: Retrofit): AnalyticsApi {
    return retrofit.create(AnalyticsApi::class.java)
}
// end region networking

// region db
private fun createDb(context: Context) = Room
    .databaseBuilder(context, GearFlowDatabase::class.java, DB_NAME)
    .build()
// end region db
