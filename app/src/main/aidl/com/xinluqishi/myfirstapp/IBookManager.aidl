// IBookManager.aidl
package com.xinluqishi.myfirstapp;

// Declare any non-default types here with import statements

import com.xinluqishi.myfirstapp.bean.Book;

interface IBookManager {


    List<Book> getBookList();

    void addBook(in Book book);


    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat,
            double aDouble, String aString);

}
