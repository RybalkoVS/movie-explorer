package by.grsu.movieexplorer.retrofit

import by.grsu.movieexplorer.movies.service.MovieDetailService
import by.grsu.movieexplorer.movies.service.MovieService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {
    single { RetrofitClient.provideRetrofit(get()) }
    single { RetrofitClient.provideOkHttpClient() }
    single { RetrofitClient.provideMovieService(get()) }
    single { RetrofitClient.provideMovieDetailService(get()) }
}

class RetrofitClient {
    companion object {
        fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
            return Retrofit.Builder()
                .baseUrl(ApiConstants.BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build()
        }

        fun provideOkHttpClient(): OkHttpClient {
            return OkHttpClient().newBuilder()
                .addInterceptor {
                    var request = it.request()
                    val url = request.url.newBuilder()
                        .addQueryParameter(ApiConstants.API_KEY, ApiConstants.API_KEY_VALUE)
                        .build()
                    request = request.newBuilder().url(url).build()
                    return@addInterceptor it.proceed(request)
                }
                .addInterceptor(
                    HttpLoggingInterceptor().apply {
                        setLevel(HttpLoggingInterceptor.Level.BASIC)
                    }
                )
                .build()
        }

        fun provideMovieService(retrofit: Retrofit): MovieService =
            retrofit.create(MovieService::class.java)

        fun provideMovieDetailService(retrofit: Retrofit): MovieDetailService =
            retrofit.create(MovieDetailService::class.java)
    }
}