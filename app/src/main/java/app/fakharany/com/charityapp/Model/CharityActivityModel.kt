package app.fakharany.com.charityapp.Model

import app.fakharany.com.charityapp.Model.DataClasses.ApiInterface
import app.fakharany.com.charityapp.POJO.RootObj
import app.fakharany.com.charityapp.Realm.Charities
import com.vicpin.krealmextensions.queryAll
import com.vicpin.krealmextensions.save
import io.reactivex.Observable

open class CharityActivityModel(var apiInterface: ApiInterface) {
    fun getCharitiesByRetrofit( ): Observable<RootObj> {
        return apiInterface!!.getCategories()
    }

    fun getLocalCharities(): RootObj {
        return RootObj(queryAll())
    }

    open fun saveToDataBase(body: RootObj?) {
        for (i in 0 until body!!.charities.size) {

            var charity = body.charities[i]
            Charities(charity.id,
                    charity.organization_name,
                    charity.organization_pic,
                    charity.organization_type,
                    charity.organization_desc).save()
        }
    }

}