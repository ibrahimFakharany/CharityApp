package app.fakharany.com.charityapp.DI.modules

import android.os.Bundle
import app.fakharany.com.charityapp.Component.DetailActivity
import app.fakharany.com.charityapp.Model.DetailActivityModels.DetailActivityModelImpl
import app.fakharany.com.charityapp.Presenter.DetailActivityPresenterImpl
import app.fakharany.com.charityapp.Presenter.interfaces.DetailActivityPresenter
import app.fakharany.com.charityapp.View.DetailActivityView
import dagger.Module
import dagger.Provides


@Module
class DetailActivityModule {

    @Provides
    fun provideDetailActivityPresenter(view: DetailActivityView, modelImpl: DetailActivityModelImpl): DetailActivityPresenter {
        return DetailActivityPresenterImpl(view, modelImpl)
    }

    @Provides
    fun provideDetailActivityModel(): DetailActivityModelImpl {
        return DetailActivityModelImpl()
    }

    @Provides
    fun provideDetailActivityView(detailACtivity: DetailActivity): DetailActivityView {
        return detailACtivity
    }

    @Provides
    fun providesBundle(): Bundle {
        return Bundle()
    }

}