package com.example.todoapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import com.example.todoapplication.ui.theme.ToDoApplicationTheme
import com.example.todoapplication.uix.viewmodel.AnasayfaViewModel
import com.example.todoapplication.uix.viewmodel.YapilacaklarDetayViewModel
import com.example.todoapplication.uix.viewmodel.YapilacaklarKayitViewModel
import com.example.todoapplication.uix.views.SayfaGecisleri
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    val anasayfaViewModel:AnasayfaViewModel by viewModels()
    val yapilacaklarDetayViewModel: YapilacaklarDetayViewModel by viewModels()
    val yapilacaklarKayitViewModel : YapilacaklarKayitViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ToDoApplicationTheme {
                SayfaGecisleri(anasayfaViewModel,yapilacaklarKayitViewModel,yapilacaklarDetayViewModel)
            }
        }
    }
}

