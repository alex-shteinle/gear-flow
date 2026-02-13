package ls.gear.flow.util.biometric

import android.content.Context
import androidx.fragment.app.FragmentActivity

interface BioMetricAuthManager {
    fun canUseBioMetricAuth(context: Context): Boolean
    fun initBioMetricPrompt(activity: FragmentActivity, onFinish: (Result<Unit>) -> Unit)
}
