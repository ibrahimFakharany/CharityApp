package app.fakharany.com.charityapp.Component

import android.app.Application
import io.realm.Realm
import io.realm.RealmConfiguration

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        Realm.init(this)

        var configuration = RealmConfiguration.Builder().name("donatation_db")
                .schemaVersion(1).deleteRealmIfMigrationNeeded()
                .build()



    }
}