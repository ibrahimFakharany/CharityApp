package app.fakharany.com.charityapp.Presenter

import android.content.Context
import android.content.Intent
import android.os.Bundle
import app.fakharany.com.charityapp.Adapter.CharityAdapter
import app.fakharany.com.charityapp.Component.DetailActivity
import app.fakharany.com.charityapp.Model.CharityActivityModel
import app.fakharany.com.charityapp.POJO.Charity
import app.fakharany.com.charityapp.R
import app.fakharany.com.charityapp.View.CharityActivityView

class CharityActivityPresenter(var mView: CharityActivityView?, var model: CharityActivityModel, var context: Context) :
        CharityActivityModel.CharityActivityModelListener, CharityAdapter.AdapterOnItemClickListener {
    var ORGANIZATION_NAME = "organization_name"
    var ORGANIZATION_ID = "id"
    var ORGANIZATION_PIC = "organization_pic"
    var ORGANIZATION_TYPE = "organization_type"
    var ORGANIZATION_DESC = "organization_desc"
    override fun onClick(charity: Charity) {

//        mView!!.showMessage(Integer.toString(charity.organizationId))

        // open detail activity
        var intent = Intent(context, DetailActivity::class.java)
        var bundle = Bundle()

        bundle.putString(ORGANIZATION_NAME, charity.organizationName)
        bundle.putString(ORGANIZATION_DESC, charity.organizationDesc)
        bundle.putString(ORGANIZATION_TYPE, charity.organizationType)
        bundle.putInt(ORGANIZATION_ID, charity.organizationId)
        bundle.putString(ORGANIZATION_PIC, charity.organizationPic)

        intent.putExtras(bundle)

        mView!!.startDetailActivity(intent)
    }

    // success getting the list of charities from service
    override fun onSuccess(charityList: ArrayList<Charity>) {
        var adapter = CharityAdapter(context, charityList, this)
        mView!!.showCharityList(adapter)
    }

    // success getting the list of charities from service
    override fun onFailed() {
        mView!!.showMessage(context.getString(R.string.failed_message))
    }

    fun getCharities() {
        model.getCharities(this, context)
    }

    fun onDetach() {
        mView = null
    }
}