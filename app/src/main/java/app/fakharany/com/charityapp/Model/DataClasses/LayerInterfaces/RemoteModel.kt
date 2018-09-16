package app.fakharany.com.charityapp.Model.DataClasses.LayerInterfaces

import app.fakharany.com.charityapp.POJO.RootObj
import io.reactivex.Observable

interface RemoteModel {
    fun getCharitiesByRetrofit(): Observable<RootObj>
    fun saveToDataBase(body: RootObj?)
}