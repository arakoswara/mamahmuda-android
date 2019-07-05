package com.diagnosabanding.diagnosis.process

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.provider.Settings
import android.support.v7.widget.LinearLayoutManager
import android.telephony.TelephonyManager
import android.util.Log
import android.view.Menu
import android.view.View
import android.widget.ProgressBar
import com.diagnosabanding.R
import com.diagnosabanding.diagnosis.result.Result
import com.diagnosabanding.model.DiagnoseResponse
import com.diagnosabanding.model.GejalaField
import com.diagnosabanding.repository.MyRepository
import com.diagnosabanding.utils.invisible
import com.diagnosabanding.utils.visible
import kotlinx.android.synthetic.main.diagnose_process.*
import kotlinx.android.synthetic.main.loading_fb.*
import kotlinx.android.synthetic.main.toolbar.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.toast

class DiagnoseProcess : AppCompatActivity(), ProcessView, PostParamView {

    private var gejala: MutableList<GejalaField> = mutableListOf()
    private lateinit var processAdapter: ProcessAdapter
    private lateinit var presenter: ProcessPresenter
    private lateinit var postProcessPresenter: PostProcessPresenter
    private lateinit var progressBar: ProgressBar
    private var menuItem: Menu? = null
    private var gejalaCodeList : MutableList<Int?> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.diagnose_process)

        setSupportActionBar(my_toolbar)
        val actionBar = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)
        actionBar?.setDisplayShowHomeEnabled(true)
        actionBar?.setHomeAsUpIndicator(R.drawable.ic_arrow_back)
        tvProcess.text = "proses"

        presenter = ProcessPresenter(this, MyRepository)
        presenter.getGejala()

        println("Test "+gejalaCodeList)

        paramList.layoutManager = LinearLayoutManager(this)
        processAdapter = ProcessAdapter(gejala) {
            gejalaCodeList.add(it.gejala_id)
        }
        paramList.adapter = processAdapter

        swipeRefresh.onRefresh {                                          
            presenter.getGejala()
        }
        shimmerLayout.startShimmerAnimation()

        tvProcess.setOnClickListener {
            val childName = intent.getStringExtra("childName")
            val childDob = intent.getStringExtra("childDob")
            val deviceID = Settings.Secure.getString(contentResolver, Settings.Secure.ANDROID_ID)

            if (gejalaCodeList.size.equals(0)) {
                toast("Error. \nSilakan pilih gejala sesuai dengan yang diderita.")
            } else {
                postProcessPresenter = PostProcessPresenter(this, MyRepository)
                postProcessPresenter.postGejala(gejalaCodeList, childName, childDob, deviceID)
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.process_menu, menu)
        menuItem = menu
        return super.onCreateOptionsMenu(menu)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

    override fun onPostSuccess(data: DiagnoseResponse?) {
        val childName = intent.getStringExtra("childName")
        val childDob = intent.getStringExtra("childDob")

        if (data?.status.toString().equals("200")) {
            startActivity<Result>(
                "childName" to childName,
                "childDob" to childDob,
                "diagnosisId" to data?.data.toString(),
                "pasienId" to "0"
            )
            finish()
        } else {
            toast("Error, Please try again.")
        }
    }

    override fun onPostError() {
        toast("Error. Please check your current connection.")
    }

    override fun processSuccess(data: List<GejalaField>) {
        swipeRefresh.isRefreshing = false
        gejala.clear()
        gejala.addAll(data)
        processAdapter.notifyDataSetChanged()

        Handler().postDelayed({
            shimmerLayout.stopShimmerAnimation()
            shimmerLayout.visibility = View.GONE
            paramList.visibility = View.VISIBLE
            tvProcess.visibility = View.VISIBLE
        }, 3000)
    }

    override fun processError() {
        toast("Data not found, please refresh page.")
    }

    override fun showLoading() {
        progressBar.visible()
    }

    override fun hideLoading() {
        progressBar.invisible()
    }
}
