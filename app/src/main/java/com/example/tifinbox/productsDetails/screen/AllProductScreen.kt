package com.example.tifinbox.productsDetails.screen

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.tifinbox.R
import com.example.tifinbox.helper.CustomFont
import com.example.tifinbox.helper.RatingBar
import com.example.tifinbox.helper.StoreUserData
import com.example.tifinbox.navGraph.SPDetailGraph
import com.example.tifinbox.productsDetails.model.AllServiceProviderModel
import com.example.tifinbox.productsDetails.model.ServiceProviderModel
import com.example.tifinbox.productsDetails.viewModel.ServiceProviderViewModel
import com.example.tifinbox.routes.ProductDetailRoutes
import com.example.tifinbox.ui.theme.appGreen
import com.example.tifinbox.util.Resource
import kotlinx.coroutines.flow.first


@Composable
fun AllProductScreen(padding: PaddingValues,spViewModel: ServiceProviderViewModel,userData: StoreUserData,navController: NavHostController){

    val allSP = spViewModel.getAllSP.collectAsStateWithLifecycle()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(padding)
    ) {
        LaunchedEffect(Unit) {
            val cookie = userData.jwtToken.first()
            spViewModel.getAllSP(cookie = cookie)
        }

        ShowAllServiceProvider(spViewModel = spViewModel,navController,allSP)
    }
}

@Composable
fun ShowAllServiceProvider(spViewModel: ServiceProviderViewModel,navController:NavHostController,allSP: State<Resource<AllServiceProviderModel>>) {
    when(val resource = allSP.value){
        is Resource.Error -> {
            Toast.makeText(LocalContext.current,"unable to load data",Toast.LENGTH_SHORT).show()
            Log.e("data","error")
        }
        is Resource.Loading -> {
            Box(
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(color = appGreen)
                Log.e("data","loading")
            }
        }
        is Resource.Success -> {
            Log.e("resource", "data fetched")
            resource.data?.let { allServiceProviderModel ->
                ServiceProviderList(allServiceProviderModel.sp,navController)
            }?: run {
                Text(
                    text = "No service providers found.",
                    modifier = Modifier.fillMaxSize(),
                    textAlign = TextAlign.Center
                )
            }

        }
        is Resource.Unspecified -> Unit
    }


}

@Composable
fun ServiceProviderList(serviceProviders: List<ServiceProviderModel>,navController: NavHostController) {


    LazyColumn(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)) {

        items(serviceProviders.size){ serviceProvider ->
            ServiceProviderCard(serviceProviders,serviceProvider,navController)
        }
    }
}

@Composable
fun ServiceProviderCard(serviceProvider: List<ServiceProviderModel>,position:Int,navController: NavHostController) {

    Card (
        modifier = Modifier
            .clickable {
                navController.navigate("${ProductDetailRoutes.spDetails}/$position")
            }
            .fillMaxWidth()
            .height(140.dp),
        colors = CardDefaults.cardColors(Color.White),
        elevation = CardDefaults.cardElevation(2.dp)
    ){
        Row(modifier = Modifier.fillMaxHeight()) {
            Image(modifier = Modifier
                .width(120.dp)
                .fillMaxHeight(),
                painter = painterResource(R.drawable.kitchen_photo_demo),
                contentDescription = "",
                contentScale = ContentScale.Crop
            )

            Column(modifier = Modifier
                .padding(start = 8.dp, top = 8.dp, end = 8.dp)) {

                Row(
                    Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.Top,
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    Text(
                        modifier = Modifier.weight(1f),
                        text = serviceProvider[position].name,
                        fontFamily = CustomFont.customFontFamily,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black,
                        fontSize = 15.sp)

                    RatingBar(3.5,5)
                }

                Text(
                    text = serviceProvider[position].phone,
                    fontFamily = CustomFont.customFontFamily,
                    fontWeight = FontWeight.Normal,
                    color = Color.Gray,
                    fontSize = 12.sp)

                Text(
                    text = serviceProvider[position].address.toString(),
                    fontFamily = CustomFont.customFontFamily,
                    fontWeight = FontWeight.Medium,
                    color = Color.Black,
                    fontSize = 12.sp,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis)
            }
        }
    }
}


