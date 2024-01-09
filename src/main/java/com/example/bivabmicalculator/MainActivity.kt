package com.example.bivabmicalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.annotation.SuppressLint
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible

class MainActivity : AppCompatActivity() {
    private lateinit var heightET:EditText
    private lateinit var weightET:EditText
    private lateinit var calBtn :Button
    private lateinit var resTV :TextView
    private lateinit var resBmi :TextView
    private lateinit var resRange :TextView
    private lateinit var resultParent :ConstraintLayout
    private lateinit var clearBtn :Button

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



            heightET=findViewById(R.id.heightET)
            weightET=findViewById(R.id.weightET)
            calBtn=findViewById(R.id.calBtn)
            resTV=findViewById(R.id.resTV)
            resBmi=findViewById(R.id.resBmi)
            resRange=findViewById(R.id.resRange)
            resultParent=findViewById(R.id.resultParent)
            clearBtn=findViewById(R.id.clearBtn)

            calBtn.setOnClickListener {
                val weight = weightET.text.toString()
                val height = heightET.text.toString()

                resultParent.visibility=View.VISIBLE
                clearBtn.visibility=View.VISIBLE


                if(height.isEmpty() && weight.isEmpty()){
                    val cusToast= AlertDialog.Builder(this)
                    cusToast.setIcon(R.drawable.baseline_add_24)
                    cusToast.setTitle("BMI Calculate")
                    cusToast.setMessage("Please input your values.")
                    cusToast.setNeutralButton("Cancel"){dialogInterface,which->
                        Toast.makeText(this,"Cancel",Toast.LENGTH_SHORT).show()
                    }
                    cusToast.setNegativeButton("No"){dialogInterface,which->
                        Toast.makeText(this,"No",Toast.LENGTH_SHORT).show()
                    }
                    cusToast.setPositiveButton("Yes"){dialogInterface,which->
                        Toast.makeText(this,"yes",Toast.LENGTH_SHORT).show()
                    }
                    val dialog = cusToast.create()
                    dialog.show()
                }
                else{
                    val bmi = weight.toFloat() / ((height.toFloat() / 100) * (height.toFloat() / 100))
                    var bmiDigit = String.format("%.2f", bmi).toFloat()
                    displayResult(bmiDigit)
                }

            }
            clearBtn.setOnClickListener(){



            }

        }
        private fun displayResult(bmiDigit: Float) {
            resTV.text= "You are healthy"
            resBmi.text = bmiDigit.toString()
            resRange.text = "(Normal range is 18.5-24.5)"

            var result = ""
            var color = 0
            var range = ""

            when {
                bmiDigit < 18.50 -> {
                    result = "Underweight"
                    color = R.color.under_weight
                    range = "(Underweight range is less than 18.50)"
                }

                bmiDigit in 18.50..24.99 -> {
                    result = "Healty"
                    color = R.color.normal
                    range = "(Healty range is 18.50-24.99)"
                }

                bmiDigit in 25.00..29.99 -> {
                    result = "Overweight"
                    color = R.color.over_weight
                    range = "(Overweight range is 24.99-29.99)"
                }

                bmiDigit > 29.99 -> {
                    result = "Obese"
                    color = R.color.obese
                    range = "(Obese range is greater than 29.99)"
                }
            }
            resTV.setTextColor(ContextCompat.getColor(this, color))
            resTV.text = result
            resRange.setTextColor(ContextCompat.getColor(this, color))
            resRange.text = range

        }

    }

