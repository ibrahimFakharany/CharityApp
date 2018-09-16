package app.fakharany.com.charityapp

import android.support.test.espresso.Espresso
import app.fakharany.com.charityapp.DI.OkHttp
import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement

open class OkHttpIdlingResourceRule : TestRule {
    override fun apply(base: Statement, description: Description?): Statement {
        return object : Statement() {
            override fun evaluate() {
                var idlingResourceRule = OkHttp3IdlingResource.create("okhttp", OkHttp.getClientInstance())
                Espresso.registerIdlingResources(idlingResourceRule)

                base.evaluate()
                Espresso.unregisterIdlingResources(idlingResourceRule)

            }


        }
    }
}