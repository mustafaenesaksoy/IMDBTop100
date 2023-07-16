package com.enesaksoy.imdbtop100.repo

import com.enesaksoy.imdbtop100.util.Resource

interface IMDBRepository {

    suspend fun getMovieList() : Resource<List<com.enesaksoy.imdbtop100.response.Result>>
}