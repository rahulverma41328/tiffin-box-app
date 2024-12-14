package com.example.tifinbox.productsDetails.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.tifinbox.productsDetails.viewModel.ServiceProviderViewModel
import com.example.tifinbox.util.Resource

@Composable
fun AllProductScreen(padding: PaddingValues, navigation:String,viewModel: ServiceProviderViewModel){

    val allSP = viewModel.getAllSP.collectAsState()
    Column(
        modifier = Modifier.fillMaxSize().padding(padding)
    ) {
        LazyColumn {
            when(allSP.value){
                is Resource.Error -> TODO()
                is Resource.Loading -> TODO()
                is Resource.Success -> TODO()
                is Resource.Unspecified -> TODO()
            }
        }
    }
}