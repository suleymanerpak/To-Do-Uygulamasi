package com.example.todoapplication.uix.views

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.todoapplication.data.entity.Yapilacaklar
import com.example.todoapplication.uix.viewmodel.AnasayfaViewModel
import com.example.todoapplication.uix.viewmodel.YapilacaklarDetayViewModel
import com.example.todoapplication.uix.viewmodel.YapilacaklarKayitViewModel
import com.google.gson.Gson

@Composable
fun SayfaGecisleri(anasayfaViewModel: AnasayfaViewModel,
                   yapilacaklarKayitViewModel: YapilacaklarKayitViewModel,
                   yapilacaklarDetayViewModel: YapilacaklarDetayViewModel
){
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "anasayfa")
    {
         composable("anasayfa"){
             Anasayfa(navController, anasayfaViewModel)
         }
        composable("yapilacaklarKayitSayfa"){
            YapilacaklarKayitSayfa(yapilacaklarKayitViewModel)
        }
        composable("yapilacaklarDetaySayfa/{yapilacak}",
            arguments = listOf(
                navArgument("yapilacak"){type = NavType.StringType}
            )
            ){
            val json = it.arguments?.getString("yapilacak")
            val nesne = Gson().fromJson(json,Yapilacaklar::class.java)
            YapilacaklarDetaySayfa(nesne,yapilacaklarDetayViewModel)
        }
    }
}