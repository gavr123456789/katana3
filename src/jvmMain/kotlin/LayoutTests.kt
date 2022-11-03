
import androidx.compose.animation.*
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

// modifier = Modifier.fillMaxWidth()
// clickable padding size

// horizontalAlignment = Alignment.CenterHorizontally - располагать по центру
//verticalArrangement = Arrangement.Bottom - располагать снизу

@Composable
fun ScrollBoxes() {
    Column(
        modifier = Modifier
            .background(Color.LightGray)
            .size(100.dp)
            .verticalScroll(rememberScrollState())

    ) {
        repeat(10) {
            Text("Item $it", modifier = Modifier.padding(2.dp))
        }
    }
}

@Composable
fun ClickableTest() {
    val count = remember { mutableStateOf(0) }
    // content that you want to make clickable
    Box(modifier = Modifier.background(Color.Red).requiredSize(50.dp).pointerInput(Unit) {
        detectTapGestures(
            onPress = { println("press") },
            onDoubleTap = { println("double tap") },
            onLongPress = { println("long press") },
            onTap = { println("tap") }
        )
    }, contentAlignment = Alignment.Center) {
        Text(
            text = count.value.toString(),
            modifier = Modifier.clickable { count.value += 1 }
        )
    }
}


@Composable
fun LayoutTest() {
    Column() {
        Button(onClick = { println("sas") }, modifier = Modifier.fillMaxWidth()) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Bottom
            ) {
                Text("press2222222222222222")
                Text("223123")
            }
        }
        Column() {
            Text("Alfred Sisley")
            Text("3 minutes ago")
            ClickableTest()
            ScrollBoxes()
        }
    }
}

@Composable
fun LayoutTest2() {

    Row(Modifier.background(color = Color.Red).fillMaxSize()) {
        Column(Modifier.background(color = Color.Magenta)) {
            Button(onClick = {}, modifier = Modifier) {
                Text("1")
            }
            Button(onClick = {}) {
                Text("2")
            }
        }
        Column(Modifier.background(color = Color.Blue)) {
            Button(onClick = {}) {
                Text("3")
            }
            Button(onClick = {}) {
                Text("4")
            }
        }
    }


}

//////
@Composable
fun TopBar(onMenuClicked: () -> Unit) {

    // TopAppBar Composable
    TopAppBar(
        // Provide Title
        title = {
            Text(text = "Scaffold||GFG", color = Color.White)
        },
        // Provide the navigation Icon (Icon on the left to toggle drawer)
        navigationIcon = {
            Icon(
                imageVector = Icons.Default.Menu,
                contentDescription = "Menu",

                // When clicked trigger onClick
                // Callback to trigger drawer open
                modifier = Modifier.clickable(onClick = onMenuClicked),
                tint = Color.White
            )
        },
        // background color of topAppBar
//        backgroundColor = Color(0xFF0F9D58)
    )
}

@Composable
fun BottomBar() {
    // BottomAppBar Composable
    BottomAppBar(
//        backgroundColor = Color(0xFF0F9D58)
    ) {
        Text(text = "Bottom App Bar", color = Color.White)
    }
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
        Row() {
            Text(text = "safasfasdfsasdssdsaasdsadsdsaasdsasdsasdasddassdsssdasdadsasdasdsd",
                Modifier
                    .padding(top = 12.dp, bottom = 12.dp, end = 12.dp, start = 10.dp)
                    .background(Color.Gray)
                    .weight(1f)
            )
            Text(
                text = "12:00 am",
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .weight(1f),
                maxLines = 1
            )
        }
    }
}

@OptIn(ExperimentalMaterialApi::class, ExperimentalAnimationApi::class)
@Composable
fun TransitionedCard() {
    var expanded by remember { mutableStateOf(false) }
    Surface(
        color = MaterialTheme.colors.primary,
        onClick = { expanded = !expanded }
    ) {
        AnimatedContent(
            targetState = expanded,
            transitionSpec = {
                fadeIn(animationSpec = tween(150, 150)) with
                        fadeOut(animationSpec = tween(150)) using
                        SizeTransform { initialSize, targetSize ->
                            if (targetState) {
                                keyframes {
                                    // Expand horizontally first.
                                    IntSize(targetSize.width, initialSize.height) at 150
                                    durationMillis = 300
                                }
                            } else {
                                keyframes {
                                    // Shrink vertically first.
                                    IntSize(initialSize.width, targetSize.height) at 150
                                    durationMillis = 300
                                }
                            }
                        }
            }
        ) { targetExpanded ->
            if (targetExpanded) {
                Text("asdasdqwe\nqweqwe\nqwa\nsasqwe")
            } else {
                    Text("Click me")
            }
        }
    }
}


@Composable
fun ScaffoldExample() {

    // create a scaffold state, set it to close by default
    val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Closed))

    // Create a coroutine scope. Opening of
    // Drawer and snackbar should happen in
    // background thread without blocking main thread
    val coroutineScope = rememberCoroutineScope()

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
                })
        },

        bottomBar = { BottomBar() },

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