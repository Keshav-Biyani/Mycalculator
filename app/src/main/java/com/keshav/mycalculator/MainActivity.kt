package com.keshav.mycalculator

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import java.lang.ArithmeticException

class MainActivity : AppCompatActivity() {
   private var txtCalc_text :TextView? =null
    var lastNumeric :Boolean = false
    var lastDot : Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        txtCalc_text = findViewById(R.id.txtCalc_text)
    }
    fun onDigti(view: View){
      txtCalc_text?.append((view as Button).text)
        lastNumeric = true
        lastDot = false

    }
    private fun removeZeroAfterDot(result : String):String{
        var value = result
        if(value.contains(".0")){
            value = result.substring(0,result.length -2)
        }
        return  value
    }
    fun onClear(view : View){
        txtCalc_text?.text = ""
    }
    fun onDecimalpoint(view: View){
        if(lastNumeric && !lastDot){
            txtCalc_text?.append(".")
            lastDot = true
            lastNumeric = false

        }
    }
    @SuppressLint("SetTextI18n")
    fun onEqual(view: View){
        if(lastNumeric){
            var txtvalue = txtCalc_text?.text.toString()
            var prefix = ""
            try {
                if(txtvalue.startsWith("-")){
                    prefix = "-"
                    txtvalue = txtvalue.substring(1)

                }
                if(txtvalue.contains("-")) {
                    val splitValue = txtvalue.split("-")
                    var first = splitValue[0]
                    var second = splitValue[1].toDouble()
                    if(prefix.isNotEmpty()){
                        first = prefix +  first
                    }
                    var finalresult = removeZeroAfterDot((first.toDouble() - second).toString())
                    txtCalc_text?.text = finalresult
                }else if(txtvalue.contains("+")){
                    val splitValue = txtvalue.split("+")
                    var first = splitValue[0]
                    var second = splitValue[1].toDouble()
                    if(prefix.isNotEmpty()){
                        first = prefix +  first
                    }
                    var finalresult = removeZeroAfterDot((first.toDouble()  + second).toString())
                    txtCalc_text?.text = finalresult
                }else if(txtvalue.contains("/")){
                    val splitValue = txtvalue.split("/")
                    var first = splitValue[0]
                    var second = splitValue[1].toDouble()
                    if(prefix.isNotEmpty()){
                        first = prefix +  first
                    }
                    var finalresult = removeZeroAfterDot((first.toDouble() / second).toString())
                    txtCalc_text?.text = finalresult
                }else if(txtvalue.contains("*")){
                    val splitValue = txtvalue.split("*")
                    var first = splitValue[0]
                    var second = splitValue[1].toDouble()
                    if(prefix.isNotEmpty()){
                        first = prefix +  first
                    }
                    var finalresult = removeZeroAfterDot((first.toDouble() * second).toString())
                    txtCalc_text?.text = finalresult
                }
            }
            catch (e : ArithmeticException){
                e.printStackTrace()
            }

        }
    }
    fun onDelete(view: View){

    }

    fun onOperator(view: View){
        txtCalc_text?.text?.let {
            if(lastNumeric && !isOperator(it.toString())){
                txtCalc_text?.append((view as Button).text)
                lastNumeric = false
                lastDot = false
            }
        }
    }
    private fun isOperator(value : String) : Boolean {
        return if(value.startsWith("-"))
        {
            false
        }else{
            value.contains("+")|| value.contains("-")|| value.contains("/")|| value.contains("*")

        }

    }



}