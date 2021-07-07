package by.grsu.movieexplorer.data.network

import by.grsu.movieexplorer.util.Constants
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {
    single { RetrofitClient.provideRetrofit(get()) }
    factory { RetrofitClient.provideOkHttpClient() }
    factory { RetrofitClient.provideMovieService(get()) }
}

class RetrofitClient {
    companion object {
        fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
            return Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build()
        }

        fun provideOkHttpClient(): OkHttpClient {
            return OkHttpClient().newBuilder()
                .addInterceptor {
                    var request = it.request()
                    val url = request.url().newBuilder()
                        .addQueryParameter(Constants.API_KEY, Constants.API_KEY_VALUE)
                        .build()
                    request = request.newBuilder().url(url).build()
                    return@addInterceptor it.proceed(request)
                }
                .build()
        }

        fun provideMovieService(retrofit: Retrofit): MovieService =
            retrofit.create(MovieService::class.java)
    }
}