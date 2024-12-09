package com.example.tifinbox.auth

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.tifinbox.auth.viewModel.RegisterViewModel
import com.example.tifinbox.ui.theme.appGreen
import com.example.tifinbox.util.Resource
import kotlinx.coroutines.flow.launchIn

@Composable
fun ScreenVerifyOtp(navController: NavController, registerViewModel: RegisterViewModel, onNavigate:() -> Unit){

    val checkOtp by registerViewModel.checkOtp.collectAsState()

    Scaffold(modifier = Modifier) { innerPadding ->
        Column(
            Modifier
                .padding(20.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally ) {

            Spacer(Modifier.height(100.dp))
            Text(modifier = Modifier.padding(top = 40.dp),
                fontFamily = FontFamily.SansSerif,
                fontSize = 30.sp,
                color = appGreen,
                text = "Enter the otp sent",
                textAlign = TextAlign.Center
            )
            Text("Just One step away from creating your account")
            var otp by remember { mutableStateOf("") }

            OutlinedTextField(
                value = otp,
                onValueChange = {otp = it},
                placeholder = { Text("xxxxxx",color = Color.Gray) },
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
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp)
            )

            Button(onClick = {
                registerViewModel.verifyOTP(otp = otp)
            },
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = appGreen,
                    contentColor = Color.White
                ),
                modifier = Modifier
                    .padding(20.dp)
                    .fillMaxWidth()
            )
            {
                Text("Next",modifier = Modifier,
                    fontFamily = FontFamily.SansSerif,
                    fontSize = 20.sp)
            }

        }

    }
    when(checkOtp){
        is Resource.Error -> {
            val context = LocalContext.current
            Toast.makeText(context,checkOtp.message,Toast.LENGTH_LONG).show()
        }
        is Resource.Loading -> {

        }
        is Resource.Success -> {
            onNavigate()
        }
        is Resource.Unspecified -> {}
    }

}