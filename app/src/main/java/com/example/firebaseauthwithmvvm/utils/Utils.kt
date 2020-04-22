package com.example.firebaseauthwithmvvm.utils

import android.app.DatePickerDialog
import android.util.Log
import android.view.View
import androidx.lifecycle.MutableLiveData
import java.util.*

public final class Utils {

    fun createDateDialog(view: View, date: MutableLiveData<String>) {
        lateinit var picker: DatePickerDialog
        Log.e("createDateDialog", "date picker")
        val calendar: Calendar = Calendar.getInstance()
        val day: Int = calendar.get(Calendar.DAY_OF_MONTH)
        val month: Int = calendar.get(Calendar.MONTH)
        val year: Int = calendar.get(Calendar.YEAR)

        //Date picker Dialog
        Log.e("createDateDialog", "date picker 3")
        picker = DatePickerDialog(
            view.context,
            DatePickerDialog.OnDateSetListener { datePicker, _year, _monthOfYear, _dayOfMonth ->
                date.value = ("$_dayOfMonth/$_monthOfYear/$_year")
            }, year, month, day
        )
        picker.show()
    }
}