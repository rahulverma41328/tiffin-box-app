package com.example.tifinbox.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.tifinbox.R
import com.example.tifinbox.routes.AuthRoutes
import com.example.tifinbox.ui.theme.appGreen

@Composable
fun ScreenIntroduction(navController: NavController){

    Scaffold(modifier = Modifier) { innerPadding ->

        Column  (modifier = Modifier.fillMaxSize().padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally){
            Spacer(Modifier.height(100.dp))
            Image(
                painter = painterResource(id = R.drawable.backkground_start_1),
                contentDescription = null,
                modifier = Modifier.fillMaxWidth()
            )
            Text(modifier = Modifier.padding(20.dp),
                fontFamily = FontFamily.SansSerif,
                fontSize = 30.sp,
                color = appGreen,
                text = "Enjoy homemade Quality Meals at anywhere",
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.weight(1f))
            Button(onClick = {
                navController.navigate(AuthRoutes.registerScreen)
            },
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = appGreen,
                    contentColor = Color.White
                ),
                modifier = Modifier.padding(20.dp)
            )
            {
                Text("Next",modifier = Modifier,
                    fontFamily = FontFamily.SansSerif,
                    fontSize = 10.sp)
            }
        }

    }
}


@Preview(showBackground = true)
@Composable
fun ScreenIntroductionPreview(){

}