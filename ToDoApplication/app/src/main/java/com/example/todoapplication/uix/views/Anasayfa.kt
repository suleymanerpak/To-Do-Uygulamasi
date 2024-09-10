package com.example.todoapplication.uix.views

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.todoapplication.R
import com.example.todoapplication.data.entity.Yapilacaklar
import com.example.todoapplication.room.Veritabani
import com.example.todoapplication.uix.viewmodel.AnasayfaViewModel
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Anasayfa(navController: NavController, anasayfaViewModel: AnasayfaViewModel){
    val aramaYapiliyorMu = remember{ mutableStateOf(false) }
    val tf = remember{ mutableStateOf("") }
    val yapilacaklarListesi = anasayfaViewModel.yapilacaklarListesi.observeAsState(listOf())
    val scope = rememberCoroutineScope()
    val snackbarHostState = remember {SnackbarHostState()}

    val context = LocalContext.current
    val vt = Veritabani.veritabaniErisim(context)!!


    LaunchedEffect(key1 = true){
        anasayfaViewModel.yapilacaklariYukle()
    }


    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    if (aramaYapiliyorMu.value)
                    {
                        TextField(
                            value = tf.value,
                            onValueChange ={
                                tf.value = it
                                anasayfaViewModel.ara(it)
                            },
                            label = { Text(text = "Ara")},
                            colors = TextFieldDefaults.textFieldColors(
                                containerColor = Color.Transparent,
                                focusedLabelColor = Color.White,
                                focusedIndicatorColor = Color.White,
                                unfocusedLabelColor = Color.Black,
                                unfocusedIndicatorColor = Color.Black
                            ))
                    }
                    else{ Text(text = "Yapılacaklar")}},
                actions = {
                    if(aramaYapiliyorMu.value)
                    {
                        IconButton(onClick = {
                            aramaYapiliyorMu.value = false
                            tf.value= ""
                        }) {
                            Icon(painter = painterResource(id = R.drawable.kapat_resim) , contentDescription ="" )
                        }
                    }
                    else
                    {
                        IconButton(onClick = {
                            aramaYapiliyorMu.value = true
                        }) {
                            Icon(painter = painterResource(id = R.drawable.ara_resim) , contentDescription ="" )
                        }
                    }
                }
                )},
        floatingActionButton = { 
            FloatingActionButton(
                onClick = {navController.navigate("yapilacaklarKayitSayfa")},
                content = {Icon(painter = painterResource(id = R.drawable.ekle_resim), contentDescription ="Ekle Butonu") }
            ) 
        },
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        })
    {paddingValues ->
       LazyColumn(modifier = Modifier
           .fillMaxSize()
           .padding(paddingValues))
       {
           items(
               count = yapilacaklarListesi.value.count(),
               itemContent = {
                   val yapilacak = yapilacaklarListesi.value[it]
                   Card(modifier = Modifier.padding(all = 5.dp)) {
                       Row(modifier = Modifier
                           .fillMaxWidth()
                           .clickable {
                               val yapilacaklarJson = Gson().toJson(yapilacak)
                               navController.navigate("yapilacaklarDetaySayfa/$yapilacaklarJson")
                           },
                           horizontalArrangement = Arrangement.SpaceBetween,
                           verticalAlignment = Alignment.CenterVertically)
                       {
                           Column(modifier = Modifier.padding(all = 10.dp))
                           {
                               Text(text = yapilacak.yapilacak_ad, fontSize = 25.sp)
                               Spacer(modifier = Modifier.size(10.dp))
                           }
                           IconButton(onClick = {
                               scope.launch {
                                   val sb = snackbarHostState.showSnackbar(
                                       message = "'${yapilacak.yapilacak_ad}' adlı yapılacak silinsin mi ?",
                                       actionLabel = "EVET"
                                   )
                                   if(sb == SnackbarResult.ActionPerformed)
                                   {
                                       anasayfaViewModel.sil(yapilacak.yapilacak_id)
                                   }
                               }
                           })
                           {
                               Icon(painter = painterResource(id = R.drawable.kapat_resim), contentDescription ="Kapatma", tint = Color.Gray)
                           }
                       }
                   }
               }
           )
       }
    }
}
