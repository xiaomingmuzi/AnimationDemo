package com.lixm.animationdemo.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.lixm.animationdemo.R
import org.jetbrains.anko.*

class AnkoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_anko)
        verticalLayout {
            val name=editText()
            name!!.textColor=resources.getColor(R.color.red_record_title_tab)
            button("click me"){
                setOnClickListener { toast("hello , ${name.text}") }
            }
        }
    }
}
