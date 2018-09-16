package app.fakharany.com.charityapp.Model.CharityActivityModels

import app.fakharany.com.charityapp.POJO.RootObj
import io.reactivex.Observable

interface Repository {
    fun getCharities(): Observable<RootObj>
}