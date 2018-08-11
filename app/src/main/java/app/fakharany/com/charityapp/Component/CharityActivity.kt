package app.fakharany.com.charityapp.Component

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import app.fakharany.com.charityapp.Adapter.CharityAdapter
import app.fakharany.com.charityapp.Model.CharityActivityModel
import app.fakharany.com.charityapp.Presenter.CharityActivityPresenter
import app.fakharany.com.charityapp.R
import app.fakharany.com.charityapp.View.CharityActivityView
import kotlinx.android.synthetic.main.activity_charity.*

class CharityActivity : AppCompatActivity(), CharityActivityView {
    override fun startDetailActivity(intent: Intent) {
        startActivity(intent)
    }

    var presenter = CharityActivityPresenter(this, CharityActivityModel(), this)

    override fun showMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun showCharityList(adapter: CharityAdapter) {
        charity_list.layoutManager = LinearLayoutManager(this)
        charity_list.adapter = adapter
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_charity)
        presenter.getCharities()
    }
}
