package com.example.apphuertoencasa.Network

import com.example.apphuertoencasa.ui.theme.model.CategoryModels
import com.example.apphuertoencasa.ui.theme.model.FrutasModels
import com.example.apphuertoencasa.ui.theme.model.VerdurasModels
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET


interface TheMealDbApiService {

    @GET("85cfd9a0047b0a0475e8e0ed2fb8ad70/raw/e7b88df13e67b4efd7b58779ec54e549a24128e1/categoria.json")
    suspend fun getCategories(): CategoryModels
    @GET("91011194660fd927264769f25fdad8e9/raw/32cbe5047e14327317df2000811feff5bb969c9d/frutas.json")
    suspend fun getFruits(): FrutasModels

    @GET("92c7512381488d458211d725001356aa/raw/c6f92c4b45aeddef98f18d77d6c21c21387f7333/verduras.json")
    suspend fun getVegetables(): VerdurasModels

}

object RetrofitInstance {
    private const val BASE_URL = "https://gist.githubusercontent.com/valbustosm-android/"

    val api: TheMealDbApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(TheMealDbApiService::class.java)
    }
}
