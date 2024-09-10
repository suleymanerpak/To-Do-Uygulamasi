package com.example.todoapplication.data.datasource

import android.util.Log
import com.example.todoapplication.data.entity.Yapilacaklar
import com.example.todoapplication.room.YapilacaklarDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class YapilacaklarDataSource(var ydao : YapilacaklarDao) {

    suspend fun kaydet(yapilacak_ad : String){
        val yeniYapilacak = Yapilacaklar(0,yapilacak_ad)
        ydao.kaydet(yeniYapilacak)
    }

    suspend fun guncelle(yapilacak_id : Int, yapilacak_ad: String){
        val guncellenenYapilacak = Yapilacaklar(yapilacak_id, yapilacak_ad)
        ydao.guncelle(guncellenenYapilacak)
    }

    suspend fun sil(yapilacak_id : Int){
        val silinenYapilacak = Yapilacaklar(yapilacak_id,"")
        ydao.sil(silinenYapilacak)
    }

    suspend fun yapilacaklariYukle() : List<Yapilacaklar> = withContext(Dispatchers.IO){

        return@withContext ydao.yapilacaklariYukle()
    }

    suspend fun ara(aramaKelimesi: String) : List<Yapilacaklar> = withContext(Dispatchers.IO){

        return@withContext ydao.ara(aramaKelimesi)
    }

}