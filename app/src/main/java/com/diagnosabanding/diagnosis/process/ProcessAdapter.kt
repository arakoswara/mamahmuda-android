package com.diagnosabanding.diagnosis.process

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import com.diagnosabanding.R
import com.diagnosabanding.model.GejalaField
import kotlinx.android.synthetic.main.item_param.view.*
import org.jetbrains.anko.sdk27.coroutines.onCheckedChange

class ProcessAdapter(
    private val gejala: List<GejalaField>,
    private val listener: (GejalaField) -> Unit)  : RecyclerView.Adapter<ProcessAdapter.ProcessViewHolder>()
{
    override fun onCreateViewHolder(view: ViewGroup, position: Int): ProcessViewHolder {
        return ProcessViewHolder(LayoutInflater.from(view.context).inflate(R.layout.item_param, view, false))
    }

    override fun getItemCount(): Int = gejala.size

    override fun onBindViewHolder(holder: ProcessViewHolder, position: Int) {
        holder.bindItem(gejala[position], listener)
        holder.itemView.checkbox_gejela.setOnCheckedChangeListener(null)
        holder.itemView.checkbox_gejela.isFocusable = false
        holder.itemView.setTag(gejala[position])
    }

    class ProcessViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bindItem(gejalaField: GejalaField, listener: (GejalaField) -> Unit) {
            itemView.checkbox_gejela.text = gejalaField.gejala_desc
            itemView.checkbox_gejela.setOnClickListener {
                listener(gejalaField)
            }
        }
    }
}