package app.fakharany.com.charityapp.Model.DataClasses

import app.fakharany.com.charityapp.Model.DataClasses.LayerInterfaces.LocalModel
import app.fakharany.com.charityapp.POJO.RootObj
import com.vicpin.krealmextensions.queryAll
import io.reactivex.Observable

open class LocalModelImpl : LocalModel {
    override fun getLocalCharities(): Observable<RootObj> {
        return Observable.fromCallable { RootObj(queryAll()) }
    }
}