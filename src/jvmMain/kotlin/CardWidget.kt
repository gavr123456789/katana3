import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import java.nio.file.Path
import kotlin.io.path.isDirectory
import kotlin.io.path.pathString

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CardWidget(text: String, onFilesChange: (List<Path>) -> Unit, fileItem: Path) {
    Card(
        onClick = {
            println("clicked card")

            if (fileItem.isDirectory()){
                println("clecked directory")

                val sas = getFiles(fileItem.pathString)
                if (sas == null){
                    return@Card
                } else {
                    println(sas)
                    onFilesChange(sas)
                }
            } else {
                println("clicked file")
            }
        },
        shape = RoundedCornerShape(4.dp),
        modifier = Modifier.padding(4.dp).fillMaxHeight()
    ) {
        Column(
            modifier = Modifier.padding(8.dp)
        ) {
            Text(text)
        }
    }
}