package app.fakharany.com.charityapp.Presenter.Executer

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Inject

class PostExecutionThreadImpl @Inject constructor():PostExecutionThread {
    override val schedulers: Scheduler
        get() = AndroidSchedulers.mainThread()
}