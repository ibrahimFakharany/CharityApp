package app.fakharany.com.charityapp.Realm

import android.os.Parcelable
import io.realm.RealmObject
import kotlinx.android.parcel.Parcelize

@Parcelize
open class Charities(var id: Int = 0,
                     var organization_name: String? = null,
                     var organization_pic: String? = null,
                     var organization_type: String? = null,
                     var organization_desc: String? = null) : RealmObject(), Parcelable