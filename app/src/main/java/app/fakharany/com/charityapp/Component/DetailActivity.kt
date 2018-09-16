package app.fakharany.com.charityapp.Component

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Toast
import app.fakharany.com.charityapp.Model.DataClasses.Constants
import app.fakharany.com.charityapp.Presenter.interfaces.DetailActivityPresenter
import app.fakharany.com.charityapp.R
import app.fakharany.com.charityapp.Realm.Charities
import app.fakharany.com.charityapp.View.DetailActivityView
import com.squareup.picasso.Picasso
import dagger.android.AndroidInjection
import io.reactivex.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.description_app_bar.*
import javax.inject.Inject

class DetailActivity : AppCompatActivity(), DetailActivityView, View.OnClickListener {
    @Inject
    lateinit var presenterImpl: DetailActivityPresenter
    var simpleIdlingResource = SimpleIdlingResource()
    override fun onLastDonationNotFound() {
        showToast(getString(R.string.last_donation_not_found))
    }

    override fun onFinishedSaveDonation() {
        showToast(getString(R.string.saved))
        finish()
    }

    override fun onRecieveLastDonation(amount: Double?) {
        showToast(amount.toString())
    }

    override fun onEditTextEmpty() {
        showToast(getString(R.string.edit_text_empty_error))
    }

    override fun showLastDonation(amount: String) {
        textLastDonation.text = amount
    }

    override fun onClick(p0: View?) {
        // on done Donatation icon click
        if (!charity_donation.text.toString().isEmpty()) {
            simpleIdlingResource.setIdleState(false)
            presenterImpl.donate(charity_donation.text.toString().toDouble()).subscribe {
                presenterImpl.recieveDonationSuccessfully(it.amount)
                simpleIdlingResource.setIdleState(true)
            }
        } else
            presenterImpl.handleEditTextEmpty()
    }

    fun showToast(message: String) {
        Toast.makeText(this, message + " this kjdklsafj ", Toast.LENGTH_SHORT).show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        var extras = intent.extras
        var charity: Charities = extras.getParcelable(Constants.CHARITY_OBJ_KEY)

        presenterImpl.onCreate(intent)
        Picasso.get()
                .load(charity.organization_pic)
                .into(charityImg)

        charityTitle.text = charity.organization_name
        charityName.text = charity.organization_name
        charityType.text = charity.organization_type
        charityDesc.text = charity.organization_desc
        id = charity.id as Integer
        doneIcon.setOnClickListener(this)
    }

    lateinit var id: Integer


    fun getMySimpleIdlingResource(): SimpleIdlingResource {
        return simpleIdlingResource
    }

    override fun onResume() {
        super.onResume()
        presenterImpl.onResume(id.toInt()).observeOn(AndroidSchedulers.mainThread()).subscribe {
            presenterImpl.recieveDonationSuccessfully(it.amount)
        }
    }
}
