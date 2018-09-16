package app.fakharany.com.charityapp

import android.content.Intent
import android.os.Bundle
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.IdlingRegistry
import android.support.test.espresso.action.ViewActions
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.espresso.matcher.ViewMatchers.withText
import android.support.test.rule.ActivityTestRule
import app.fakharany.com.charityapp.Component.DetailActivity
import app.fakharany.com.charityapp.Component.SimpleIdlingResource
import app.fakharany.com.charityapp.Factory.AndroidDataFactory
import app.fakharany.com.charityapp.Model.DataClasses.Constants
import app.fakharany.com.charityapp.Realm.Charities
import app.fakharany.com.charityapp.Realm.Donation
import com.vicpin.krealmextensions.saveManaged
import io.realm.Realm
import org.junit.Before
import org.junit.Test


class TestDetailActivity {
    var activityRule = ActivityTestRule<DetailActivity>(DetailActivity::class.java, true, false)
    lateinit var id: Integer
    lateinit var organizationName: String
    lateinit var organizationPic: String
    lateinit var organizationType: String
    lateinit var organizationDesc: String
    lateinit var donationAmount: Integer
    lateinit var simpleIdlingResource: SimpleIdlingResource
    @Before
    fun setup() {
        var realm = Realm.getInstance(Realm.getDefaultConfiguration())

        id = AndroidDataFactory.randomInt() as Integer
        donationAmount = AndroidDataFactory.randomInt() as Integer
        // this is last donation
        Donation(id.toInt(), donationAmount.toDouble()).saveManaged(realm)


        organizationName = AndroidDataFactory.randomString()
        var organizationPic = AndroidDataFactory.randomString()
        var organizationType = AndroidDataFactory.randomString()
        var organizationDesc = AndroidDataFactory.randomString()
        var charity = Charities(id.toInt(), organizationName, organizationPic, organizationType, organizationDesc)

        var intent = Intent()
        var bundle = Bundle()
        bundle.putParcelable(Constants.Companion.CHARITY_OBJ_KEY, charity)
        intent.putExtras(bundle)
        activityRule.launchActivity(intent)
        simpleIdlingResource = activityRule.activity.getMySimpleIdlingResource()

        IdlingRegistry.getInstance().register(simpleIdlingResource)
    }

    @Test
    fun testShowIntentValues() {

        onView(withId(R.id.charityName)).check(matches(withText(organizationName)))
        onView(withId(R.id.charityType)).check(matches(withText(organizationType)))
        onView(withId(R.id.charityDesc)).check(matches(withText(organizationDesc)))

    }


    @Test
    fun testDonationShown() {
        var data = "29"
        var dataDouble = "29.0"
        onView(withId(R.id.charity_donation)).perform(ViewActions.typeText(data), ViewActions.closeSoftKeyboard())
        onView(withId(R.id.doneIcon)).perform(ViewActions.click())
        onView(withId(R.id.textLastDonation)).check(matches(withText(dataDouble)))

    }

    @Test
    fun showsLastDonation() {
        onView(withId(R.id.textLastDonation)).check(matches(withText(donationAmount.toDouble().toString())))
    }
}