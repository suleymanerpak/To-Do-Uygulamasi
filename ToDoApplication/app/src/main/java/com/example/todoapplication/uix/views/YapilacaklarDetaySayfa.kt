package com.example.todoapplication.uix.views

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.todoapplication.data.entity.Yapilacaklar
import com.example.todoapplication.uix.viewmodel.YapilacaklarDetayViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun YapilacaklarDetaySayfa(gelenYapilacak: Yapilacaklar, yapilacaklarDetayViewModel: YapilacaklarDetayViewModel){
    val tfYapilacaklarAd = remember { mutableStateOf("") }

    LaunchedEffect(key1 = true)
    {
        tfYapilacaklarAd.value = gelenYapilacak.yapilacak_ad
    }


    Scaffold(topBar = { TopAppBar(title = { Text(text = "Yapılacaklar Detay Sayfa") }) })
    { paddingValues ->
        Column(modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        )
        {
            TextField(value = tfYapilacaklarAd.value, onValueChange = {tfYapilacaklarAd.value = it},
                label = { Text(text = "Yapılacak")}
            )
            Button(
                modifier = Modifier.size(250.dp,50.dp),
                onClick = {
                    yapilacaklarDetayViewModel.guncelle(gelenYapilacak.yapilacak_id,tfYapilacaklarAd.value)
                }) {
                Text(text = "GÜNCELLE")
            }
        }
    }
}