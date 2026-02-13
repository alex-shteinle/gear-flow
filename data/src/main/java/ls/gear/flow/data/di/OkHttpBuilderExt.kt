package ls.gear.flow.data.di

import android.annotation.SuppressLint
import android.os.Build
import android.os.Build.VERSION.SDK_INT
import android.util.Log
import okhttp3.OkHttpClient
import ls.gear.flow.log.DEFAULT_TAG
import java.security.SecureRandom
import java.security.cert.X509Certificate
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager

@SuppressLint("CustomX509TrustManager")
internal fun OkHttpClient.Builder.ignoreAllSSLErrors(): OkHttpClient.Builder {
    Log.d(DEFAULT_TAG, "ignoreAllSSLErrors: ")
    val tlsVersion = if (SDK_INT >= Build.VERSION_CODES.Q) "TLSv1.3" else "TLSv1.2"
    val naiveTrustManager = object : X509TrustManager {
        override fun getAcceptedIssuers(): Array<X509Certificate> = arrayOf()
        override fun checkClientTrusted(certs: Array<X509Certificate>, authType: String) = Unit
        override fun checkServerTrusted(certs: Array<X509Certificate>, authType: String) = Unit
    }

    val insecureSocketFactory = SSLContext.getInstance(tlsVersion).apply {
        val trustAllCerts = arrayOf<TrustManager>(naiveTrustManager)
        init(null, trustAllCerts, SecureRandom())
    }.socketFactory

    sslSocketFactory(insecureSocketFactory, naiveTrustManager)
    hostnameVerifier { _, _ -> true }
    return this
}
