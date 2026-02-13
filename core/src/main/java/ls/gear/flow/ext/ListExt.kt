package ls.gear.flow.ext

fun <T> List<T>.replaceIf(newValue: T, block: (T) -> Boolean): List<T> {
    return map {
        if (block(it)) newValue else it
    }
}
