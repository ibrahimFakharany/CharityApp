package app.fakharany.com.charityapp.DI.modules

import app.fakharany.com.charityapp.Component.CharityActivity
import app.fakharany.com.charityapp.DI.App
import app.fakharany.com.charityapp.Model.CharityActivityModels.RepositoryImpl
import app.fakharany.com.charityapp.Model.DataClasses.ApiClass
import app.fakharany.com.charityapp.Model.DataClasses.ApiInterface
import app.fakharany.com.charityapp.Model.DataClasses.LocalModelImpl
import app.fakharany.com.charityapp.Model.DataClasses.RemoteModelImpl
import app.fakharany.com.charityapp.Presenter.CharityActivityPresenterImpl
import app.fakharany.com.charityapp.Presenter.Executer.PostExecutionThread
import app.fakharany.com.charityapp.Presenter.Executer.PostExecutionThreadImpl
import app.fakharany.com.charityapp.Presenter.interfaces.CharityActivityPresenter
import app.fakharany.com.charityapp.View.CharityActivityView
import dagger.Module
import dagger.Provides


@Module
class CharityActivityModule {

    @Provides
    fun provideCharityActivityPresenter(view: CharityActivityView, model: RepositoryImpl): CharityActivityPresenter {
        return CharityActivityPresenterImpl(view, model)
    }

    @Provides
    fun providesCharityActivityRemoteModel(apiInterface: ApiInterface): RemoteModelImpl = RemoteModelImpl(apiInterface)

    @Provides
    fun providesCharityActivityLocalModel(): LocalModelImpl = LocalModelImpl()

    @Provides
    fun providesCharityActivityRepository(remoteModelImpl: RemoteModelImpl, localModelImpl: LocalModelImpl): RepositoryImpl = RepositoryImpl(remoteModelImpl, localModelImpl)

    @Provides
    fun providesCharityActivityView(charityActivity: CharityActivity): CharityActivityView = charityActivity

    @Provides
    fun providesRetrofit(): ApiInterface {
        return ApiClass.getApiClassInstance().getRetro()!!.create(ApiInterface::class.java)
    }

    @Provides
    fun providePostExecutionThread(): PostExecutionThread {
        return PostExecutionThreadImpl()
    }

    @Provides
    fun provideApplication(activity: CharityActivity): App {
        return activity.application as App
    }
}