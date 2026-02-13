package ls.gear.flow.util.ext

import java.time.LocalDate
import java.time.format.DateTimeFormatter

fun LocalDate.toStringByPattern(pattern: String): String {
    return format(DateTimeFormatter.ofPattern(pattern))
}
