package com.tantn.payexchange.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tantn.payexchange.R
import kotlinx.android.synthetic.main.item_list.view.*


class HomeRecyclerAdapter :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var exchangeRateList = ArrayList<Pair<String, Double>?>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_list, parent, false)
        return CurrencyRateViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        exchangeRateList[position]?.let { (holder as CurrencyRateViewHolder).bindItems(it) }
    }

    override fun getItemCount(): Int {
        return exchangeRateList.size
    }

    fun setExchangeData(newList: ArrayList<Pair<String, Double>?>?) {
        if (newList != null) {
            exchangeRateList.clear()
            exchangeRateList.addAll(newList)
            notifyDataSetChanged()
        }
    }

    class CurrencyRateViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindItems(pair: Pair<String, Double>) {
            itemView.countryNameTv.text = pair.first
            itemView.exchangeRateTv.text = pair.second.toString()
        }
    }
}