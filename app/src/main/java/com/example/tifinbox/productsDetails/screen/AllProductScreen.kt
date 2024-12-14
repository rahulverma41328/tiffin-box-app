package com.example.tifinbox.productsDetails.screen

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.tifinbox.api.RetrofitInstance
import com.example.tifinbox.api.ServiceProvider
import com.example.tifinbox.productsDetails.viewModel.ServiceProviderViewModel
import com.example.tifinbox.util.Resource
import kotlinx.coroutines.launch

@Composable
fun AllProductScreen(padding: PaddingValues,viewModel: ServiceProviderViewModel){

    val allSP = viewModel.getAllSP.collectAsState()
    Column(
        modifier = Modifier.fillMaxSize().padding(padding)
    ) {
        Button(onClick = {

        },Modifier.padding(100.dp)) {
            Text("click here", color = Color.Black)
        }
    }
}
