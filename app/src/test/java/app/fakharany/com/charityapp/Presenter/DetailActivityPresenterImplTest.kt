package app.fakharany.com.charityapp.Presenter

import android.os.Bundle
import app.fakharany.com.charityapp.Factory.DataFactory
import app.fakharany.com.charityapp.Model.DetailActivityModels.AddDontionRepository
import app.fakharany.com.charityapp.Realm.Donation
import app.fakharany.com.charityapp.View.DetailActivityView
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Observable
import org.junit.Test
import org.mockito.Mockito

class DetailActivityPresenterImplTest {

    val detailActivityView = Mockito.mock(DetailActivityView::class.java)
    val detailActivityModel = Mockito.mock(AddDontionRepository::class.java)
    var detailPresenter = DetailActivityPresenterImpl(detailActivityView, detailActivityModel)

    @Test
    fun testDonationDone() {


        var data = DataFactory.randomDouble()
        stubbingAddDonate(Bundle(), DataFactory.randomInt(), DataFactory.randomDouble())
        var observer = detailPresenter.donate(data).test()
        observer.assertComplete()
    }


    fun stubbingAddDonate(bundle: Bundle, id: Int, amount: Double) {
        whenever(detailActivityModel.addDonate(bundle)).thenReturn(Observable.just(Donation(id, amount)))
    }

}