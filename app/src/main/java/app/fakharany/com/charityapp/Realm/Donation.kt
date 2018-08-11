package app.fakharany.com.charityapp.Realm

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey


open class Donation(
        @PrimaryKey
        var id: Int? = null,
        var amount: Double? = null) : RealmObject()