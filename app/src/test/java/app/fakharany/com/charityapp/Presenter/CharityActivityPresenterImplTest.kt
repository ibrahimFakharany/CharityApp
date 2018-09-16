package app.fakharany.com.charityapp.Presenter


import app.fakharany.com.charityapp.Factory.DataFactory
import app.fakharany.com.charityapp.Model.CharityActivityModels.Repository
import app.fakharany.com.charityapp.POJO.RootObj
import app.fakharany.com.charityapp.Presenter.Executer.PostExecutionThread
import app.fakharany.com.charityapp.View.CharityActivityView
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Observable
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito


class CharityActivityPresenterImplTest {
    var charityActivityView = Mockito.mock(CharityActivityView::class.java)
    var model = Mockito.mock(Repository::class.java)
    var charityActivityPresenter = CharityActivityPresenterImpl(charityActivityView, model)

    @Before
    fun setup() {
        RxAndroidPlugins.setInitMainThreadSchedulerHandler({ Schedulers.trampoline() })
    }

    @Test
    fun testGetCharitiesPresenter() {

        var data = DataFactory.createRootObj(5)
        stubbingModelGetCharitiesMethod(data)
        var observer = charityActivityPresenter.getCharities().test()
        observer.assertComplete()
    }
/*
    @Test
    fun testIsShowFirstCharityIsCalledOnTheView() {
        var data = DataFactory.createRootObj(5)
        stubbingModelGetCharitiesMethod(data)
        charityActivityPresenter.onActivityCreated()
        verify(charityActivityView).showFirstCharity(data.charities[0])
    }*/

    fun stubbingModelGetCharitiesMethod(charities: RootObj) {
        whenever(model.getCharities()).thenReturn(Observable.just(charities))
    }
}