package com.lixm.animationdemo.activity

import android.graphics.BitmapFactory
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.lixm.animationdemo.R
import com.lixm.animationdemo.utils.ImageUtils
import org.jetbrains.anko.contentView

class UserImageActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_image)

        val button = contentView!!.findViewById<Button>(R.id.button1)
        val image = contentView!!.findViewById<ImageView>(R.id.user_imge)
        val text = contentView!!.findViewById<TextView>(R.id.text_img_size)

        val bmp = BitmapFactory.decodeResource(getResources(), R.mipmap.user_1)
        text.setText(ImageUtils.getImageSize(bmp))
//        val bit: Bitmap = ImageUtils.getImage(this)
//        image.setImageBitmap(bit)
        image.setBackgroundResource(R.mipmap.user_1)
        button.setOnClickListener(View.OnClickListener {
            val bit = ImageUtils.getImageM(this)
            if (bit != null) {
                image.setImageBitmap(bit)
                text.setText(text.text.toString() + "--》" + ImageUtils.getImageSize(bit))
            }
        })
    }
}
