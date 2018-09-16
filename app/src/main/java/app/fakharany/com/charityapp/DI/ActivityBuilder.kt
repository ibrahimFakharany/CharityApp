package app.fakharany.com.charityapp.DI

import app.fakharany.com.charityapp.Component.CharityActivity
import app.fakharany.com.charityapp.Component.DetailActivity
import app.fakharany.com.charityapp.DI.modules.CharityActivityModule
import app.fakharany.com.charityapp.DI.modules.DetailActivityModule
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = [CharityActivityModule::class])
    abstract fun provideCharityActivity(): CharityActivity


    @ContributesAndroidInjector(modules = [DetailActivityModule::class])
    abstract fun provideDetailActivity(): DetailActivity
}