package ls.gear.flow.data.network.interceptor

import okhttp3.Interceptor
import okhttp3.Response
import ls.gear.flow.data.network.CookieRepository
import java.io.IOException

private const val HEADER_SET_COOKIE = "Set-Cookie"

class ReceivedCookiesInterceptor(private val cookieRepository: CookieRepository) : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalResponse: Response = chain.proceed(chain.request())
        val cookieHeaders = originalResponse.headers(HEADER_SET_COOKIE)
        if (cookieHeaders.isNotEmpty()) {
            cookieRepository.save(cookieHeaders.toSet())
        }
        return originalResponse
    }
}
