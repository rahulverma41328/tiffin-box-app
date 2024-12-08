package com.example.tifinbox.auth

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.tiffinbox.auth.RegisterScreen
import com.example.tiffinbox.auth.ScreenLogin
import com.example.tiffinbox.auth.ScreenSplash
import com.example.tifinbox.auth.viewModel.RegisterViewModel
import com.example.tifinbox.dashboard.DashboardActivity
import com.example.tifinbox.routes.AuthRoutes
import com.example.tifinbox.ui.theme.TifinBOXTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TifinBOXTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val navController = rememberNavController()
                    val registerViewModel = ViewModelProvider(this)[RegisterViewModel::class.java]
                    NavHost(navController = navController, startDestination = AuthRoutes.splashScreen, builder = {
                        composable(AuthRoutes.splashScreen,){
                            ScreenSplash(navController = navController)
                        }
                        composable(AuthRoutes.introductionScreen,){
                            ScreenIntroduction(navController)
                        }
                        composable(AuthRoutes.registerScreen) {
                            RegisterScreen(navController,registerViewModel)
                        }
                        composable(AuthRoutes.loginScreen) {
                            ScreenLogin(navController)
                        }
                        composable(AuthRoutes.locationScreen) {
                            ScreenVerifyOtp(navController, registerViewModel){
                                val intent = Intent(this@MainActivity, DashboardActivity::class.java)
                                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                                startActivity(intent)
                            }
                        }

                    })
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {

}