// IBookManager.aidl
package com.lixm.animationdemo;
import com.lixm.animationdemo.Book;

// Declare any non-default types here with import statements

interface IBookManager {
   List<Book> getBookList();
   void addBook(in Book book);
}
