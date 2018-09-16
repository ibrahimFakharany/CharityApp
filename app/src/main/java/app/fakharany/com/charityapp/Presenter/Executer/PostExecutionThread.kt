package app.fakharany.com.charityapp.Presenter.Executer

import io.reactivex.Scheduler

interface PostExecutionThread {

    val schedulers: Scheduler
}