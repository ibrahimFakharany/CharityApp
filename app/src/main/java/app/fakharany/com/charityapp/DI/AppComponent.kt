package app.fakharany.com.charityapp.DI

import app.fakharany.com.charityapp.DI.modules.ApiClassModule
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule


@Component(modules = [
    AndroidSupportInjectionModule::class,
    ActivityBuilder::class,
    ApiClassModule::class
])
interface AppComponent : AndroidInjector<App>