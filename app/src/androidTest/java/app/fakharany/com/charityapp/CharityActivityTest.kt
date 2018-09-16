package app.fakharany.com.charityapp

import android.app.Activity
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.IdlingRegistry
import android.support.test.espresso.UiController
import android.support.test.espresso.ViewAction
import android.support.test.espresso.action.ViewActions.*
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.espresso.web.internal.deps.guava.collect.Iterables
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.support.test.runner.lifecycle.ActivityLifecycleMonitorRegistry
import android.support.test.runner.lifecycle.Stage
import android.view.View
import app.fakharany.com.charityapp.Adapter.CharityAdapter
import app.fakharany.com.charityapp.Component.CharityActivity
import app.fakharany.com.charityapp.Component.SimpleIdlingResource
import app.fakharany.com.charityapp.Presenter.interfaces.CharityActivityPresenter
import dagger.android.AndroidInjector
import dagger.android.AndroidInjector.Factory
import dagger.android.DispatchingAndroidInjector
import dagger.android.DispatchingAndroidInjector_Factory
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import org.junit.After
import org.junit.Before
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
    lateinit var simpleIdlingResource: SimpleIdlingResource

    @get:Rule
    val activityRule = ActivityTestRule<CharityActivity>(CharityActivity::class.java)

    @Before
    fun setup() {
        simpleIdlingResource = activityRule.activity.getMySimpleIdlingResource()
        IdlingRegistry.getInstance().register(simpleIdlingResource)
    }

    @After
    fun downTear() {
        IdlingRegistry.getInstance().unregister(simpleIdlingResource)
    }

    @Rule
    @JvmField
    open var oktthpTestRule: OkHttpIdlingResourceRule = OkHttpIdlingResourceRule()

    @Test
    fun useAppContext() {
        onView(withId(R.id.charity_list)).perform(RecyclerViewActions.scrollToPosition<CharityAdapter.MyViewHolder>(5))
                .perform(click())
        onView(withId(R.id.charity_donation)).perform(typeText("25"), closeSoftKeyboard())
        onView(withId((R.id.doneIcon))).perform(click())

        var activity = getActivity()
        activity?.finish()

        onView(withId(R.id.charity_list)).perform(RecyclerViewActions.scrollToPosition<CharityAdapter.MyViewHolder>(5))
                .perform(click())
        onView(withId(R.id.textLastDonation)).check(matches(withText("25.0")))
    }

    /*@Test
    fun testCharityNameIsDisplayed() {
        onView(withId(R.id.simpletext))
                .check(matches(withText("Abu Rish Hospital")))
    }*/


    fun getActivity(): Activity? {
        val currentActivity = arrayOfNulls<Activity>(1)
        onView(allOf(withId(android.R.id.content), isDisplayed())).perform(object : ViewAction {
            override fun getConstraints(): Matcher<View> {
                return isAssignableFrom(View::class.java)
            }

            override fun getDescription(): String {
                return "getting text from a TextView"
            }

            override fun perform(uiController: UiController, view: View) {
                if (view.getContext() is Activity) {
                    val activity1 = view.getContext() as Activity
                    currentActivity[0] = activity1
                }
            }
        })
        return currentActivity[0]
    }
}