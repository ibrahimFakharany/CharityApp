package app.fakharany.com.charityapp.Model.DetailActivityModels

import android.os.Bundle
import app.fakharany.com.charityapp.Realm.Donation
import io.reactivex.Observable
import io.reactivex.Single

interface AddDontionRepository {


    fun addDonate(amount: Bundle): Observable<Donation>

    fun getLastDonation(id: Int): Observable<Donation>
}