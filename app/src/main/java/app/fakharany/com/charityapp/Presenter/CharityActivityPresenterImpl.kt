package app.fakharany.com.charityapp.Presenter

import app.fakharany.com.charityapp.Model.CharityActivityModels.Repository
import app.fakharany.com.charityapp.POJO.RootObj
import app.fakharany.com.charityapp.Presenter.Executer.PostExecutionThread
import app.fakharany.com.charityapp.Presenter.interfaces.CharityActivityPresenter
import app.fakharany.com.charityapp.Realm.Charities
import app.fakharany.com.charityapp.View.CharityActivityView
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy


open class CharityActivityPresenterImpl
constructor(var mView: CharityActivityView?, var model: Repository, val postExecutionThread: PostExecutionThread) : CharityActivityPresenter {

    override fun onClickCharity(charity: Charities) {
        mView!!.startDetailActivity(charity)
    }

    override fun showCharities(charities: List<Charities>) {
        mView!!.showCharityList(charities)
    }

    override fun onActivityCreated() {
        var observable = getCharities()

        observable.observeOn(AndroidSchedulers.mainThread()).subscribeBy(
                onError =
                {
                    it.printStackTrace()
                    mView?.onErrorLoadingCharities()
                },

                onNext =
                {
                    showCharities(it.charities)
                }
        )

        /*observable.observeOn(AndroidSchedulers.mainThread()).subscribeBy(

                onError = {
                    it.printStackTrace()
                    mView?.onErrorLoadingCharities()
                },

                onNext = {
                    var charity = it.charities[0]
                    mView?.showFirstCharity(charity)
                }
        )*/
    }

    override fun getCharities(): Observable<RootObj> {
        return model.getCharities()
    }

    override fun onDetach() {
        mView = null
    }

}