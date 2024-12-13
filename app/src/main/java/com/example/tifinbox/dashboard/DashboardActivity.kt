package com.example.tifinbox.dashboard

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import com.example.tifinbox.R
import com.example.tifinbox.dashboard.bottomnav.BottomBar
import com.example.tifinbox.dashboard.bottomnav.BottomNavGraph
import com.example.tifinbox.dashboard.screen.ScreenHome
import com.example.tifinbox.helper.CustomFont
import com.example.tifinbox.ui.theme.TifinBOXTheme
import com.example.tifinbox.ui.theme.appGreen

class DashboardActivity: ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TifinBOXTheme {
                val navController = rememberNavController()
                Scaffold(
                    bottomBar = { BottomBar(navController = navController) },
                    modifier = Modifier,
                    topBar = {
                        TopAppBar(
                            title = {
                                Text("TiffinBox",
                                    fontSize = 20.sp,
                                    fontFamily = CustomFont.customFontFamily,
                                    fontWeight = FontWeight.Bold
                                )
                            },
                            Modifier.shadow(9.dp, ambientColor = Color.Black)
                            ,
                            actions = {
                                IconButton(onClick = {}) {
                                    Icon(
                                        painter = painterResource(id = R.drawable.bell_icon),
                                        contentDescription = "notification",
                                        tint = appGreen,
                                        modifier = Modifier.size(20.dp)
                                    )
                                }
                            }
                        )
                    }) { innerPadding ->

                    Row(modifier = Modifier.padding(innerPadding)) {
                        BottomNavGraph(navController)
                    }
                }
            }
        }
    }
}
