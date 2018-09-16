package app.fakharany.com.charityapp.Model.DataClasses.LayerInterfaces

import app.fakharany.com.charityapp.POJO.RootObj
import io.reactivex.Observable

interface LocalModel {
    fun getLocalCharities(): Observable<RootObj>
}