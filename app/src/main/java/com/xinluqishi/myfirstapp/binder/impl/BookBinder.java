package com.xinluqishi.myfirstapp.binder.impl;

import android.os.Binder;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

import com.xinluqishi.myfirstapp.bean.Book;
import com.xinluqishi.myfirstapp.binder.IBookBinder;

import java.util.List;

/**
 * 自己手动实现Binder
 * Created by shikeyue on 2017/7/30.
 */

public class BookBinder extends Binder implements IBookBinder {

    public BookBinder() {
        this.attachInterface(this, DESCRIPTOR);
    }

//    public static IBookBinder asInterface(android.os.IBinder obj)
//    {
//        if ((obj==null)) {
//            return null;
//        }
//        android.os.IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
//        if (((iin!=null)&&(iin instanceof IBookBinder))) {
//            return ((IBookBinder)iin);
//        }
//        return new BookBinder.Proxy(obj);
//    }

    @Override
    public IBinder asBinder() {
        return this;
    }

    @Override
    protected boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {

        switch (code) {
            case INTERFACE_TRANSACTION: {
                reply.writeString(DESCRIPTOR);
                return true;
            }
            case TRANSACTION_getBookList: {
                data.enforceInterface(DESCRIPTOR);
            }
        }

        return false;
    }

    @Override
    public List<Book> getBookList() throws RemoteException {
        return null;
    }

    @Override
    public void addBook(Book book) throws RemoteException {

    }


}
