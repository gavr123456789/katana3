
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import java.nio.file.Path
import kotlin.io.path.isDirectory
import kotlin.io.path.pathString


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun FileRow(text: String, onFilesChange: (List<Path>) -> Unit, fileItem: Path) {
    var checkedCard by remember { mutableStateOf(false) }
    // In fact, I need 2 cards, one with rounded left corners, and the second one with rounded right corners
    Row() {

        Column(modifier = Modifier.fillMaxWidth().weight(1f)) {
            Card(
                onClick = {
                    println("clicked card")

                    if (fileItem.isDirectory()) {
                        println("clecked directory")

                        val pathList = getFiles(fileItem.pathString)
                        if (pathList == null) {
                            return@Card
                        } else {
                            println(pathList)
                            onFilesChange(pathList)
                        }
                    } else {
                        println("clicked file")
                    }
                    checkedCard = !checkedCard
                },
                shape = RoundedCornerShape(100, 0, 0, 100),
//                modifier = Modifier.padding(start = 8.dp),
                elevation = if (checkedCard) 0.dp else 4.dp,

                ) {
                Row(
                    modifier = Modifier.padding(8.dp).fillMaxHeight()
                ) {
                    Text(text)
                    Spacer(Modifier.weight(1f))
                }
            }
        }
        Column(modifier = Modifier.fillMaxWidth().weight(0.3f)) {
            Card(
                onClick = {
                    println("clicked card")

                    if (fileItem.isDirectory()) {
                        println("clecked directory")

                        val pathList = getFiles(fileItem.pathString)
                        if (pathList == null) {
                            return@Card
                        } else {
                            println(pathList)
                            onFilesChange(pathList)
                        }
                    } else {
                        println("clicked file")
                    }
                    checkedCard = !checkedCard
                },
                shape = RoundedCornerShape(0, 100, 100, 0),
//                modifier = Modifier.padding(start = 8.dp),
                elevation = if (checkedCard) 0.dp else 4.dp,
            ) {
                Row(
                    modifier = Modifier.padding(8.dp).fillMaxHeight()
                ) {
                    Icon(Icons.Rounded.ArrowBack, contentDescription = "s" )
                }
            }
        }
    }

}