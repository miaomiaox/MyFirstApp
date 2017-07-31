// IBookManager.aidl
package com.xinluqishi.myfirstapp;

// Declare any non-default types here with import statements

import com.xinluqishi.myfirstapp.bean.Book;
import com.xinluqishi.myfirstapp.INewBookArrivedListener;

interface IBookManager {


    List<Book> getBookList();

    void addBook(in Book book);

    void registerListener(INewBookArrivedListener listener);

    void unregisterListener(INewBookArrivedListener listener);

    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat,
            double aDouble, String aString);

}
