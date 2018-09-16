package app.fakharany.com.charityapp.Model.CharityActivityModels

import app.fakharany.com.charityapp.Model.DataClasses.LayerInterfaces.LocalModel
import app.fakharany.com.charityapp.Model.DataClasses.LayerInterfaces.RemoteModel
import app.fakharany.com.charityapp.POJO.RootObj
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers

open class RepositoryImpl(var remoteModel: RemoteModel, var localModel: LocalModel) : Repository {

    override fun getCharities(): Observable<RootObj> {
        var mergedObservable = Observable
                .mergeDelayError(remoteModel
                        .getCharitiesByRetrofit()
                        .doOnNext({ remoteModel.saveToDataBase(it) }).subscribeOn(Schedulers.io()),
                        localModel.getLocalCharities())
        return mergedObservable
    }

}

