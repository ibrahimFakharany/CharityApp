package app.fakharany.com.charityapp.Factory

import app.fakharany.com.charityapp.POJO.RootObj
import app.fakharany.com.charityapp.Realm.Charities
import java.util.*
import java.util.concurrent.ThreadLocalRandom

object AndroidDataFactory {

    fun randomString(): String {

        return UUID.randomUUID().toString()

    }


    fun randomInt(): Int {
        return ThreadLocalRandom.current().nextInt(0, 1000 + 1)
    }


    fun getBoolean(): Boolean {

        return Math.random() > .5

    }

    fun createRootObj(count: Int): RootObj {
        var charities = mutableListOf<Charities>()
        var charity = Charities(randomInt(), randomString(), randomString(), randomString(), randomString())
        repeat(count) {
            charities.add(charity)
        }
        return RootObj(charities)
    }

    fun randomDouble() : Double{
        return randomInt().toDouble()
    }


}