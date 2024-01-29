package com.example.rolldice

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.rolldice.ui.theme.RollDiceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RollDiceTheme {
                RollDiceWithButtonAndImage()
            }
        }
    }
}

@Composable
fun RollDiceWithButtonAndImage(modifier: Modifier =
                                   Modifier
                                       .fillMaxSize()
                                       .wrapContentSize(align = Alignment.Center))
{
    var gameCount by remember{mutableStateOf(0)}
    var tapCount by remember{mutableStateOf(0)}
    var randTapNum by remember {mutableStateOf((5..10).random())}

    Column (modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally)
    {
        var ID by remember{mutableStateOf(1)}
        val orangeImageID = when (ID)
        {
              1-> R.drawable.orange_tree
             2-> R.drawable.orange_fruit
            3-> R.drawable.orange_drink_full
            else-> R.drawable.orange_drink_empty
        }

        var textID by remember{mutableStateOf(1)}
        val text = when (textID)
        {
            1-> "Tap the orange tree to select an orange!"
            2-> "Keep tapping the orange to squeeze it"
            3-> "Tap the orange juice to drink it"
            else-> "Tap the empty glass to start again"
        }


        Button(onClick = {
            tapCount++
            if(tapCount <= randTapNum)
            {
                ID = 2
                textID = 2

            }
            else if(tapCount == 1 + randTapNum)
            {
                ID = 3
                textID = 3
            }
            else if(tapCount == 2 + randTapNum)
            {
                ID = 4
                textID = 4
            }
            else
            {
                gameCount += 1
                tapCount = 0
                ID = 1
                textID = 1

               randTapNum = (6..10).random()
            }

        })
        {

            Image(painter = painterResource(orangeImageID),
                contentDescription = "orange")
        }


        Spacer(modifier = Modifier.height(60.dp))
        Text(text = "$text")

        Spacer(modifier = Modifier.height(120.dp))
        Text(
            text = "Game count: $gameCount",
            modifier = Modifier
                .align(Alignment.Start)
        )
//        Text(text = "tap count: $tapCount")
//        Text(text = "randTapNum: $randTapNum")

    }

}

@Preview(showBackground = true)
@Composable
fun RollDiceWithButtonAndImagePreview()
{
    RollDiceWithButtonAndImage()
}