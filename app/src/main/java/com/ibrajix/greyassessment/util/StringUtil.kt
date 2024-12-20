import android.os.Build
import androidx.annotation.RequiresApi
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.temporal.ChronoUnit

@RequiresApi(Build.VERSION_CODES.O)
fun String.toDaysAgo(): String {

    val instant = Instant.parse(this)

    val dateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault())

    val now = LocalDateTime.now()

    val daysAgo = ChronoUnit.DAYS.between(dateTime, now)

    return "updated $daysAgo day${if (daysAgo != 1L) "s" else ""} ago"
}
