package app.fakharany.com.charityapp.Presenter

import android.content.Context
import android.content.Intent
import android.os.Bundle
import app.fakharany.com.charityapp.Model.DetailActivityModel
import app.fakharany.com.charityapp.R
import app.fakharany.com.charityapp.View.DetailActivityView

class DetailActivityPresenter(var mView: DetailActivityView, var model: DetailActivityModel, var context: Context)
    : DetailActivityModel.DetailActivityModelListener {
    override fun onLastDonationNotFound() {
        mView.showMessage(context.getString(R.string.last_donation_not_found))
    }

    override fun onFinishedSaveDonation() {
        mView.showMessage(context.getString(R.string.saved))
    }

    override fun onRecieveLastDonation(amount: Double?) {
        mView.showMessage("" + amount)
        mView.showLastDonation(amount.toString())
    }

    var ORGANIZATION_ID = "organization_id"
    var DONATION_AMOUNT = "donationAmount"
    var extras: Bundle? = null

    fun handleEditTextEmpty() {

        mView.showMessage(context.getString(R.string.edit_text_empty_error))

    }

    override fun onFailed() {

    }

    override fun onSuccess() {

    }

    fun donate(amount: Double) {

        var bundle = Bundle()
        bundle.putInt(ORGANIZATION_ID, extras!!.getInt("id"))
        bundle.putDouble(DONATION_AMOUNT, amount)

        model.addDonate(bundle, this)
    }

    fun onCreate(intent: Intent) {

        this.extras = intent.extras

    }

    fun onResume() {
        // GET LAST DONATION WHEN RESUME THE ACTIVITY
        var id = extras!!.getInt("id")
        model.getLastDonation(id, this)

    }
}