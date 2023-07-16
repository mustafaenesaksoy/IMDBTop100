package com.enesaksoy.imdbtop100.repo

import com.enesaksoy.imdbtop100.api.RetrofitApi
import com.enesaksoy.imdbtop100.response.Result
import com.enesaksoy.imdbtop100.util.Resource
import javax.inject.Inject

class IMDBRepositoryImpl@Inject constructor(private val retrofitApi: RetrofitApi) : IMDBRepository {
    override suspend fun getMovieList(): Resource<List<Result>> {
        return try {
            val response = retrofitApi.getMovieList()
            if(response.isSuccessful){
                response.body()?.let {
                    return@let Resource.success(it)
                }?: Resource.error("Error",null)
            }else{
                Resource.error("Error",null)
            }
        }catch (e : Exception){
            Resource.error("Error",null)
        }
    }
}