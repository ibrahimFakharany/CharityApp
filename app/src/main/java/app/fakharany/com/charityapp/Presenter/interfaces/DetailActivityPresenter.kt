package app.fakharany.com.charityapp.Presenter.interfaces

import android.content.Intent
import app.fakharany.com.charityapp.Realm.Donation
import io.reactivex.Observable

interface DetailActivityPresenter {


    fun handleEditTextEmpty()
    fun donate(id: Int, amount: Double): Observable<Donation>
    fun onCreate(intent: Intent)
    fun onResume(id: Int): Observable<Donation>
    fun recieveDonationSuccessfully(amount: Double?)
}