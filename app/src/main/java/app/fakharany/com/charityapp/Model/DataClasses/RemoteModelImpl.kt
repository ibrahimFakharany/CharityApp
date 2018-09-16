package app.fakharany.com.charityapp.Model.DataClasses

import app.fakharany.com.charityapp.Model.DataClasses.LayerInterfaces.RemoteModel
import app.fakharany.com.charityapp.POJO.RootObj
import app.fakharany.com.charityapp.Realm.Charities
import com.vicpin.krealmextensions.save
import io.reactivex.Observable

open class RemoteModelImpl (val apiInterface: ApiInterface) : RemoteModel {
    override fun getCharitiesByRetrofit(): Observable<RootObj> {
        return apiInterface.getCategories()
    }

    override fun saveToDataBase(body: RootObj?) {
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