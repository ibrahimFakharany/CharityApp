package app.fakharany.com.charityapp.DI

import android.app.Activity
import android.app.Application
import app.fakharany.com.charityapp.Model.DataClasses.Constants
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import io.realm.Realm
import io.realm.RealmConfiguration
import javax.inject.Inject

open class App : Application(), HasActivityInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    override fun activityInjector(): AndroidInjector<Activity> {
        return dispatchingAndroidInjector
    }

    open fun getBaseUrl(): String {
        return Constants.BASE_URL
    }

    override fun onCreate() {
        super.onCreate()
        DaggerAppComponent.create().inject(this)
        try {
            Realm.init(this)

            RealmConfiguration.Builder().name("donatation_db")
                    .schemaVersion(1)
                    .build()
        } catch (e: Exception) {

        }

    }
}