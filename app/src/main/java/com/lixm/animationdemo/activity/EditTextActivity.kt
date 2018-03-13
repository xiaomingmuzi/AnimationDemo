package com.lixm.animationdemo.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.EditText
import android.widget.ScrollView
import com.lixm.animationdemo.R
import com.lixm.animationdemo.customview.MyLinearLayoutKT
import org.jetbrains.anko.contentView


class EditTextActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_text)
        val s = "中国位于东亚，是以华夏文明为主体、中华文化为基础，以汉族为主要民族的统一多民族国家。"
        val sv = contentView?.findViewById<ScrollView>(R.id.sv)
        val et = contentView?.findViewById<EditText>(R.id.et)
        val mll = contentView?.findViewById<MyLinearLayoutKT>(R.id.mll)
        mll?.setParentScrollview(sv!!)
        mll?.setChildEditText(et!!)
        et?.setText(s)
    }

}
