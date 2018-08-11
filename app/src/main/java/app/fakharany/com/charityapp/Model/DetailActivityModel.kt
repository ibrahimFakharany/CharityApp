package app.fakharany.com.charityapp.Model

import android.os.Bundle
import app.fakharany.com.charityapp.Realm.Donation
import com.vicpin.krealmextensions.queryFirst
import com.vicpin.krealmextensions.saveManaged
import io.realm.Realm

class DetailActivityModel {
    var ORGANIZATION_ID = "organization_id"
    var DONATION_AMOUNT = "donationAmount"

    interface DetailActivityModelListener {

        fun onSuccess()
        fun onFailed()
        fun onRecieveLastDonation(amount: Double?)
        fun onLastDonationNotFound()
        fun onFinishedSaveDonation()

    }

    fun addDonate(bundle: Bundle, listener: DetailActivityModelListener) {

        var id = bundle.getInt(ORGANIZATION_ID)
        var amount = bundle.getDouble(DONATION_AMOUNT)
        var entered = false
        Donation(id, amount).saveManaged(Realm.getDefaultInstance()).addChangeListener({ donation: Donation ->
            listener.onRecieveLastDonation(donation.amount)
            entered = true
        })

        if (!entered)
            listener.onRecieveLastDonation(amount)

        listener.onFinishedSaveDonation()
    }


    fun getLastDonation(id: Int, listener: DetailActivityModelListener) {

        var lastDonationObject = queryFirst<Donation> { equalTo("id", id) }
        if (lastDonationObject != null)
            listener.onRecieveLastDonation(lastDonationObject!!.amount)


    }


}