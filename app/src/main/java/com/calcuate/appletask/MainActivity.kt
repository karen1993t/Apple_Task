package com.calcuate.appletask

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import kotlinx.coroutines.*

const val VALUE = "State Apple"
const val MAX_VALUE = "max value"

class MainActivity : AppCompatActivity() {

    lateinit var firstCountApple: EditText
    lateinit var maxValueBox: EditText
    lateinit var btnNext: Button
    var k = true


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        firstCountApple = findViewById(R.id.et_count_first_apple)
        maxValueBox = findViewById(R.id.et_max_count_box)
        btnNext = findViewById(R.id.bt_next)

        btnNext.setOnClickListener() {
            val valueFirst = firstCountApple.text
            val valueMax = maxValueBox.text
            if (valueFirst.isNotEmpty() && valueMax.isNotEmpty()
                && valueFirst.toString().toInt() <= valueMax.toString().toInt()) {
                val intent = Intent(this,OperationActivity::class.java)
            intent.putExtra(VALUE,firstCountApple.text.toString().toInt())
            intent.putExtra(MAX_VALUE,maxValueBox.text.toString().toInt())
            startActivity(intent)

            }else{
                Toast.makeText(
                    this,"the fields are empty or the number of apples is larger than the box"
                ,Toast.LENGTH_SHORT).show()
            }
        }

    }
}