package com.example.lazygridtest

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
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
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LazyGridTestTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    //modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    LazyVerticalGrid()
                    Reset()
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
fun Reset(){
    Button (
        onClick = {},
        modifier = Modifier.fillMaxWidth()
            .padding(top = 8.dp)
            .padding(start = 12.dp)
            .padding(end = 12.dp)
    ){
        Text("リセット")
    }
}

@Composable
fun DisplayCount(){
    Text(
        modifier = Modifier.padding(bottom = 8.dp),
        text= "引いた枚数"
    )
}


@Composable
fun LazyVerticalGrid(context: Context = LocalContext.current.applicationContext){
    val list = (1..80).map{ it.toString() }
    val list01 = mutableListOf( "A","A","B","C","C","D","E","F","G","G","G","G","G","G","G","G","G","G","G","G","G","G","G","G","G","G","G","G","G","G","G","G","G","G","H","H","H","H","H","H","H","H","H","H","H","H","H","H","H","H","H","H","H","H","H","H","H","H","H","H","I","I","I","I","I","I","I","I","I","I","I","I","I","I","I","I","I","I","I","I" )
    val shuffledList = list01.shuffled()
    var count: Int = 0

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
                Text(text = "HEADER", modifier = Modifier.padding(8.dp))
            }
        }*/
        items(list01.size) { index ->
            var isClicked by rememberSaveable { mutableStateOf(false) }
            Card(

                backgroundColor = Color.Red,
                modifier = Modifier.padding(4.dp).fillMaxWidth()
                    .clickable {
                        if (!isClicked){ count++; isClicked = true}
                        Toast.makeText(context, "引いた枚数:$count", Toast.LENGTH_SHORT).show()
                               },
                elevation = 8.dp,
            ) {
                if ( isClicked ){
                    Text(
                        text = shuffledList[index],
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
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    LazyGridTestTheme {
        Greeting()
        LazyVerticalGrid()
    }
}