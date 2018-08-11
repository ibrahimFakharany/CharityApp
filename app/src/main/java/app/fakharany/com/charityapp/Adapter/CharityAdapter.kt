package app.fakharany.com.charityapp.Adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import app.fakharany.com.charityapp.POJO.Charity
import app.fakharany.com.charityapp.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_charity_list.view.*

class CharityAdapter(context: Context, var charityList: ArrayList<Charity>?, var listener: AdapterOnItemClickListener) : RecyclerView.Adapter<CharityAdapter.MyViewHolder>() {

    interface AdapterOnItemClickListener {

        fun onClick(charity: Charity);

    }


    var inflater: LayoutInflater? = null

    init {
        inflater = LayoutInflater.from(context)
    }

    override fun getItemCount(): Int {
        return charityList!!.size
    }

    override fun onBindViewHolder(holder: MyViewHolder?, position: Int) {

        var charityObj = charityList!![position]

        holder!!.charityNameTv!!.text = charityObj.organizationName
        holder!!.charityTypeTv!!.text = charityObj.organizationType


        holder.rootView!!.setOnClickListener({ p0 ->
            listener.onClick(charityObj)
        })
        Picasso.get().load(charityObj.organizationPic).into(holder.charityPicImg)

    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): MyViewHolder {

        var view: View = inflater!!.inflate(R.layout.item_charity_list, parent, false)

        return MyViewHolder(view)
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
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