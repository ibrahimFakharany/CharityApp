package app.fakharany.com.charityapp.Presenter.interfaces

import app.fakharany.com.charityapp.POJO.RootObj
import app.fakharany.com.charityapp.Realm.Charities
import io.reactivex.Observable

interface CharityActivityPresenter {
    fun onClickCharity(charity: Charities)
    fun showCharities(charities: List<Charities>)
    fun onActivityCreated()
    fun getCharities(): Observable<RootObj>
    fun onDetach()
}