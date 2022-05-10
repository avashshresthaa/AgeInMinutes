package com.avash.ageinminutes

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.DatePicker
import android.widget.Toast
import com.avash.ageinminutes.databinding.ActivityMainBinding
import java.text.SimpleDateFormat
import java.util.*


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //toast.length is used for the duration of the toast
        //context: this means that my main activity will be the context in which the toast is displayed
        //.show to show it
        binding.btnDatePicker.setOnClickListener {view ->
            clickDatePicker(view)
            //Toast.makeText(this, "Button works", Toast.LENGTH_LONG).show()
        }
    }

    // view is used to get the view first and use it to call the method clickdatepicker

    fun clickDatePicker(view: View){
        /* getInstance will gives instance in which we open the calendar,
        we are using Calendar class which allows us to create calendar object my calendar */
        val myCalender = Calendar.getInstance()
        val year = myCalender.get(Calendar.YEAR)
        val month = myCalender.get(Calendar.MONTH)
        val day = myCalender.get(Calendar.DAY_OF_MONTH)

        //dialog is a type of pop up and we use it say pls open the pop up which is a date picker

        val dpd = DatePickerDialog(this,
            DatePickerDialog.OnDateSetListener {
                view, selectedYear, selectedMonth, selectedDayOfMonth ->
                Toast.makeText(this, "Thank you for using my app.", Toast.LENGTH_LONG).show()

                var selectedDate = "$selectedDayOfMonth/ ${selectedMonth+1}/ $selectedYear"
                binding.tvSelectedDate.setText(selectedDate)

                var sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)

                val theDate = sdf.parse(selectedDate)

                val selectedDateInMinutes = theDate!!.time / 60000

                //
                val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))

                val currentDateToMinutes = currentDate!!.time / 60000

                val differenceInMinutes = currentDateToMinutes - selectedDateInMinutes

                val daysOFBorn = differenceInMinutes / 1440

                binding.tvSelectedDateInMinutes.setText(differenceInMinutes.toString())

                binding.tvSelectedDateInDays.setText(daysOFBorn.toString())

            }
            ,year
            ,month
            ,day)

        dpd.datePicker.setMaxDate(Date().time - 86400000)
        dpd.show()
    }

}