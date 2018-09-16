package app.fakharany.com.charityapp.DI.modules

import app.fakharany.com.charityapp.Model.DataClasses.Constants
import dagger.Module
import dagger.Provides

@Module
class ApiClassModule {
    @Provides
    fun provideBaseUrl(): String {
        return Constants.BASE_URL
    }
}