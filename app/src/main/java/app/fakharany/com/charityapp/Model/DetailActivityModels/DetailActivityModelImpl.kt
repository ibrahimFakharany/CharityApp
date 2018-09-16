package app.fakharany.com.charityapp.Model.DetailActivityModels

import android.os.Bundle
import app.fakharany.com.charityapp.Realm.Donation
import com.vicpin.krealmextensions.queryFirst
import com.vicpin.krealmextensions.saveManaged
import io.reactivex.Observable
import io.realm.Realm

class DetailActivityModelImpl : AddDontionRepository {

    var ORGANIZATION_ID = "organization_id"
    var DONATION_AMOUNT = "donationAmount"

    override fun getLastDonation(id: Int): Observable<Donation> {
        var thisId = id
        var lastDonation = queryFirst<Donation> { equalTo("id", thisId) }
        if (lastDonation == null) {
            return Observable.just(Donation(-1, -1.0))
        } else
            return Observable.just(lastDonation)
    }
    override fun addDonate(bundle: Bundle): Observable<Donation> {
        if (bundle == null)
            throw IllegalArgumentException("this bundle cannot be null")
        var id = bundle.getInt(ORGANIZATION_ID)
        var amount = bundle.getDouble(DONATION_AMOUNT)
        var donation = Donation(id, amount).saveManaged(Realm.getDefaultInstance())
        return Observable.just(donation)
    }

}