package com.diagnosabanding.diagnosis.result

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.provider.Settings
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.diagnosabanding.R
import com.diagnosabanding.diagnosis.detail.ResultDetail
import com.diagnosabanding.model.ResultField
import com.diagnosabanding.repository.MyRepository
import kotlinx.android.synthetic.main.activity_result.*
import kotlinx.android.synthetic.main.loading_fb.*
import kotlinx.android.synthetic.main.toolbar.*
import org.jetbrains.anko.startActivity
import java.security.Security

class Result : AppCompatActivity(), ResultView {

    private var result : MutableList<ResultField> = mutableListOf()
    private lateinit var resultAdapter: ResultAdapter
    private lateinit var presenter: ResultPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        // setup action bar
        setSupportActionBar(my_toolbar)
        val actionBar = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)
        actionBar?.setDisplayShowHomeEnabled(true)
        actionBar?.setHomeAsUpIndicator(R.drawable.ic_arrow_back)

        // get data from intent
        val childName = intent.getStringExtra("childName")
        val childDob = intent.getStringExtra("childDob")
        val diagnosisID = intent.getStringExtra("diagnosisId")
        val pasienID = intent.getStringExtra("pasienId")
        // get deviceID
        val deviceID = Settings.Secure.getString(contentResolver, Settings.Secure.ANDROID_ID)

        child_name.text = childName
        child_dob.text = childDob

        // call action from presenter
        presenter = ResultPresenter(this, MyRepository)
        presenter.getResult(deviceID, diagnosisID, pasienID)

        // draw data in recycle view
        resultList.layoutManager = LinearLayoutManager(this)
        resultAdapter = ResultAdapter(result) {
            startActivity<ResultDetail>(
                "childName" to childName,
                "childDob" to childDob,
                "diagnosisValue" to it.diagnosis_value,
                "diagnosisName" to it.diagnosis_name,
                "solutionValue" to it.solution_value,
                "description" to it.description
            )
        }
        resultList.adapter = resultAdapter

        // call facebook loader
        shimmerLayout.startShimmerAnimation()
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }

    override fun onSuccess(data: List<ResultField>) {
        result.clear()
        result.addAll(data)
        resultAdapter.notifyDataSetChanged()

        Handler().postDelayed( {
            shimmerLayout.stopShimmerAnimation()
            shimmerLayout.visibility = View.GONE
            resultList.visibility = View.VISIBLE
        },1000)
    }

    override fun onError() {
        Handler().postDelayed( {
            shimmerLayout.stopShimmerAnimation()
            shimmerLayout.visibility = View.GONE
            errorNotFound.visibility = View.VISIBLE
        },1000)
    }
}
