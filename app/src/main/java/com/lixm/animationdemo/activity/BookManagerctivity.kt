package com.lixm.animationdemo.activity

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import com.lixm.animationdemo.Book
import com.lixm.animationdemo.IBookManager
import com.lixm.animationdemo.R
import com.lixm.animationdemo.service.BookManagerService
import kotlinx.android.synthetic.main.activity_book_managerctivity.*

class BookManagerctivity : AppCompatActivity() {

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                message.setText(R.string.title_home)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_dashboard -> {
                message.setText(R.string.title_dashboard)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_notifications -> {
                message.setText(R.string.title_notifications)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }


    private val mConnection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName, service: IBinder) {
            var bookManager: IBookManager = IBookManager.Stub.asInterface(service)
            try {
                var list:List<Book> = bookManager.bookList
                println("query book list,list type:"+list.javaClass.canonicalName)
                println("query book list : "+list.toString())


            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        override fun onServiceDisconnected(name: ComponentName) {

        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_managerctivity)

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        var intent = Intent(this,BookManagerService::class.java)
        bindService(intent,mConnection, Context.BIND_AUTO_CREATE)
    }

    override fun onDestroy() {
        unbindService(mConnection)
        super.onDestroy()

    }
}
