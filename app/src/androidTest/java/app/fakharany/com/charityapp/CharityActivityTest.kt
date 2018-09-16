package app.fakharany.com.charityapp

import android.app.Activity
import android.support.test.InstrumentationRegistry
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.*
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.espresso.matcher.ViewMatchers.withText
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import app.fakharany.com.charityapp.Adapter.CharityAdapter
import app.fakharany.com.charityapp.Component.CharityActivity
import app.fakharany.com.charityapp.DI.App
import app.fakharany.com.charityapp.Presenter.interfaces.CharityActivityPresenter
import dagger.android.AndroidInjector
import dagger.android.AndroidInjector.Factory
import dagger.android.DispatchingAndroidInjector
import dagger.android.DispatchingAndroidInjector_Factory
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import javax.inject.Provider

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class CharityActivityTest {
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

    @Rule
    @JvmField
    open var oktthpTestRule: OkHttpIdlingResourceRule = OkHttpIdlingResourceRule()

    @Test
    fun useAppContext() {
        onView(withId(R.id.charity_list)).perform(RecyclerViewActions.scrollToPosition<CharityAdapter.MyViewHolder>(0))
                .perform(click())
        onView(withId(R.id.charity_donation)).perform(typeText("25"), closeSoftKeyboard())
        onView(withId((R.id.doneIcon))).perform(click())
        onView(withId(R.id.charity_list)).perform(RecyclerViewActions.scrollToPosition<CharityAdapter.MyViewHolder>(0))
                .perform(click())
        onView(withId(R.id.textLastDonation)).check(matches(withText("25.0")))
    }

    @Test
    fun testCharityNameIsDisplayed() {
        onView(withId(R.id.simpletext))
                .check(matches(withText("Abu Rish Hospital")))
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
        val map = mapOf(Pair<Class<out Activity>, Provider<Factory<out Activity>>>(T::class.java, Provider { factory }))
        return DispatchingAndroidInjector_Factory.newDispatchingAndroidInjector(map)
    }
}