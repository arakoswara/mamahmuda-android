package com.diagnosabanding.diagnosis.process

import android.util.Log
import com.diagnosabanding.model.DiagnoseResponse
import com.diagnosabanding.model.GejalaField
import com.diagnosabanding.model.GejalaResponse
import com.diagnosabanding.repository.MyRepository

class ProcessPresenter(private val view: ProcessView, private val myRepository: MyRepository) {
    fun getGejala() {
        myRepository.getGejala(object : ProcessCallback<GejalaResponse> {
            override fun processSuccess(data: List<GejalaField>) {
                view.processSuccess(data)
            }

            override fun processError() {
                Log.d("ERROR ", true.toString())
                view.processError()
            }
        })
    }
}