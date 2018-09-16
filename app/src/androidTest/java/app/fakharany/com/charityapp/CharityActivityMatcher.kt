package app.fakharany.com.charityapp

import android.text.TextUtils
import android.view.View
import android.widget.RelativeLayout
import android.widget.TextView
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeMatcher


class CharityActivityMatcher {

    companion object {

        open fun withCharityViewName(expected: String): Matcher<View> {
            return object : TypeSafeMatcher<View>() {
                override fun matchesSafely(item: View?): Boolean {
                    if (item == null) {

                        val taskName = item?.findViewById(R.id.relative_layout) as TextView
                        return if (TextUtils.isEmpty(taskName.text)) {
                            false
                        } else {

                            taskName.text == expected

                        }

                    } else return false

                }

                override fun describeTo(description: Description?) {
                    description?.appendText("look for " + expected + " in the file item_charity_list")
                }
            }


        }
    }
}