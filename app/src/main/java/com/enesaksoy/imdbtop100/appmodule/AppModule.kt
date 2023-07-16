package com.enesaksoy.imdbtop100.appmodule

import android.content.Context
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.enesaksoy.imdbtop100.R
import com.enesaksoy.imdbtop100.api.RetrofitApi
import com.enesaksoy.imdbtop100.repo.IMDBRepository
import com.enesaksoy.imdbtop100.repo.IMDBRepositoryImpl
import com.enesaksoy.imdbtop100.util.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun injectRetrofit() : RetrofitApi{
       return Retrofit.Builder()
           .baseUrl(BASE_URL)
           .addConverterFactory(GsonConverterFactory.create())
           .build()
           .create(RetrofitApi::class.java)
    }

    @Provides
    @Singleton
    fun injectRepo(retrofitApi: RetrofitApi) : IMDBRepository{
        return IMDBRepositoryImpl(retrofitApi)
    }

    @Provides
    @Singleton
    fun injectGlide(@ApplicationContext context : Context) = Glide.with(context)
        .setDefaultRequestOptions(
            RequestOptions().placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)
        )
}