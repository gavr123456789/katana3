
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import java.nio.file.Path


//@Composable
//fun ClickableTest() {
//    val count = remember { mutableStateOf(0) }
//    // content that you want to make clickable
//    Box(modifier = Modifier.background(Color.Red).requiredSize(50.dp).pointerInput(Unit) {
//        detectTapGestures(
//            onPress = { println("press") },
//            onDoubleTap = { println("double tap") },
//            onLongPress = { println("long press") },
//            onTap = { println("tap") }
//        )
//    }, contentAlignment = Alignment.Center) {
//        Text(
//            text = count.value.toString(),
//            modifier = Modifier.clickable { count.value += 1 }
//        )
//    }
//}


//////
@Composable
fun TopBar(onMenuClicked: () -> Unit, onBackClick: () -> Unit) {
    TopAppBar(
        title = {
            Text(text = "home/", color = Color.White)
        },
        navigationIcon = {

            Icon(
                imageVector = Icons.Default.Menu,
                contentDescription = "Menu",
                modifier = Modifier.clickable(onClick = onMenuClicked),
                tint = Color.White
            )
            IconButton(onClick = onBackClick) {
                Icon(Icons.Rounded.ArrowBack, contentDescription = "Localized description")
            }
        },
    )
}

@Composable
fun Drawer() {
    // Column Composable
    Column(
        Modifier
            .background(Color.White)
            .fillMaxSize()
    ) {
        // Repeat is a loop which
        // takes count as argument
        repeat(5) { item ->
            Text(text = "Item number $item", modifier = Modifier.padding(8.dp), color = Color.Black)
        }
    }
}


@Composable
fun Body() {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        val maybeFiles = getFiles(".") ?: listOf()
        var files: List<Path> by rememberSaveable() { mutableStateOf(maybeFiles) }


        Page(files = files, onFilesChange = { files = it })
    }
}


@Composable
fun MainLayout() {

    val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Closed))

    val coroutineScope = rememberCoroutineScope()
    val (expandedBar, setExpandedBar) = remember { mutableStateOf(false) }


    // Scaffold Composable
    Scaffold(

        // pass the scaffold state
        scaffoldState = scaffoldState,

        // pass the topbar we created
        topBar = {
            TopBar(
                // When menu is clicked open the
                // drawer in coroutine scope
                onMenuClicked = {
                    coroutineScope.launch {
                        // to close use -> scaffoldState.drawerState.close()
                        scaffoldState.drawerState.open()
                    }
                },
                onBackClick = {
                    setExpandedBar(!expandedBar)
                }
            )
        },

        bottomBar = {
            val density = LocalDensity.current
            AnimatedVisibility(
                expandedBar,
                enter = slideInVertically {
                    with(density) { 60.dp.roundToPx() }
                },
                exit = slideOutVertically{
                    with(density) { 60.dp.roundToPx() }
                }
            ) {
                BottomAppBar(
                ) {
                    Text("sas")
                }
            }

        },

        content = {
            Body()
        },

        drawerContent = {
            Drawer()
        },

//        floatingActionButton = {
//            FloatingActionButton(
//
//                onClick = {
//                    coroutineScope.launch {
//                        when (scaffoldState.snackbarHostState.showSnackbar(
//                            message = "Snack Bar",
//                            actionLabel = "Dismiss"
//                        )) {
//                            SnackbarResult.Dismissed -> {
//                                // do something when
//                                // snack bar is dismissed
//                            }
//
//                            SnackbarResult.ActionPerformed -> {
//                                // when it appears
//                            }
//                        }
//                    }
//                }) {
//                // Simple Text inside FAB
//                Text(text = "X")
//            }
//        }
    )
}