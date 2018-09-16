package app.fakharany.com.charityapp.Adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import app.fakharany.com.charityapp.R
import app.fakharany.com.charityapp.Realm.Charities
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_charity_list.view.*


class CharityAdapter(var charityList: ArrayList<Charities> = arrayListOf(), var listener: AdapterOnItemClickListener) : RecyclerView.Adapter<CharityAdapter.MyViewHolder>() {


    interface AdapterOnItemClickListener {

        fun onClick(charity: Charities)

    }

    override fun getItemCount(): Int {

        return charityList.size

    }


    fun addData(newList: ArrayList<Charities>) {
        if (charityList.isNotEmpty())
            charityList.clear()

        charityList.addAll(newList)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        var view: View = LayoutInflater.from(parent?.context)!!.inflate(R.layout.item_charity_list, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        var charityObj = charityList[position]

        holder?.charityNameTv?.text = charityObj.organization_name
        holder?.charityTypeTv?.text = charityObj.organization_type

        holder?.rootView?.setOnClickListener({ p0 ->
            listener.onClick(charityObj)
        })

        Picasso.get().load(charityObj.organization_pic).into(holder?.charityPicImg)
    }

    open class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var charityNameTv: TextView? = null
        var charityTypeTv: TextView? = null
        var charityPicImg: ImageView? = null
        var rootView: RelativeLayout? = null

        init {
            charityNameTv = itemView.name_charity
            charityTypeTv = itemView.type_charity
            charityPicImg = itemView.img_charity
            rootView = itemView.relative_layout
        }
    }

}