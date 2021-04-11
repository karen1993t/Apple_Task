package com.calcuate.appletask

import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
const val TEXT_VIEW_KEY = "Result"

class OperationActivity : AppCompatActivity() {
    lateinit var valueCountApples: TextView
    lateinit var addApples: Button
    lateinit var takeApples: Button
    lateinit var btnReset: Button
    var maxValue: Int = 0
    var firstValue: Int = 0
    var count = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_operation)

        valueCountApples = findViewById(R.id.info_first_apple)
        addApples = findViewById(R.id.add_apples)
        takeApples = findViewById(R.id.take_apples)
        btnReset = findViewById(R.id.btn_reset)
        firstValue = intent.getIntExtra(VALUE, 0)
        count = firstValue

        valueCountApples.text = firstValue.toString()
        maxValue = intent.getIntExtra(MAX_VALUE, 0)

        if (savedInstanceState != null && savedInstanceState.containsKey(TEXT_VIEW_KEY)) {
            valueCountApples.text = savedInstanceState.getString(TEXT_VIEW_KEY)
            count = valueCountApples.text.toString().toInt()
        }

        addApples.setOnClickListener() {
            if (count != maxValue) {
                valueCountApples.setBackgroundResource(R.drawable.apple_box)
                btnReset.visibility = View.INVISIBLE
                GlobalScope.launch(Dispatchers.Default) {
                    count += 1
                    withContext(Dispatchers.Main) {
                        valueCountApples.text = count.toString()
                    }
                }
            } else {

                btnReset.visibility = View.VISIBLE
                btnReset.setOnClickListener() {
                    valueCountApples.text = firstValue.toString()
                    count = firstValue
                }
            }
        }
        takeApples.setOnClickListener() {
            if (count != 0) {
                btnReset.visibility = View.INVISIBLE
                GlobalScope.launch(Dispatchers.Default) {
                    count -= 1
                    withContext(Dispatchers.Main) {
                        valueCountApples.text = count.toString()
                    }
                }
            } else {
                valueCountApples.setBackgroundResource(R.drawable.empty_box)
                btnReset.visibility = View.VISIBLE
                btnReset.setOnClickListener() {
                    valueCountApples.setBackgroundResource(R.drawable.apple_box)
                    valueCountApples.text = firstValue.toString()
                    count = firstValue

                }
            }
        }
    }
    override fun onSaveInstanceState(outState: Bundle) {
        outState?.run {
            putString(TEXT_VIEW_KEY, count.toString())
        }
        super.onSaveInstanceState(outState)
    }
}