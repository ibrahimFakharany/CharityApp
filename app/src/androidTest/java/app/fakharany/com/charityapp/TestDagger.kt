package app.fakharany.com.charityapp

import android.app.Activity
import android.support.test.InstrumentationRegistry
import android.support.test.rule.ActivityTestRule
import app.fakharany.com.charityapp.Component.CharityActivity
import app.fakharany.com.charityapp.Component.SimpleIdlingResource
import app.fakharany.com.charityapp.DI.App
import app.fakharany.com.charityapp.Presenter.interfaces.CharityActivityPresenter
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.DispatchingAndroidInjector_Factory
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito
import javax.inject.Provider

class TestDagger {

    val mockCharityPresenter = Mockito.mock(CharityActivityPresenter::class.java)
    @get:Rule
    val activityRule = object : ActivityTestRule<CharityActivity>(CharityActivity::class.java, true, true) {
        override fun beforeActivityLaunched() {
            super.beforeActivityLaunched()
            val myApp = InstrumentationRegistry.getTargetContext().applicationContext as App
            myApp.dispatchingAndroidInjector = createFakeActivityInjector<CharityActivity> {
                presenterImpl = mockCharityPresenter
            }
        }
    }

    @Test
    fun testOnActivityCreatedCalledOnPresenter() {
        var presenter = activityRule.activity.presenterImpl
        Mockito.verify(presenter).onActivityCreated()
    }

    inline fun <reified T : Activity> createFakeActivityInjector(crossinline block: T.() -> Unit)
            : DispatchingAndroidInjector<Activity> {
        val injector = AndroidInjector<Activity> { instance ->
            if (instance is T) {
                instance.block()
            }
        }
        val factory = AndroidInjector.Factory<Activity> { injector }
        val map = mapOf(Pair<Class<out Activity>, Provider<AndroidInjector.Factory<out Activity>>>(T::class.java, Provider { factory }))
        return DispatchingAndroidInjector_Factory.newDispatchingAndroidInjector(map)
    }

}