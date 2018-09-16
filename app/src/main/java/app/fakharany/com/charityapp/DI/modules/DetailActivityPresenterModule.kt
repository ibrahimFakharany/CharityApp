package app.fakharany.com.charityapp.DI.modules

import android.os.Bundle
import dagger.Module
import dagger.Provides

@Module
class DetailActivityPresenterModule {

    @Provides
    fun providesBundle(): Bundle {
        return Bundle()
    }

}