package ls.gear.flow.util.biometric

import android.content.Context
import androidx.biometric.BiometricManager
import androidx.biometric.BiometricPrompt
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import ls.gear.flow.R
import ls.gear.flow.domain.error.GearFlowError
import ls.gear.flow.log.GearFlowLogger

class BioMetricAuthManagerImpl(private val logger: GearFlowLogger) : BioMetricAuthManager {

    override fun canUseBioMetricAuth(context: Context): Boolean {
        return BiometricManager
            .from(context)
            .canAuthenticate(BiometricManager.Authenticators.BIOMETRIC_STRONG) == BiometricManager.BIOMETRIC_SUCCESS
    }

    override fun initBioMetricPrompt(activity: FragmentActivity, onFinish: (Result<Unit>) -> Unit) {
        val appName = activity.getString(R.string.app_name)
        val promptInfo = BiometricPrompt.PromptInfo.Builder()
            .setTitle(activity.getString(R.string.biometric_title, appName))
            .setNegativeButtonText(activity.getString(R.string.cancel))
            .build()

        val biometricPrompt = BiometricPrompt(
            activity,
            ContextCompat.getMainExecutor(activity),
            object : BiometricPrompt.AuthenticationCallback() {
                override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
                    super.onAuthenticationError(errorCode, errString)
                    // Handle authentication error
                    logger.logError(
                        klass = this@BioMetricAuthManagerImpl,
                        message = "Authentication error: $errString"
                    )
                    onFinish(Result.failure(GearFlowError.BioMetric))
                }

                override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                    super.onAuthenticationSucceeded(result)
                    onFinish(Result.success(Unit))
                }

                override fun onAuthenticationFailed() {
                    super.onAuthenticationFailed()
                    // Handle authentication failure
                    logger.logError(
                        klass = this@BioMetricAuthManagerImpl,
                        message = "Authentication failure"
                    )
                    onFinish(Result.failure(GearFlowError.BioMetric))
                }
            }
        )
        biometricPrompt.authenticate(promptInfo)
    }
}
