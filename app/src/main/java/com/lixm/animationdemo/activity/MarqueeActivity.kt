package com.lixm.animationdemo.activity

import android.content.Context
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.widget.TextView
import android.widget.Toast
import com.lixm.animationdemo.R
import com.lixm.animationdemo.customview.ClickTextView
import com.lixm.animationdemo.customview.MarqueeView
import org.jetbrains.anko.contentView
import java.util.*

class MarqueeActivity : BaseActivity() {

    private var dynamicList: ArrayList<String>? = null
    private val mContext: Context = this

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_marquee)

        contentView!!.findViewById<TextView>(R.id.myTextView).setSelected(true)
        val marquee = contentView!!.findViewById<MarqueeView>(R.id.marquee_view)
        initDynamicMarquee(marquee)

        val clickTxt=contentView!!.findViewById<ClickTextView>(R.id.click_txt)
        clickTxt.setText(dynamicList)
        clickTxt.ellipsize= TextUtils.TruncateAt.MARQUEE
        clickTxt.setSelected(true)
    }

    private fun initDynamicMarquee(marquee_view: MarqueeView) {
        dynamicList = ArrayList()
        //        dynamicList.add("李玟《Di Da Di》、周杰伦《简单爱》、S.H.E《恋人未满》、萧亚轩《最熟悉的陌生人》、");
        //        dynamicList.add("梁静茹《分手快乐》、蔡依林《倒带》、五月天《志明与春娇》、戴佩妮《怎样》、王菲《红豆》、");
        //        dynamicList.add("林俊杰《学不会》、陶喆《爱很简单》、陈奕迅《淘汰》、林宥嘉《说谎》、庾澄庆《情非得已》、");
        //        dynamicList.add("飞儿乐团《Lydia》、徐佳莹《失落沙洲》、杨丞琳《暧昧》、陈绮贞《旅行的意义》、孙燕姿《克卜勒》、");
        //        dynamicList.add("蔡健雅《无底洞》、王力宏《唯一》、张惠妹《掉了》、莫文蔚《真的吗》、卢广仲《我爱你》、");
        //        dynamicList.add("萧敬腾《王妃》、苏打绿《小情歌》、田馥甄《小幸运》");
        //        dynamicList.add("李玟《Di Da Di》、周杰伦《简单爱》、S.H.E《恋人未满》、萧亚轩《最熟悉的陌生人》、");
        //        dynamicList.add("梁静茹《分手快乐》、蔡依林《倒带》、五月天《志明与春娇》、戴佩妮《怎样》、王菲《红豆》、");
        dynamicList!!.add("李玟《Di Da Di》")
        dynamicList!!.add("梁静茹《分手快乐》")
        dynamicList!!.add("林俊杰《学不会》")
        dynamicList!!.add("飞儿乐团《Lydia》")
        dynamicList!!.add("蔡健雅《无底洞》")
        dynamicList!!.add("萧敬腾《王妃》")
        dynamicList!!.add("田馥甄《小幸运》")
        dynamicList!!.add("S.H.E《恋人未满》")
        for (i in dynamicList!!.indices) {
            val textView = LayoutInflater.from(mContext).inflate(R.layout.horizontal_dynamic_item, null) as TextView
            textView.text = dynamicList!!.get(i)
            textView.setOnClickListener { Toast.makeText(mContext, "点我" + i, Toast.LENGTH_SHORT).show() }
            marquee_view.addViewInQueue(textView)
        }
//        marquee_view.setScrollSpeed(10)
//        marquee_view.setScrollDirection(MarqueeView.RIGHT_TO_LEFT)
//        marquee_view.setLayoutMargin(15)
        marquee_view.startScroll()
    }
}
