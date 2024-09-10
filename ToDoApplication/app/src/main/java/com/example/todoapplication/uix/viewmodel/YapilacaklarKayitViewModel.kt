package com.example.todoapplication.uix.viewmodel

import androidx.lifecycle.ViewModel
import com.example.todoapplication.data.repo.YapilacaklarRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class YapilacaklarKayitViewModel @Inject constructor(var yrepo:YapilacaklarRepository) : ViewModel() {

    fun kaydet(yapilacak_ad : String){
        CoroutineScope(Dispatchers.Main).launch {
            yrepo.kaydet(yapilacak_ad)
        }
    }
}