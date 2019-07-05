package com.diagnosabanding.diagnosis.historyDetail

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.provider.Settings
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.View
import com.diagnosabanding.R
import com.diagnosabanding.diagnosis.result.Result
import com.diagnosabanding.model.HistoryDetailField
import com.diagnosabanding.repository.MyRepository
import kotlinx.android.synthetic.main.activity_history_detail.*
import kotlinx.android.synthetic.main.loading_fb.*
import kotlinx.android.synthetic.main.toolbar.*
import org.jetbrains.anko.startActivity

class HistoryDetail : AppCompatActivity(), HistoryDetailView {

    private lateinit var myAdapter: HistoryDetailAdapter
    private lateinit var myPresenter: HistoryDetailPresenter
    private var historyDetailField: MutableList<HistoryDetailField> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history_detail)

        setSupportActionBar(my_toolbar)
        val actionBar = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)
        actionBar?.setDisplayShowHomeEnabled(true)
        actionBar?.setHomeAsUpIndicator(R.drawable.ic_arrow_back)

        val deviceID = Settings.Secure.getString(contentResolver, Settings.Secure.ANDROID_ID)
        val pasienID = intent.getStringExtra("pasien_id")
        val childName = intent.getStringExtra("childName")
        val childDob= intent.getStringExtra("childDob")

        child_name.text = childName
        child_dob.text = childDob

        myPresenter = HistoryDetailPresenter(this, MyRepository)
        myPresenter.getHistoryPasien(deviceID, pasienID)

        historyDetailList.layoutManager = LinearLayoutManager(this)
        myAdapter = HistoryDetailAdapter(historyDetailField) {
            startActivity<Result>(
                "childName" to childName,
                "childDob" to childDob,
                "diagnosisId" to it.diagnosis_id.toString(),
                "pasienId" to pasienID
            )
        }
        historyDetailList.adapter = myAdapter
        shimmerLayout.startShimmerAnimation()
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }

    override fun onSuccess(data: List<HistoryDetailField>) {
        historyDetailField.clear()
        historyDetailField.addAll(data)
        myAdapter.notifyDataSetChanged()

        Handler().postDelayed( {
            shimmerLayout.stopShimmerAnimation()
            shimmerLayout.visibility = View.GONE
            historyDetailList.visibility = View.VISIBLE
        },1000)
    }

    override fun onError() {
    }
}
