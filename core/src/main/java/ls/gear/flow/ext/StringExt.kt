package ls.gear.flow.ext

fun String.isFilledForIndex(index: Int) = length > index

fun String.hasOnlyNumbers() = all { char -> char.isDigit() }

fun String.toIntOrZero() = toIntOrNull() ?: 0

inline fun <reified T : Enum<T>> enumValueOfOrNull(name: String?): T? {
    return enumValues<T>().find { it.name.equals(name, ignoreCase = true) }
}
