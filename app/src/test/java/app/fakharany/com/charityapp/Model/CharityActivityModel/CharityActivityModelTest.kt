package app.fakharany.com.charityapp.Model.CharityActivityModel

import app.fakharany.com.charityapp.Factory.DataFactory
import app.fakharany.com.charityapp.Model.CharityActivityModels.RepositoryImpl
import app.fakharany.com.charityapp.Model.DataClasses.LayerInterfaces.LocalModel
import app.fakharany.com.charityapp.Model.DataClasses.LayerInterfaces.RemoteModel
import app.fakharany.com.charityapp.POJO.RootObj
import io.reactivex.Observable
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers.any
import org.mockito.Mockito

class CharityActivityModelTest {
    val remoteModel = Mockito.mock(RemoteModel::class.java)
    val localModel = Mockito.mock(LocalModel::class.java)
    val charityActivityRepository = RepositoryImpl(remoteModel, localModel)
    @Before
    fun setup() {
        RxAndroidPlugins.setInitMainThreadSchedulerHandler({ Schedulers.trampoline() })
    }
    @Test
    fun testGetCharities() {
        var data = Observable.just(DataFactory.createRootObj(2))

        stubbingGetCharitiesOnRemoteModel(data)
        stubbingGetCharitiesLocalModel(data)
        stubbingSaveToDatabase()
        var observer = charityActivityRepository.getCharities().test()
        observer.assertComplete()
    }

    fun stubbingGetCharitiesOnRemoteModel(observable: Observable<RootObj>) {
        Mockito.`when`(remoteModel.getCharitiesByRetrofit()).thenReturn(observable)
    }

    fun stubbingGetCharitiesLocalModel(observable: Observable<RootObj>) {
        Mockito.`when`(localModel.getLocalCharities()).thenReturn(observable)
    }

    fun stubbingSaveToDatabase() {
        Mockito.doNothing().`when`(remoteModel).saveToDataBase(any())
    }

}