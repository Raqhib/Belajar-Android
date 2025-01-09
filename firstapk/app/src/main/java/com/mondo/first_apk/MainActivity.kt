package com.mondo.first_apk

import android.health.connect.datatypes.units.Length
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlin.math.ulp

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var edtWidth: EditText
    private lateinit var edtHeight: EditText
    private lateinit var edtLength: EditText
    private lateinit var btnCalculate: Button
    private lateinit var tvResult: TextView

    companion object{
        private const val STATE_RESULT = "state_result"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        edtWidth = findViewById(R.id.edt_width)
        edtHeight = findViewById(R.id.edt_height)
        edtLength = findViewById(R.id.edt_length)
        btnCalculate = findViewById(R.id.btn_calculate)
        tvResult = findViewById(R.id.tv_result)
        btnCalculate.setOnClickListener(this)

        if (savedInstanceState != null){
            val result = savedInstanceState.getString(STATE_RESULT)
            tvResult.text = result
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(STATE_RESULT, tvResult.text.toString())
    }

    override fun onClick(v: View?) {
        if (v?.id == R.id.btn_calculate){
            val inputLength = edtLength.text.toString().trim()
            val inputWidth = edtWidth.text.toString().trim()
            val inputHeight = edtHeight.text.toString().trim()

//            >>>>if Expression<<<
//            var isEmptyFields = true
//            if (inputLength.isEmpty()){
//                isEmptyFields = true
//                edtLength.error = "Field Tidak Boleh Kosong"
//            }else if (inputWidth.isEmpty()){
//                isEmptyFields = true
//                edtWidth.error = "Field Tidak Boleh Kosong"
//            }else if (inputHeight.isEmpty()){
//                isEmptyFields = true
//                edtHeight.error = "Field Tidak Boleh Kosong"
//            }else{
//                val volume = inputLength.toDouble() * inputWidth.toDouble() * inputHeight.toDouble()
//                tvResult.text = volume.toString()
//            }

//            >>>>When Expression<<<<<
            var isEmptyFields = when(true){
                inputLength.isEmpty() -> edtLength.error = "Field Tidak Boleh Kosong"
                inputHeight.isEmpty() -> edtHeight.error = "Field Tidak Boleh Kosong"
                inputWidth.isEmpty() -> edtWidth.error = "Field Tidak Boleh Kosong"
                else -> {
                    val volume = inputLength.toDouble() * inputWidth.toDouble() * inputHeight.toDouble()
                    tvResult.text = volume.toString()
                }

            }
        }
    }
}