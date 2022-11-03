import java.nio.file.Path
import kotlin.io.path.Path
import kotlin.io.path.exists
import kotlin.io.path.listDirectoryEntries

fun getFiles(pathStr: String): List<Path>? {
    val path = Path(pathStr)

    if (!path.exists()) {
        return null
    }

    return path.listDirectoryEntries()
}