package ls.gear.flow.data.network.interceptor

import okhttp3.Interceptor
import okhttp3.Response

private const val API_KEY_HEADER = "x-api-key"

class ApiKeyInterceptor(private val apiKey: String) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val apiKeyRequest = request.newBuilder()
            .addHeader(API_KEY_HEADER, apiKey)
            .build()
        return chain.proceed(apiKeyRequest)
    }
}
