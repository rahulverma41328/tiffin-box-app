package com.example.tifinbox.dashboard.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.random.Random

@Composable
fun ScreenHome(){
    Column(modifier = Modifier.fillMaxSize()) {
        PromotionalBanner()
    }
}

@Composable
fun PromotionalBanner() {
    val listSate = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()
    val originalItems = (1..5).toList()
    val infiniteItems = remember { originalItems + originalItems }

    LaunchedEffect(Unit) {
        while (true){
            delay(2000L)
            coroutineScope.launch {
                val nextIndex = (listSate.firstVisibleItemIndex + 1)
                if (nextIndex >= infiniteItems.size/2){
                    listSate.scrollToItem(0)
                }else{
                    listSate.animateScrollToItem(nextIndex)
                }
            }
        }
    }
    LazyRow(
        state = listSate,
        modifier = Modifier.fillMaxWidth()) {
        itemsIndexed((infiniteItems).toList()){_,_ ->
            Card(
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier.padding(16.dp)
                    .width(300.dp)
                    .height(150.dp),
                colors = CardDefaults.cardColors(containerColor = Color(
                    Random.nextFloat(),
                    Random.nextFloat(),
                    Random.nextFloat(),
                    1f
                ))
            ){}
        }

    }

}
