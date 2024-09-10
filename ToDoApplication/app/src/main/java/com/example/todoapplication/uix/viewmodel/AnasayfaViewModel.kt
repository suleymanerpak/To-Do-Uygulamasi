package com.example.todoapplication.uix.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.todoapplication.data.entity.Yapilacaklar
import com.example.todoapplication.data.repo.YapilacaklarRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AnasayfaViewModel @Inject constructor(var yrepo:YapilacaklarRepository) : ViewModel() {
    var yapilacaklarListesi = MutableLiveData<List<Yapilacaklar>>()

    init {
        yapilacaklariYukle()
    }

    fun sil(yapilacak_id : Int){
        CoroutineScope(Dispatchers.Main).launch {
            yrepo.sil(yapilacak_id)
            yapilacaklariYukle()
        }
    }

    fun yapilacaklariYukle(){
        CoroutineScope(Dispatchers.Main).launch {
            yapilacaklarListesi.value = yrepo.yapilacaklariYukle()
        }
    }

    fun ara(aramaKelimesi: String){
        CoroutineScope(Dispatchers.Main).launch {
            yapilacaklarListesi.value = yrepo.ara(aramaKelimesi)
        }
    }
}