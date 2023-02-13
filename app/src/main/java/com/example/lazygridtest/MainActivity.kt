package com.example.lazygridtest

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lazygridtest.ui.theme.LazyGridTestTheme

class MainActivity : ComponentActivity() {
    private val list01 = mutableListOf( "A","A","B","C","C","D","E","F","G","G","G","G","G","G","G","G","G","G","G","G","G","G","G","G","G","G","G","G","G","G","G","G","G","G","H","H","H","H","H","H","H","H","H","H","H","H","H","H","H","H","H","H","H","H","H","H","H","H","H","H","I","I","I","I","I","I","I","I","I","I","I","I","I","I","I","I","I","I","I","I" )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val shuffledlist = list01.shuffled()
            LazyGridTestTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    //modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    LazyVerticalGrid(shuffledlist)
                    //Reset()
                }
            }
        }
    }
}

@Composable
fun Greeting() {
    Text(text = "くじシミュレーション")
}

@Composable
fun ExtendedFloatingActionButton(){
    val context = LocalContext.current
    Box(modifier = Modifier.fillMaxSize()){
        ExtendedFloatingActionButton(
            icon = { Icon(Icons.Filled.Refresh, "")},
            text = { Text("リセット")},
            onClick = { context.startActivity(Intent(context, MainActivity::class.java))},
            modifier = Modifier.align(alignment = Alignment.BottomCenter)
                .padding(bottom = 100.dp),
            elevation = FloatingActionButtonDefaults.elevation(8.dp)
        )
    }

}

@Composable
fun Reset(){
    val activity = LocalContext.current as Activity
    //val dispatcher = LocalOnBackPressedDispatcherOwner.current?.onBackPressedDispatcher
    val context = LocalContext.current
    Button (
        //onClick = { activity.finish() },
        onClick = { context.startActivity(Intent(context, MainActivity::class.java)) },
        modifier = Modifier.fillMaxWidth()
            .padding(top = 16.dp)
            .padding(start = 12.dp)
            .padding(end = 12.dp)
    ){
        Text("リセット" ,fontWeight = FontWeight.Bold,)
    }
}

@Composable
fun LazyVerticalGrid( _list:List<String>){
    var count by remember { mutableStateOf(0) }
    var price: Int by remember { mutableStateOf(0)}
    val counter = rememberCounter()
    val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Open))

    Scaffold(
        scaffoldState = rememberScaffoldState(),
        topBar = {
            TopAppBar(
                title = { Text(text = "くじシミュレータ")},
                navigationIcon = {
                    IconButton(onClick = { })
                    { Icon(imageVector = Icons.Rounded.Menu, contentDescription = "Drawer Icon")}
                },
                actions = {
                    IconButton(onClick = {})
                    { Icon(imageVector = Icons.Rounded.Settings, contentDescription = "設定", tint = Color.White,)}
                },
            )
            //Reset()
                 },
        bottomBar = {
            Text(
                text = "枚数: $count　 $price 円",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
                    .padding(start = 12.dp)
                    .padding(end = 12.dp)
                    .padding(bottom = 8.dp)
            )
        },
    ){ padding ->
        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            contentPadding = PaddingValues(
                start = 12.dp,
                top = 8.dp,
                end = 12.dp,
                bottom = 8.dp
            ),
            modifier = Modifier.fillMaxHeight()
                .padding(top = 8.dp)
                .padding(bottom = 48.dp)
        ) {
            /*item(span = { GridItemSpan(maxLineSpan) }) {
                Surface(
                    color = MaterialTheme.colors.primaryVariant,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = "枚数 $count", modifier = Modifier.padding(8.dp))
                }
            }*/
            items(_list.size) { index ->
                var isClicked by rememberSaveable { mutableStateOf(false) }

                Card(
                    backgroundColor = Color.Red,
                    modifier = Modifier.padding(4.dp).fillMaxWidth()
                        .clickable {
                            if (!isClicked) {
                                count++
                                isClicked = true
                                price = count * 730
                                counter.increment()
                            }
                        },
                    elevation = 8.dp,
                ) {
                    if ( isClicked ){
                        Text(
                            text = _list[index],
                            fontWeight = FontWeight.Bold,
                            fontSize = 30.sp,
                            color = Color(0xFFFFFFFF),
                            textAlign = TextAlign.Center,
                            modifier = Modifier.padding(16.dp)
                        )
                    } else {
                        Text(
                            text = "?",
                            fontWeight = FontWeight.Bold,
                            fontSize = 30.sp,
                            color = Color(0xFFFFFFFF),
                            textAlign = TextAlign.Center,
                            modifier = Modifier.padding(16.dp)
                        )
                    }
                }
            }
        }
        ExtendedFloatingActionButton()
    }

    /*
    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        contentPadding = PaddingValues(
            start = 12.dp,
            top = 8.dp,
            end = 12.dp,
            bottom = 8.dp
        ),
        modifier = Modifier.fillMaxHeight()
            .padding(top = 60.dp)
            .padding(bottom = 60.dp)
    ) {
        /*item(span = { GridItemSpan(maxLineSpan) }) {
            Surface(
                color = MaterialTheme.colors.primaryVariant,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "枚数 $count", modifier = Modifier.padding(8.dp))
            }
        }*/
        items(_list.size) { index ->
            var isClicked by rememberSaveable { mutableStateOf(false) }

            Card(
                backgroundColor = Color.Red,
                modifier = Modifier.padding(4.dp).fillMaxWidth()
                    .clickable {
                        if (!isClicked) {
                            count++
                            isClicked = true
                            price = count * 730
                            counter.increment()
                        }
                    },
                elevation = 8.dp,
            ) {
                if ( isClicked ){
                    Text(
                        text = _list[index],
                        fontWeight = FontWeight.Bold,
                        fontSize = 30.sp,
                        color = Color(0xFFFFFFFF),
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(16.dp)
                    )
                } else {
                    Text(
                        text = "?",
                        fontWeight = FontWeight.Bold,
                        fontSize = 30.sp,
                        color = Color(0xFFFFFFFF),
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(16.dp)
                    )
                }
            }
        }
    }
    Column {
        Text(
            text = "枚数: $count　 $price 円",
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
                .padding(start = 12.dp)
                .padding(end = 12.dp)
        )
    }
    */
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    val list01 = mutableListOf( "A","A","B","C","C","D","E","F","G","G","G","G","G","G","G","G","G","G","G","G","G","G","G","G","G","G","G","G","G","G","G","G","G","G","H","H","H","H","H","H","H","H","H","H","H","H","H","H","H","H","H","H","H","H","H","H","H","H","H","H","I","I","I","I","I","I","I","I","I","I","I","I","I","I","I","I","I","I","I","I" )
    val shuffledlist = list01.shuffled()
    LazyGridTestTheme {
        Greeting()
        LazyVerticalGrid(shuffledlist)
    }
}