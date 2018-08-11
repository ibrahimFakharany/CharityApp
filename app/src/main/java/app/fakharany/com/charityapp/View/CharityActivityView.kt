package app.fakharany.com.charityapp.View

import android.content.Intent
import app.fakharany.com.charityapp.Adapter.CharityAdapter

open interface CharityActivityView : RootInterface {

    fun showCharityList(adapter: CharityAdapter)
    fun startDetailActivity(intent: Intent)

}