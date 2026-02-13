package ls.gear.flow.data.network

import android.content.SharedPreferences
import androidx.core.content.edit

private const val KEY_COOKIE = "cookie"

class CookieRepositoryImpl(private val sharedPreferences: SharedPreferences) : CookieRepository {

    override fun save(cookies: Set<String>) {
        sharedPreferences.edit {
            putStringSet(KEY_COOKIE, cookies)
        }
    }

    override fun get(): Set<String> {
        return sharedPreferences.getStringSet(KEY_COOKIE, emptySet()) ?: emptySet()
    }
}
