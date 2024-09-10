package com.example.todoapplication.data.repo

import com.example.todoapplication.data.datasource.YapilacaklarDataSource
import com.example.todoapplication.data.entity.Yapilacaklar

class YapilacaklarRepository(var yds:YapilacaklarDataSource) {


    suspend fun kaydet(yapilacak_ad : String) = yds.kaydet(yapilacak_ad)

    suspend fun guncelle(yapilacak_id : Int, yapilacaklar_ad: String) = yds.guncelle(yapilacak_id, yapilacaklar_ad)

    suspend fun sil(yapilacak_id : Int) = yds.sil(yapilacak_id)

    suspend fun yapilacaklariYukle() : List<Yapilacaklar> = yds.yapilacaklariYukle()

    suspend fun ara(aramaKelimesi: String) : List<Yapilacaklar> = yds.ara(aramaKelimesi)

}