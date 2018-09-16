package app.fakharany.com.charityapp.Presenter

import android.content.Intent
import android.os.Bundle
import app.fakharany.com.charityapp.DI.DaggerDetailActivitPresenterComponent
import app.fakharany.com.charityapp.Model.DetailActivityModels.AddDontionRepository
import app.fakharany.com.charityapp.Presenter.interfaces.DetailActivityPresenter
import app.fakharany.com.charityapp.Realm.Donation
import app.fakharany.com.charityapp.View.DetailActivityView
import io.reactivex.Observable
import javax.inject.Inject

class DetailActivityPresenterImpl(val mView: DetailActivityView, val modelImpl: AddDontionRepository)
    : DetailActivityPresenter {

    @Inject
    lateinit var bundle: Bundle

    init {
        DaggerDetailActivitPresenterComponent.create().inject(this)
    }


    var ORGANIZATION_ID = "organization_id"
    var DONATION_AMOUNT = "donationAmount"
    var extras: Bundle? = null

    override fun handleEditTextEmpty() {
        mView.onEditTextEmpty()
    }

    override fun donate(id: Int, amount: Double): Observable<Donation> {
        if (bundle != null) {
            bundle.putInt(ORGANIZATION_ID, id)
            bundle.putDouble(DONATION_AMOUNT, amount)

        }

        return modelImpl.addDonate(bundle)
    }

    override fun onCreate(intent: Intent) {
        this.extras = intent.extras
    }

    override fun onResume(id: Int): Observable<Donation> {
        // GET LAST DONATION WHEN RESUME THE ACTIVITY
        var thisId = id
        return modelImpl.getLastDonation(thisId)
    }

    override fun recieveDonationSuccessfully(amount: Double?) {
        if (amount != -1.0)
            mView.showLastDonation(amount.toString())
    }
}