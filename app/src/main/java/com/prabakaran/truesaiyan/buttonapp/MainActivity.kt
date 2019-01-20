package com.prabakaran.truesaiyan.buttonapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.Exception
import java.lang.NumberFormatException
import java.util.*

class MainActivity : AppCompatActivity() {

//    val displayOperation by lazy { findViewById<TextView>(R.id.txt_symbol) }
//    private lateinit var edt_result: EditText
//    private lateinit var edt_input: EditText


    private var operand1: Double? = null
    private var operand2: Double = 0.00
    private var pendingOperation = "="

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        edt_input = findViewById(R.id.edt_input)
//        edt_result = findViewById(R.id.edt_result)
//
//        val button0: Button = findViewById(R.id.button0)
//        val button1: Button = findViewById(R.id.button1)
//        val button2: Button = findViewById(R.id.button2)
//        val button3: Button = findViewById(R.id.button3)
//        val button4: Button = findViewById(R.id.button4)
//        val button5: Button = findViewById(R.id.button5)
//        val button6: Button = findViewById(R.id.button6)
//        val button7: Button = findViewById(R.id.button7)
//        val button8: Button = findViewById(R.id.button8)
//        val button9: Button = findViewById(R.id.button8)
//        val buttondot: Button = findViewById(R.id.buttondot)
//
//        val buttonadd: Button = findViewById(R.id.buttonadd)
//        val buttonequals: Button = findViewById(R.id.buttonequals)
//        val buttondivide: Button = findViewById(R.id.buttondivide)
//        val buttonmultiply: Button = findViewById(R.id.buttonmultiply)
//        val buttonsubtract: Button = findViewById(R.id.buttonsubtract)

        val numlistener = View.OnClickListener { v ->
            val b = v as Button
            edt_input?.append(b.text)
        }

        button0.setOnClickListener(numlistener)
        button1.setOnClickListener(numlistener)
        button2.setOnClickListener(numlistener)
        button3.setOnClickListener(numlistener)
        button4.setOnClickListener(numlistener)
        button5.setOnClickListener(numlistener)
        button6.setOnClickListener(numlistener)
        button7.setOnClickListener(numlistener)
        button8.setOnClickListener(numlistener)
        button9.setOnClickListener(numlistener)
        buttondot.setOnClickListener(numlistener)

        val oplistener = View.OnClickListener { v ->
            val operation = (v as Button).text.toString()
            val newinput = edt_input?.text.toString()
            if (newinput.isNotEmpty()) {

                if (edt_result.text.toString().isEmpty()) {
                    edt_result.setText(newinput)
                    edt_input.setText("")
                } else {
                    performoperation(newinput, operation)
                }
            }
            pendingOperation = operation
            txt_symbol.setText(operation)
        }

        buttonadd.setOnClickListener(oplistener)
        buttonsubtract.setOnClickListener(oplistener)
        buttondivide.setOnClickListener(oplistener)
        buttonmultiply.setOnClickListener(oplistener)
        buttonequals.setOnClickListener(oplistener)
        buttonnegate.setOnClickListener { v ->
            val value = edt_input.text.toString()

            System.out.println("negate")
            if (value.isEmpty()) {
                edt_input.setText("-")
            } else {
                try {
                    var tempdouble: Double = value.toDouble()
                    tempdouble *= -1
                    edt_input.setText(tempdouble.toString())
                } catch (e: Exception) {
                    edt_input.setText("")
                }
            }
        }
        buttonclear.setOnClickListener {
            operand1 = 0.00
            operand2 = 0.00
            pendingOperation = "="
            edt_input.setText("")
            edt_result.setText("")
            txt_symbol.setText("")
        }

    }

    private fun performoperation(newinput: String, operation: String) {
        if (operand1 == null) {
            operand1 = newinput.toDouble()
        } else {
            try {
                operand2 = newinput.toDouble()
                if (pendingOperation == "=") {
                    pendingOperation = operation
                }

                when (pendingOperation) {
                    "=" -> operand1 = operand2
                    "/" -> if (operand2 == 0.00) {
                        operand1 = Double.NaN
                    } else {
                        operand1 = operand1!! / operand2
                    }
                    "*" -> operand1 = operand1!! * operand2
                    "-" -> operand1 = operand1!! - operand2
                    "+" -> operand1 = operand1!! + operand2
                }
                edt_result.setText(operand1.toString())
                edt_input.setText("")
            } catch (e: NumberFormatException) {

            }


        }


    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        outState?.putString("operation", pendingOperation)
        if (operand1 != null)
            outState?.putDouble("operand", operand1!!)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        super.onRestoreInstanceState(savedInstanceState)
        pendingOperation = savedInstanceState?.getString("operation")!!
        operand1 = savedInstanceState?.getDouble("operand")
        txt_symbol.setText(savedInstanceState?.getString("operation"))
    }
}
