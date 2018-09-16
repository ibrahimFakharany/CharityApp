package app.fakharany.com.charityapp

import app.fakharany.com.charityapp.DI.App
import io.realm.Realm
import io.realm.RealmConfiguration


class TestApp : App() {
    var url: String? = null
    override fun getBaseUrl(): String {
        return url!!
    }

    override fun onCreate() {
        super.onCreate()
        val testConfig = RealmConfiguration.Builder().inMemory().name("test-realm").build()
        Realm.setDefaultConfiguration(testConfig)
    }

    fun setBaseUrl(url: String) {
        this.url = url
    }
}