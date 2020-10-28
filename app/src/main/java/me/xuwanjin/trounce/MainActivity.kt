package me.xuwanjin.trounce

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.setContent
import androidx.ui.tooling.preview.Preview
import me.xuwanjin.trounce.ui.TrounceTheme
import androidx.compose.foundation.Text
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.material.Switch
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.state
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import me.xuwanjin.trounce.model.Peer
import me.xuwanjin.trounce.model.WireGuard
import me.xuwanjin.trounce.model.WireGuardInterface
import androidx.compose.material.IconButton as IconButton

class MainActivity : AppCompatActivity() {
    @ExperimentalLazyDsl
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Column() {
                    toolBar()
                    wireGuardItemList(modifier = Modifier.fillMaxWidth().wrapContentHeight())
                }

            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    TrounceTheme {
        Greeting("Android")
    }
}

@Preview(showBackground = true)
@Composable
fun toolBar() {
    TopAppBar(
        modifier = Modifier.fillMaxWidth()
            .wrapContentHeight(),
        title = { Text(text = "WireGuard") },
        actions = {
            IconButton(onClick = {}) {
                Icon(Icons.Filled.List)
            }
        }
    )
}

@ExperimentalLazyDsl
@Composable
fun wireGuardItemList(modifier: Modifier) {
    val wireGuard: WireGuard = WireGuard(
        id = "1",
        isInActive = false,
        wireGuardInterface = WireGuardInterface(
            "Matthew",
            "",
            "",
            "",
        ),
        peerList = listOf(Peer(
            "",
            "",
            "",
            ""
        )),
    )
    val itemsIndexedList = listOf(wireGuard, wireGuard, wireGuard, wireGuard, wireGuard)

    LazyColumnFor(itemsIndexedList, modifier = modifier) {
        Row(
            modifier = modifier.clickable(onClick = {

            })
        ) {
            ConstraintLayout(
                modifier = modifier.padding(
                    top = 14.dp, bottom = 14.dp,
                )
            ) {
                val (configName, switchBar) = createRefs()
                Text(
                    modifier = Modifier.constrainAs(configName) {
                        start.linkTo(parent.start)
                        top.linkTo(parent.top)
                    }.padding(
                        start = 20.dp,
                    ),
                    text = it.wireGuardInterface.name,
                    color = Color.Blue,
                    fontSize = 18.sp,
                )
                val checkedState = state { wireGuard.isInActive }
                Switch(
                    modifier = Modifier.constrainAs(switchBar) {
                        top.linkTo(parent.top)
                        end.linkTo(parent.end)
                    }.padding(
                        end = 20.dp,
                    ),
                    checked = checkedState.value,
                    onCheckedChange = {
                        wireGuard.isInActive = it
                        checkedState.value = it
                    },
                )
            }
        }
    }
}
