package com.diagnosabanding.diagnosis.result

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.diagnosabanding.R
import com.diagnosabanding.model.ResultField
import kotlinx.android.synthetic.main.item_result.view.*

class ResultAdapter(
    private val result: List<ResultField>, private val listener: (ResultField) -> Unit
) : RecyclerView.Adapter<ResultAdapter.ResultViewHolder>()
{
    override fun onCreateViewHolder(view: ViewGroup, p1: Int): ResultViewHolder {
        return ResultViewHolder(LayoutInflater.from(view.context).inflate(R.layout.item_result, view, false))
    }

    override fun getItemCount(): Int = result.size

    override fun onBindViewHolder(p0: ResultViewHolder, p1: Int) {
        p0.bindItem(result[p1], listener)
    }

    class ResultViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bindItem(resultField: ResultField, listener: (ResultField) -> Unit) {
            itemView.diagnosis.text = "${resultField.diagnosis_name} ${resultField.diagnosis_value}%"
            itemView.setOnClickListener { listener(resultField) }
        }
    }
}