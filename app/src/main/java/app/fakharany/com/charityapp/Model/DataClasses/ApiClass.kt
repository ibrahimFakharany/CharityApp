package app.fakharany.com.charityapp.Model.DataClasses

import app.fakharany.com.charityapp.DI.OkHttp
import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


class ApiClass {

    companion object {
        var instance: ApiClass? = null
        fun getApiClassInstance(): ApiClass {
            if (instance == null)
                instance = ApiClass()
            return instance!!
        }
    }

    var retrofit: Retrofit? = null

    fun getRetro(): Retrofit? {
        if (retrofit == null) {

            val gson = GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create()
            retrofit = Retrofit.Builder()
                    .baseUrl(Constants.BASE_URL)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .client(OkHttp.getClientInstance())
                    .build()
        }
        return retrofit
    }

    fun getOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder().connectTimeout(1000, TimeUnit.MILLISECONDS)
                .readTimeout(1000, TimeUnit.MILLISECONDS).build()
    }


}