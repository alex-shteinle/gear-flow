package ls.gear.flow.data.util.connection

import android.content.Context
import android.net.ConnectivityManager

class ConnectionUtilImpl(private val context: Context) : ConnectionUtil {

    private val connectivityManager
        get() = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    override fun hasConnection(): Boolean {
        val activeNetwork = connectivityManager.activeNetwork
        return activeNetwork != null &&
            connectivityManager.getNetworkCapabilities(activeNetwork) != null
    }
}
