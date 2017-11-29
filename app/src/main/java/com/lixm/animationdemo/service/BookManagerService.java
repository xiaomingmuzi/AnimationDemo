package com.lixm.animationdemo.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;

import com.lixm.animationdemo.Book;
import com.lixm.animationdemo.IBookManager;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author Lixm
 * @date 2017/11/27
 * @detail
 */

public class BookManagerService extends Service {

    private static final String TAG = "BMS";
    private CopyOnWriteArrayList<Book> mBookeList = new CopyOnWriteArrayList<>();

    private Binder mBinder=new IBookManager.Stub(){
        @Override
        public List<Book> getBookList() throws RemoteException {
            return mBookeList;
        }

        @Override
        public void addBook(Book book) throws RemoteException {
            mBookeList.add(book);
        }
    };

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
//        return null;
    }
}
