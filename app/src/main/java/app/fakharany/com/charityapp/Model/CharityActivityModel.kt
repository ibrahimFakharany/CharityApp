package app.fakharany.com.charityapp.Model

import android.content.Context
import app.fakharany.com.charityapp.POJO.Charity
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject

class CharityActivityModel {
    var CHARITY_ARR = "charities"
    var ORGANIZATION_NAME = "organization_name"
    var ORGANIZATION_ID = "id"
    var ORGANIZATION_PIC = "organization_pic"
    var ORGANIZATION_TYPE = "organization_type"
    var ORGANIZATION_DESC = "organization_desc"


    interface CharityActivityModelListener {
        fun onSuccess(charityList: ArrayList<Charity>)
        fun onFailed()
    }


    fun getCharities(listener: CharityActivityModelListener, context: Context) {

        var request = StringRequest(Request.Method.GET, "https://demo8044805.mockable.io/7arka_get_charities", { result ->

            var list: ArrayList<Charity> = parseJson(result)
            listener.onSuccess(list)


        }, { error ->

            listener.onFailed()
        })

        var queue = Volley.newRequestQueue(context)
        queue.add(request)


    }

    private fun parseJson(result: String?): ArrayList<Charity> {
        var root = JSONObject(result)
        var resultList = ArrayList<Charity>()

        var charitiesArr = root.getJSONArray(CHARITY_ARR)

        for (i in 0 until charitiesArr.length()) {

            var charityObj = charitiesArr.getJSONObject(i)
            resultList.add(Charity(charityObj.getInt(ORGANIZATION_ID),
                    charityObj.getString(ORGANIZATION_NAME),
                    charityObj.getString(ORGANIZATION_PIC),
                    charityObj.getString(ORGANIZATION_TYPE),
                    charityObj.getString(ORGANIZATION_DESC))
            )

        }

        return resultList
    }
}