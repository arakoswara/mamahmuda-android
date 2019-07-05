package com.diagnosabanding.diagnosis

import android.app.DatePickerDialog
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.Spannable
import android.text.TextWatcher
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.DatePicker
import android.widget.EditText
import com.diagnosabanding.R
import com.diagnosabanding.diagnosis.history.History
import com.diagnosabanding.diagnosis.process.DiagnoseProcess
import com.diagnosabanding.staticPage.About
import com.otaliastudios.autocomplete.Autocomplete
import com.otaliastudios.autocomplete.AutocompletePolicy
import kotlinx.android.synthetic.main.data_child.*
import kotlinx.android.synthetic.main.toolbar.*
import org.jetbrains.anko.startActivity
import java.text.SimpleDateFormat
import java.util.*


class DataChild : AppCompatActivity(), AutocompletePolicy {

    private var calendar = Calendar.getInstance()
    private var menuItem: Menu? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.data_child)

        setSupportActionBar(my_toolbar)
        calendarDialog()

        btnSave.setOnClickListener {
            validateForm()

            if (child_name.text.isNotEmpty() && child_dob.text.isNotEmpty()) {
                val childName = child_name.text.toString()
                val childDob = child_dob.text.toString()
                startActivity<DiagnoseProcess>("childName" to childName, "childDob" to childDob)
            }
        }
    }

    override fun shouldShowPopup(text: Spannable?, cursorPos: Int): Boolean {
        return text.isNullOrEmpty()
    }

    override fun shouldDismissPopup(text: Spannable?, cursorPos: Int): Boolean {
        return text.isNullOrBlank()
    }

    override fun getQuery(text: Spannable?): CharSequence {
        return  text.toString()
    }

    override fun onDismiss(text: Spannable?) {}

    private fun validateForm() {
        child_name.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                Log.d("After ", s.toString())
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                Log.d("Before ", s.toString())
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                Log.d("On ", s.toString())
            }

        })

        child_dob.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

        })
        child_name.error = if (child_name.text.toString().length == 0 ) "Nama anak tidak boleh kosong" else null
        child_dob.error = if (child_dob.text.toString().length == 0) "Tanggal lahir tidak boleh kosong" else null
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.history, menu)
        menuItem = menu

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            R.id.history -> {
                startActivity<History>()
                true
            }
            R.id.info -> {
                startActivity<About>()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun calendarDialog() {
        val dateSetListener = object : DatePickerDialog.OnDateSetListener {
            override fun onDateSet(view: DatePicker, year: Int, monthOfYear: Int,
                                   dayOfMonth: Int) {
                calendar.set(Calendar.YEAR, year)
                calendar.set(Calendar.MONTH, monthOfYear)
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                updateDateInView()
            }
        }

        child_dob.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View) {
                val date = DatePickerDialog(this@DataChild,
                    dateSetListener,
                    calendar.get(Calendar.YEAR),
                    calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.DAY_OF_MONTH))

                date.datePicker.maxDate = calendar.timeInMillis
                date.datePicker.minDate = calendar.timeInMillis - (1000 * 60 * 60 * 24)
                date.show()
            }

        })
    }

    private fun updateDateInView() {
        val myFormat = "dd/MM/yyyy"
        val sdf = SimpleDateFormat(myFormat, Locale.US)
        child_dob.setText(sdf.format(calendar.getTime()))
    }
}
