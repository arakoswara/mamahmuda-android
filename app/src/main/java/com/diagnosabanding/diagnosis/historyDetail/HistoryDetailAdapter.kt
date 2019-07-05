package com.diagnosabanding.diagnosis.historyDetail

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.diagnosabanding.R
import com.diagnosabanding.model.HistoryDetailField
import kotlinx.android.synthetic.main.item_history_detail.view.*

class HistoryDetailAdapter(
    private val historyPasien: List<HistoryDetailField>,
    private val listener: (HistoryDetailField) -> Unit
) : RecyclerView.Adapter<HistoryDetailAdapter.HistoryPasienViewHolder>(){

    override fun onCreateViewHolder(view: ViewGroup, position: Int): HistoryPasienViewHolder {
        return HistoryPasienViewHolder(LayoutInflater.from(view.context).inflate(R.layout.item_history_detail, view, false))
    }

    override fun getItemCount(): Int = historyPasien.size

    override fun onBindViewHolder(holder: HistoryPasienViewHolder, position: Int) {
        holder.bindItem(historyPasien[position], listener)
    }

    class HistoryPasienViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bindItem(result: HistoryDetailField, listener: (HistoryDetailField) -> Unit) {
            itemView.diagnosis.text = result.diagnosis
            itemView.setOnClickListener { listener(result) }
        }
    }
}