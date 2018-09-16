package app.fakharany.com.charityapp.Model.DataClasses

import app.fakharany.com.charityapp.POJO.RootObj
import io.reactivex.Observable
import retrofit2.http.GET

interface ApiInterface {
    @GET(Constants.CHARITIES)
    fun getCategories(): Observable<RootObj>
}