package by.grsu.movieexplorer.data.network

import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {
    single { RetrofitClient.provideRetrofit(get()) }
    factory { RetrofitClient.provideOkHttpClient() }
    factory { RetrofitClient.provideMovieService(get()) }
}

class RetrofitClient {
    companion object {
        fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
            return Retrofit.Builder().baseUrl(MovieService.BASE_URL).client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create()).build()
        }

        fun provideOkHttpClient(): OkHttpClient {
            return OkHttpClient().newBuilder().addInterceptor {
                var request = it.request()
                val url = request.url().newBuilder()
                    .addQueryParameter("api_key", "a629ba31fce2bc863e700cf8de0d882d")
                    .build()
                request = request.newBuilder().url(url).build()
                return@addInterceptor it.proceed(request)
            }.build()
        }

        fun provideMovieService(retrofit: Retrofit): MovieService =
            retrofit.create(MovieService::class.java)
    }
}