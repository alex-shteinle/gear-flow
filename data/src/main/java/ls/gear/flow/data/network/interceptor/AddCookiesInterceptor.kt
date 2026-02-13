package ls.gear.flow.data.network.interceptor

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import ls.gear.flow.data.network.CookieRepository
import java.io.IOException

private const val HEADER_COOKIE = "Cookie"

class AddCookiesInterceptor(private val cookieRepository: CookieRepository) : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val builder: Request.Builder = chain.request().newBuilder()
        val prefCookies = cookieRepository.get()
        prefCookies.forEach {
            builder.addHeader(HEADER_COOKIE, it)
        }
        return chain.proceed(builder.build())
    }
}
