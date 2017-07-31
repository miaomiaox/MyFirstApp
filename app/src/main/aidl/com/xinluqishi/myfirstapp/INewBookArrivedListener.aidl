// INewBookArrivedListener.aidl
package com.xinluqishi.myfirstapp;

import com.xinluqishi.myfirstapp.bean.Book;

// Declare any non-default types here with import statements

interface INewBookArrivedListener {

    void onNewBookArrived(in Book newBook);

    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat,
            double aDouble, String aString);
}
