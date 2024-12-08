package com.example.tiffinbox.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.magnifier
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.icons.outlined.Phone
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.tifinbox.R
import com.example.tifinbox.routes.AuthRoutes
import com.example.tifinbox.ui.theme.appGreen

@Composable
fun ScreenLogin(navController: NavController){

    Scaffold(modifier = Modifier) { padding ->
        Column(Modifier.padding(20.dp).fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally ) {
            LoginTopLayout()
            LoginMiddleLayout(navController)
        }
    }

}

@Composable
fun LoginMiddleLayout(navController: NavController) {

    var phone by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    OutlinedTextField(
        value = password,
        onValueChange = {password = it},
        placeholder = { Text("password",color = Color.Gray) },
        leadingIcon = {
            Icon(
                imageVector = Icons.Outlined.Lock,
                contentDescription = null,
                tint = Color.Gray
            )
        },
        shape = RoundedCornerShape(16.dp),
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = Color.LightGray,
            unfocusedBorderColor = Color.LightGray,
            focusedLabelColor = Color.Gray,
            unfocusedLabelColor = Color.Gray,
            cursorColor = Color.Black
        ),
        modifier = Modifier.fillMaxWidth().padding(top = 20.dp)
    )
    OutlinedTextField(
        value = phone,
        onValueChange = {phone = it},
        placeholder = { Text("phone", color = Color.Gray) },
        leadingIcon = {
            Icon(
                imageVector = Icons.Outlined.Phone,
                contentDescription = null,
                tint = Color.Gray
            )
        },
        shape = RoundedCornerShape(16.dp),
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = Color.LightGray,
            unfocusedBorderColor = Color.LightGray,
            focusedLabelColor = Color.Gray,
            unfocusedLabelColor = Color.Gray,
            cursorColor = Color.Black
        ),
        modifier = Modifier.fillMaxWidth().padding(top = 20.dp)
    )

    Button(onClick = {

    },
        shape = RoundedCornerShape(10.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = appGreen,
            contentColor = Color.White
        ),
        modifier = Modifier.padding(20.dp).fillMaxWidth()
    )
    {
        Text("Next",modifier = Modifier,
            fontFamily = FontFamily.SansSerif,
            fontSize = 20.sp)
    }

    Text("or",Modifier.fillMaxWidth(),
        textAlign = TextAlign.Center)
    Text("don't have an account?Register", color = appGreen,
        fontFamily = FontFamily.SansSerif,
        fontSize = 15.sp,
        modifier = Modifier.clickable {
            navController.navigate(AuthRoutes.registerScreen)
        })
}

@Composable
fun LoginTopLayout() {
    Spacer(Modifier.height(100.dp))

    Image(
        painter = painterResource(R.drawable.logo),
        contentDescription = null,
        modifier = Modifier.width(50.dp).height(50.dp)
    )

    Text(modifier = Modifier.padding(top = 40.dp),
        fontFamily = FontFamily.SansSerif,
        fontSize = 30.sp,
        color = Color.Black,
        text = "Login your account",
        textAlign = TextAlign.Center
    )
}
