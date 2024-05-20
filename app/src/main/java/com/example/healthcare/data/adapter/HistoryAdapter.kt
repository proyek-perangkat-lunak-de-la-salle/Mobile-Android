package com.example.healthcare.data.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.healthcare.R
import com.example.healthcare.data.remote.model.HistoryResponseItem
import com.example.healthcare.helper.DateUtils

class HistoryAdapter: RecyclerView.Adapter<HistoryAdapter.MyViewHolder>() {
    var items =  listOf<HistoryResponseItem>()
    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val iv: ImageView = itemView.findViewById(R.id.iv_history_item)
        val tvDate: TextView = itemView.findViewById(R.id.tv_date)
        val tvCluster: TextView = itemView.findViewById(R.id.tv_cluster)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.history_item, parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = items[position]
        val date = item.createdAt
        val cluster = item.cluster

        when(cluster) {
            "Rendah" -> holder.iv.setImageResource(R.drawable.smiling)
            "Sedang" -> holder.iv.setImageResource(R.drawable.smiling)
            "Tinggi" -> holder.iv.setImageResource(R.drawable.suffering)
        }

        holder.tvCluster.text = cluster
        holder.tvDate.text = DateUtils.parseDateToIndonesianFormat(date)

    }
}