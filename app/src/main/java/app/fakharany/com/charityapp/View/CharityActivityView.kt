package app.fakharany.com.charityapp.View

import app.fakharany.com.charityapp.Realm.Charities

open interface CharityActivityView {
    fun onErrorLoadingCharities()
    fun showCharityList(charities: List<Charities>)
    fun startDetailActivity(charity: Charities)
    fun showFirstCharity(charity: Charities)

}