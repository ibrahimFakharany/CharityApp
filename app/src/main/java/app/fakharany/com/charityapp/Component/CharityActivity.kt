package app.fakharany.com.charityapp.Component

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.Toast
import app.fakharany.com.charityapp.Adapter.CharityAdapter
import app.fakharany.com.charityapp.Model.DataClasses.Constants
import app.fakharany.com.charityapp.Presenter.interfaces.CharityActivityPresenter
import app.fakharany.com.charityapp.R
import app.fakharany.com.charityapp.Realm.Charities
import app.fakharany.com.charityapp.View.CharityActivityView
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_charity.*
import javax.inject.Inject

open class CharityActivity : AppCompatActivity(), CharityActivityView, CharityAdapter.AdapterOnItemClickListener {
    override fun showFirstCharity(charity: Charities) {
        simpletext.text = charity.organization_name
    }

    var simpleIdlingResource: SimpleIdlingResource = SimpleIdlingResource()
    @Inject
    lateinit var presenterImpl: CharityActivityPresenter
    open var progress: ProgressDialog? = null
    lateinit var adapter: CharityAdapter

    fun getMySimpleIdlingResource(): SimpleIdlingResource {
        return simpleIdlingResource
    }

    override fun onErrorLoadingCharities() {
        progress?.dismiss()
        showToast(getString(R.string.failed_online_message))
    }

    fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }


    override fun onClick(charity: Charities) {
        presenterImpl?.onClickCharity(charity)
    }

    override fun startDetailActivity(charity: Charities) {
        val intent = Intent(this, DetailActivity::class.java)
        val bundle = Bundle()
        bundle.putParcelable(Constants.CHARITY_OBJ_KEY, charity)
        intent.putExtras(bundle)
        startActivity(intent)
    }

    override fun showCharityList(charities: List<Charities>) {
        adapter.addData(charities as ArrayList<Charities>)
        charity_list.layoutManager = LinearLayoutManager(this)
        charity_list.adapter = adapter
        simpleIdlingResource.setIdleState(true)
        Toast.makeText(this, "finished loading ", Toast.LENGTH_SHORT).show()
        progress?.dismiss()
        charity_list.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                var mLayoutManager = charity_list.layoutManager as LinearLayoutManager
                if (charity_list.layoutManager.getChildCount() + mLayoutManager.findFirstVisibleItemPosition() >= mLayoutManager.itemCount) {
                    showDialog()
                    presenterImpl?.getCharities()
                }

            }
        })


    }

    fun showDialog() {
        progress?.setTitle("Loading")
        progress?.setMessage("Wait while loading...")
        progress?.setCancelable(false)
        progress?.show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_charity)
        simpleIdlingResource.setIdleState(false)
        presenterImpl?.onActivityCreated()
        adapter = CharityAdapter(listener = this)

    }

    override fun onDestroy() {
        super.onDestroy()
        presenterImpl?.onDetach()
    }
}