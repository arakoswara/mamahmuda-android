package com.diagnosabanding.diagnosis.history

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.provider.Settings
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.View
import com.diagnosabanding.R
import com.diagnosabanding.diagnosis.historyDetail.HistoryDetail
import com.diagnosabanding.model.HistoryField
import com.diagnosabanding.model.HistoryResponse
import com.diagnosabanding.repository.MyRepository
import kotlinx.android.synthetic.main.activity_history.*
import kotlinx.android.synthetic.main.loading_fb.*
import kotlinx.android.synthetic.main.toolbar.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

class History : AppCompatActivity(), HistoryView{
    private lateinit var presenter: HistoryPresenter
    private lateinit var historyAdapter: HistoryAdapter
    private var history : MutableList<HistoryField> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)

        setSupportActionBar(my_toolbar)
        val actionBar = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)
        actionBar?.setDisplayShowHomeEnabled(true)
        actionBar?.setHomeAsUpIndicator(R.drawable.ic_clear)

        historyList.layoutManager = LinearLayoutManager(this)
        historyAdapter = HistoryAdapter(history) {
            startActivity<HistoryDetail>(
                "pasien_id" to it.pasien_id.toString(),
                "childName" to it.name,
                "childDob" to it.dob
            )
        }
        historyList.adapter = historyAdapter

        val deviceID = Settings.Secure.getString(contentResolver, Settings.Secure.ANDROID_ID)
        presenter = HistoryPresenter(this, MyRepository)
        presenter.getHistory(deviceID)
        shimmerLayout.startShimmerAnimation()
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }

    override fun onSuccess(data: HistoryResponse) {
        history.clear()
        history.addAll(data.data)
        historyAdapter.notifyDataSetChanged()

        if (data.status?.equals(200)!!) {
            Handler().postDelayed( {
                shimmerLayout.stopShimmerAnimation()
                shimmerLayout.visibility = View.GONE
                historyList.visibility = View.VISIBLE
            },1000)
        }
        else
        {
            Handler().postDelayed( {
                shimmerLayout.stopShimmerAnimation()
                shimmerLayout.visibility = View.GONE
                errorNotFound.visibility = View.VISIBLE
            },1000)
        }

    }

    override fun onError() {
        Handler().postDelayed( {
            shimmerLayout.stopShimmerAnimation()
            shimmerLayout.visibility = View.GONE
            errorNotFound.visibility = View.VISIBLE
        },1000)
    }
}
