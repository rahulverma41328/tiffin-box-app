package com.example.tiffinbox.auth

import android.content.Intent
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.key
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.tifinbox.R
import com.example.tifinbox.dashboard.DashboardActivity
import com.example.tifinbox.helper.StoreUserData
import com.example.tifinbox.routes.AuthRoutes
import kotlinx.coroutines.delay

@Composable
fun ScreenSplash(navController: NavController,onNavigate:() -> Unit){

    val context = LocalContext.current
    val userData = StoreUserData(context)

    LaunchedEffect(key1 = true) {
        delay(2000)
        userData.userLogin.collect{isLogin ->
            if (isLogin){
                onNavigate()
            }
        }
        navController.navigate(AuthRoutes.introductionScreen)
    }
    Scaffold(modifier = Modifier) { innerPadding ->

        Column  (modifier = Modifier.fillMaxSize().padding(innerPadding),
             horizontalAlignment = Alignment.CenterHorizontally){
            Spacer(Modifier.height(200.dp))
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = null,
                modifier = Modifier.width(250.dp).height(250.dp)
            )
            Text(modifier = Modifier.padding(20.dp),
                fontFamily = FontFamily.SansSerif,
                fontSize = 30.sp,
                text = "Order Your Homemade Food",
                textAlign = TextAlign.Center
            )
            Text(modifier = Modifier.padding(30.dp),
                textAlign = TextAlign.Center,
                text = "TiffinBox")

            Text(modifier = Modifier.padding(40.dp),
                text = "Designed by \n WolfCart",
                textAlign = TextAlign.Center,
                fontSize = 10.sp,
                fontFamily = FontFamily.SansSerif
            )
        }

    }
}

@Preview(showBackground = true)
@Composable
fun ScreenSplashPreview(){
}