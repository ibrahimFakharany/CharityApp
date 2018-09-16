package app.fakharany.com.charityapp.DI

import app.fakharany.com.charityapp.DI.modules.DetailActivityPresenterModule
import app.fakharany.com.charityapp.Presenter.DetailActivityPresenterImpl
import dagger.Component


@Component(modules = [
    DetailActivityPresenterModule::class
])
interface DetailActivitPresenterComponent {
    fun inject(presenterImpl: DetailActivityPresenterImpl)
}