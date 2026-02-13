package ls.gear.flow.util.ext

fun Int.toEmptyIfNotPositive() = if (this <= 0) "" else toString()
