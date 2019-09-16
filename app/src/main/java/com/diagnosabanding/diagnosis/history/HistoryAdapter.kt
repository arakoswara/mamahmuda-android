package com.diagnosabanding.diagnosis.history

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.diagnosabanding.R
import com.diagnosabanding.model.HistoryField
import kotlinx.android.synthetic.main.item_history.view.*

class HistoryAdapter(
    private val history: List<HistoryField>, private val listener: (HistoryField) -> Unit)
    : RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder> (){
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): HistoryViewHolder {
        return HistoryViewHolder(LayoutInflater.from(p0.context).inflate(R.layout.item_history, p0, false))
    }

    override fun getItemCount(): Int = history.size

    override fun onBindViewHolder(p0: HistoryViewHolder, p1: Int) {
        p0.bindItem(history[p1], listener)
    }

    class HistoryViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bindItem(history: HistoryField, listener: (HistoryField) -> Unit) {
            itemView.name.text = history.name
            itemView.dob.text = "Tanggal Lahir : ${history.dob}"
            itemView.createdAt.text = "Tanggal terdaftar : ${history.created_at}"
            itemView.setOnClickListener {
                listener(history)
            }
        }
    }
}