package app.fakharany.com.charityapp.DI

import okhttp3.OkHttpClient

open abstract class OkHttp {
    companion object {

        var instance: OkHttpClient? = null
        fun getClientInstance(): OkHttpClient {
            if (instance == null) {

                instance = OkHttpClient()
            }
            return instance!!
        }
    }
}