package com.diagnosabanding.diagnosis.detail

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import com.diagnosabanding.R
import kotlinx.android.synthetic.main.activity_result_detail.*
import kotlinx.android.synthetic.main.toolbar.*

class ResultDetail : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result_detail)

        setSupportActionBar(my_toolbar)
        val actionBar = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)
        actionBar?.setDisplayShowHomeEnabled(true)
        actionBar?.setHomeAsUpIndicator(R.drawable.ic_arrow_back)

        val childName = intent.getStringExtra("childName")
        val childDob= intent.getStringExtra("childDob")
        val descriptionValue = intent.getStringExtra("description")
        val solustionValue = intent.getStringExtra("solutionValue")
        val diagnosisValue = intent.getStringExtra("diagnosisValue")
        val diagnosisName = intent.getStringExtra("diagnosisName")

        val oldValue = "<br>"
        val newValue = "\n"

        name.text = childName
        dob.text = childDob
        name_medis.text = diagnosisName
        name_general.text = diagnosisName
        diagnosis_percent.text = "${diagnosisValue}%"
        description.text = descriptionValue.replace(oldValue, newValue)
        solution.text = solustionValue.replace(oldValue, newValue)
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }
}
