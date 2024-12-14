package com.example.tifinbox.productsDetails

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.tifinbox.helper.CustomFont
import com.example.tifinbox.productsDetails.screen.AllProductScreen
import com.example.tifinbox.productsDetails.viewModel.ServiceProviderViewModel
import com.example.tifinbox.ui.theme.TifinBOXTheme

class AllProduct : ComponentActivity() {

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val intent = Intent()
        val data = intent.getStringExtra("kitchen_all")
        val viewModel = ViewModelProvider(this)[ServiceProviderViewModel::class.java]


        viewModel.getAllSP(applicationContext)
        setContent {
            TifinBOXTheme {
                val navController = rememberNavController()
                Scaffold(
                   topBar = {
                       TopAppBar(
                           title = {
                               Text("All Product",
                                   fontSize = 20.sp,
                                   fontFamily = CustomFont.customFontFamily,
                                   fontWeight = FontWeight.Bold)
                           },
                           Modifier.shadow(9.dp, ambientColor = Color.Black))
                   },
                    modifier = Modifier
                ){ innerPadding ->

                    Column {

                        AllProductScreen(innerPadding, viewModel = viewModel)
                    }
                }
            }
        }
    }
}