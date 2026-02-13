package ls.gear.flow.log

const val DEFAULT_TAG = "GearFlow"

interface GearFlowLogger {
    fun logDebug(klass: Any, message: String)
    fun logError(klass: Any, error: Throwable? = null, message: String? = null)
    fun logFunction(functionName: String, message: String)
}
