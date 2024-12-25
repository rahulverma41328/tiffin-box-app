package com.example.tifinbox.dashboard.screen

import android.content.Context
import android.content.Intent
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tifinbox.R
import com.example.tifinbox.helper.CustomFont
import com.example.tifinbox.helper.RatingBar
import com.example.tifinbox.productsDetails.AllProduct

@Composable
fun ScreenHome(){
    val scrollState = rememberScrollState()
    Column(modifier = Modifier.fillMaxSize()
        .verticalScroll(scrollState),
        horizontalAlignment = Alignment.CenterHorizontally) {
        val context = LocalContext.current
        CarousalBanner()
        PopularServiceProvider(context)
        PopularMeals()
    }
}

@Composable
fun PopularMeals() {
    Column (
        Modifier
            .fillMaxWidth()
            .padding(20.dp)
            .wrapContentHeight()
    ){
        Row(horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()){
            Text("Popular Meals",
                fontFamily = CustomFont.customFontFamily,
                fontWeight = FontWeight.Bold
                , fontSize = 15.sp)

            Button(onClick = {

            },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.White,
                    contentColor = Color(0xFF00A876)
                ),
                shape = RoundedCornerShape(16.dp),
                elevation = ButtonDefaults.elevatedButtonElevation(
                    defaultElevation = 6.dp
                ),
                modifier = Modifier.padding(2.dp)) {
                Text("View All")
            }
        }

        MealsItems()
    }
}

@Composable
fun MealsItems() {
    val mealsItems = listOf(
        listOf(
            R.drawable.kitchen_photo_demo,
            "Daal Bati",
            "rating",
        ),
        listOf(
            R.drawable.kitchen_photo_demo,
            "Shahi Paneer",
            "rating",
        ),
        listOf(
            R.drawable.kitchen_photo_demo,
            "Malai Kofta",
            "rating",
        ),
        listOf(
            R.drawable.kitchen_photo_demo,
            "Aloo Sabji",
            "rating",
        )

    )

    val mealsItemState = rememberPagerState(pageCount = { mealsItems.size })

    Row {

        LazyRow(contentPadding = PaddingValues(end = 150.dp),
            modifier = Modifier
                .wrapContentSize()
                .padding(top = 10.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)) { items(mealsItems.size){currentPage ->
            Card(
                modifier = Modifier
                    .width(150.dp)
                    .height(200.dp),
                colors = CardDefaults.cardColors(Color.White),
                elevation = CardDefaults.cardElevation(2.dp)
            ) {
                Box(Modifier.fillMaxSize()){
                    Image(modifier = Modifier.fillMaxSize(),
                        painter = painterResource(mealsItems[currentPage][0] as Int),
                        contentDescription = "",
                        contentScale = ContentScale.Crop)

                    Box(
                        Modifier
                            .fillMaxSize()
                            .background(
                                Brush.verticalGradient(
                                    colors = listOf(
                                        Color.Transparent,
                                        Color.Black
                                    ),
                                    startY = 300f
                                )
                            ))
                    Box(contentAlignment = Alignment.BottomStart,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(start = 20.dp, bottom = 10.dp)){

                        Column  {
                            Text(mealsItems[currentPage][1] as String,
                                fontFamily = CustomFont.customFontFamily,
                                fontWeight = FontWeight.Normal,
                                color = Color.White,
                                fontSize = 15.sp)

                            RatingBar(3.4)

                        }

                    }
                }
            }
        }

        }

    }
}

@Composable
fun PopularServiceProvider(context: Context) {
    Column (
        Modifier
            .fillMaxWidth()
            .padding(20.dp)
            .wrapContentHeight()){

        Row(horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()){
            Text("Popular Kitchen",
                fontFamily = CustomFont.customFontFamily,
                fontWeight = FontWeight.Bold
                , fontSize = 15.sp)

            Button(onClick = {
                val intent = Intent(context,AllProduct::class.java).apply {
                    putExtra("kitchen_all","data")
                }
                context.startActivity(intent)
            },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.White,
                    contentColor = Color(0xFF00A876)
                ),
                shape = RoundedCornerShape(16.dp),
                elevation = ButtonDefaults.elevatedButtonElevation(
                    defaultElevation = 6.dp
                ),
                modifier = Modifier.padding(2.dp)) {
                Text("View All")
            }
        }


        ServiceProvider()


    }
}

@Composable
fun ServiceProvider() {
    val kitchenItems = listOf(
        listOf(
            R.drawable.kitchen_photo_demo,
            "Shalu Kitchen",
            "rating",
        ),
        listOf(
            R.drawable.kitchen_photo_demo,
            "Shalu Kitchen",
            "rating",
        ),
        listOf(
            R.drawable.kitchen_photo_demo,
            "Shalu Kitchen",
            "rating",
        ),
        listOf(
            R.drawable.kitchen_photo_demo,
            "Shalu Kitchen",
            "rating",
        )

    )

   // val kitchenItemState = rememberPagerState(pageCount = { kitchenItems.size })

    Row {

        LazyRow(
            modifier = Modifier
                .wrapContentSize()
                .padding(top = 10.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            items(kitchenItems.size){
                currentPage ->
                Card(
                    modifier = Modifier
                        .width(150.dp)
                        .height(200.dp),
                    colors = CardDefaults.cardColors(Color.White),
                    elevation = CardDefaults.cardElevation(2.dp)
                ) {
                    Box(Modifier.fillMaxSize()){
                        Image(modifier = Modifier.fillMaxSize(),
                            painter = painterResource(kitchenItems[currentPage][0] as Int),
                            contentDescription = "",
                            contentScale = ContentScale.Crop)

                        Box(
                            Modifier
                                .fillMaxSize()
                                .background(
                                    Brush.verticalGradient(
                                        colors = listOf(
                                            Color.Transparent,
                                            Color.Black
                                        ),
                                        startY = 300f
                                    )
                                ))
                        Box(contentAlignment = Alignment.BottomStart,
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(start = 20.dp, bottom = 10.dp)){

                            Column  {
                                Text(kitchenItems[currentPage][1] as String,
                                    fontFamily = CustomFont.customFontFamily,
                                    fontWeight = FontWeight.Normal,
                                    color = Color.White,
                                    fontSize = 15.sp)

                                RatingBar(3.4)

                            }

                        }
                    }
                }
            }
        }

    }
}





@Composable
fun CarousalBanner(){
    val images = listOf(
        R.drawable.carousel_img,
        R.drawable.carousel_img,
        R.drawable.carousel_img,
        R.drawable.carousel_img,
        R.drawable.carousel_img,
    )

    val pagerState = rememberPagerState(pageCount = { images.size })

    Box(modifier = Modifier.wrapContentSize()){
        HorizontalPager(state = pagerState,
            Modifier.wrapContentSize()
                ) { currentPage ->

            Card(
                modifier = Modifier
                    .wrapContentSize()
                    .padding(start = 20.dp, end = 20.dp, top = 10.dp, bottom = 5.dp),
                elevation = CardDefaults.cardElevation(8.dp)
            ) {
                Image(painter = painterResource(id = images[currentPage]), contentDescription = "banner")
            }
        }
    }

    PageIndicator(
        pageCount = images.size,
        currentPage = pagerState.currentPage,
        modifier = Modifier
    )
}

@Composable
fun PageIndicator(pageCount: Int, currentPage: Int, modifier: Modifier.Companion) {
    Row (
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ){
        repeat(pageCount){
            IndicatorDots(isSelected = it == currentPage)
        }
    }
}

@Composable
fun IndicatorDots(isSelected: Boolean) {
    val size = animateDpAsState(targetValue = if(isSelected) 8.dp else 6.dp, label = "")
    Box(modifier = Modifier
        .padding(2.dp)
        .size(size.value)
        .clip(CircleShape)
        .background(if (isSelected) Color(0xff373737) else Color(0x32000000)))
}
