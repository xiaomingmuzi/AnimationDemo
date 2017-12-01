package com.lixm.animationdemo.activity

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.lixm.animationdemo.R
import com.lixm.animationdemo.customview.CustomPayEditText
import com.lixm.animationdemo.dialog.PayDialog

class PayPassportActivity : BaseActivity() {

    private val TAG = "PayPassportActivity"
    private var mCustomPayEditText: CustomPayEditText? = null
    private var CustomPayEditText2: CustomPayEditText? = null
    private var ellipsizeTxt: TextView? = null
    private var ellipsizeEt: EditText? = null
    private var button: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pay_passport)
        mCustomPayEditText = findViewById<View>(R.id.ppe_pwd) as CustomPayEditText
        CustomPayEditText2 = findViewById<View>(R.id.ppe_pwd2) as CustomPayEditText
        button = findViewById<View>(R.id.btn_dialog) as Button

        mCustomPayEditText!!.initStyle(R.drawable.edit_num_bg, 6, 0.33f, R.color.color_333, R.color.aaaaa, 20)
        mCustomPayEditText!!.setOnTextFinishListener { str -> //密码输入完后的回调
            Toast.makeText(this@PayPassportActivity, str, Toast.LENGTH_SHORT).show()
        }

        CustomPayEditText2!!.initStyle(R.drawable.edit_num_bg_red, 8, 0.33f, R.color.colorAccent, R.color.colorAccent, 20)
        CustomPayEditText2!!.setOnTextFinishListener { str ->
            Toast.makeText(this@PayPassportActivity, "显示明文：" + str, Toast.LENGTH_SHORT).show()
            CustomPayEditText2!!.setShowPwd(false)
        }

        button!!.setOnClickListener {
            val payDialog = PayDialog(this@PayPassportActivity)
            payDialog.show()
        }

        ellipsizeTxt = findViewById<View>(R.id.ellipsize_txt) as TextView
        ellipsizeEt = findViewById<View>(R.id.ellipsize_et) as EditText
        ellipsizeEt!!.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable) {
                ellipsizeTxt!!.text = s.toString()
            }
        })
    }
}
