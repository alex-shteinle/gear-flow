package ls.gear.flow.data.util.log

import android.util.Log
import ls.gear.flow.log.DEFAULT_TAG
import ls.gear.flow.log.GearFlowLogger

class GearFlowLoggerImpl : GearFlowLogger {

    override fun logDebug(klass: Any, message: String) {
        Log.d(klass.toTag(), message)
    }

    override fun logError(klass: Any, error: Throwable?, message: String?) {
        val finalMessage = buildString {
            if (!message.isNullOrBlank()) append("message = $message")
            error?.let { append("error = $error") }
            if (this.isBlank()) append("Where is your error message, dude?")
        }
        Log.e(klass.toTag(), finalMessage)
    }

    override fun logFunction(functionName: String, message: String) {
        Log.d("$DEFAULT_TAG: $functionName", message)
    }

    private fun Any.toTag() = "$DEFAULT_TAG: ${this::class.java}"
}
