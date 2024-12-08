package com.example.tifinbox.dashboard

import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Toolbar
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SmallTopAppBar
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tifinbox.ui.theme.TifinBOXTheme
import com.example.tifinbox.ui.theme.appGreen

class DashboardActivity: ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TifinBOXTheme {
                Scaffold(
                    modifier = Modifier,
                    topBar = {
                        TopAppBar(
                            title = {
                                Text("TiffinBox",
                                    fontSize = 20.sp,
                                    fontFamily = FontFamily.SansSerif)
                            },
                            Modifier.shadow(9.dp, ambientColor = Color.Black)
                            ,
                            actions = {
                                IconButton(onClick = {}) {
                                    Icon(
                                        imageVector = Icons.Outlined.Notifications,
                                        contentDescription = "notification",
                                        tint = appGreen,
                                        modifier = Modifier.size(30.dp)
                                    )
                                }
                            }
                        )
                    }) { innerPadding ->

                    Box(modifier = Modifier.fillMaxSize().padding(innerPadding)){
                        ScreenHome()
                    }
                }
            }
        }
    }
}
