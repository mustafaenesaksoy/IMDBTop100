package com.enesaksoy.imdbtop100.api

import com.enesaksoy.imdbtop100.util.API_HOST
import com.enesaksoy.imdbtop100.util.API_KEY
import com.enesaksoy.imdbtop100.util.BASE_URL
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers

interface RetrofitApi {

    @GET(BASE_URL)
    suspend fun getMovieList(
        @Header("X-RapidAPI-Key") apiKey : String = API_KEY,
        @Header("X-RapidAPI-Host") apiHost : String = API_HOST
    ) : Response<List<com.enesaksoy.imdbtop100.response.Result>>
}