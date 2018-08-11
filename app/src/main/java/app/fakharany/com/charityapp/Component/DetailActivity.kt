package app.fakharany.com.charityapp.Component

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.Toast
import app.fakharany.com.charityapp.Model.DetailActivityModel
import app.fakharany.com.charityapp.Presenter.DetailActivityPresenter
import app.fakharany.com.charityapp.R
import app.fakharany.com.charityapp.View.DetailActivityView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.description_app_bar.*

class DetailActivity : AppCompatActivity(), DetailActivityView, View.OnClickListener {
    override fun showLastDonation(amount: String) {
        textLastDonation.text = amount
    }

    override fun onClick(p0: View?) {
        // on done Donatation icon click
        if(!charity_donation.text.toString().isEmpty())
        presenter.donate(charity_donation.text.toString().toDouble())
        else
            presenter.handleEditTextEmpty()
    }

    var presenter = DetailActivityPresenter(this, DetailActivityModel(), this)
    override fun showMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        Log.e("detailActivity", message)
    }

    var ORGANIZATION_NAME = "organization_name"
    var ORGANIZATION_ID = "id"
    var ORGANIZATION_PIC = "organization_pic"
    var ORGANIZATION_TYPE = "organization_type"
    var ORGANIZATION_DESC = "organization_desc"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        var extras = intent.extras
        presenter.onCreate(intent)
        Picasso.get()
                .load(extras.getString(ORGANIZATION_PIC))
                .into(charityImg)

        charityTitle.text = extras.getString(ORGANIZATION_NAME)
        charityName.text = extras.getString(ORGANIZATION_NAME)
        charityType.text = extras.getString(ORGANIZATION_TYPE)
        charityDesc.text = extras.getString(ORGANIZATION_DESC)
        var id = extras.getInt(ORGANIZATION_ID)

        doneIcon.setOnClickListener(this)

    }


    override fun onResume() {
        super.onResume()
        presenter.onResume()
    }
}
