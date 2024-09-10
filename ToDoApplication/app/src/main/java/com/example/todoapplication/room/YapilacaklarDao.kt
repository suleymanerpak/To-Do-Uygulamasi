package com.example.todoapplication.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.todoapplication.data.entity.Yapilacaklar

@Dao
interface YapilacaklarDao {
    @Query("SELECT * FROM yapilacaklar")
    suspend fun yapilacaklariYukle() : List<Yapilacaklar>

    @Insert
    suspend fun kaydet(yapilacaklar: Yapilacaklar)

    @Update
    suspend fun guncelle(yapilacaklar: Yapilacaklar)

    @Delete
    suspend fun sil(yapilacaklar: Yapilacaklar)

    @Query("SELECT* FROM yapilacaklar WHERE yapilacak_ad like '%' || :aramaKelimesi || '%' ")
    suspend fun ara(aramaKelimesi:String) : List<Yapilacaklar>


}