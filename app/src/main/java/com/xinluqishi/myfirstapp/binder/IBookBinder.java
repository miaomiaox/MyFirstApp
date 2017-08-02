package com.xinluqishi.myfirstapp.binder;

import android.os.IInterface;
import android.os.RemoteException;

import com.xinluqishi.myfirstapp.IBookManager;
import com.xinluqishi.myfirstapp.INewBookArrivedListener;
import com.xinluqishi.myfirstapp.bean.Book;

import java.util.ArrayList;
import java.util.List;

/**
 * 自己手动实现Binder
 * Created by shikeyue on 2017/7/30.
 */

public interface IBookBinder extends IInterface {

    public static final java.lang.String DESCRIPTOR = "com.xinluqishi.myfirstapp.binder.IBookBinder";

    static final int TRANSACTION_getBookList = (android.os.IBinder.FIRST_CALL_TRANSACTION + 0);
    static final int TRANSACTION_addBook = (android.os.IBinder.FIRST_CALL_TRANSACTION + 1);

    public java.util.List<com.xinluqishi.myfirstapp.bean.Book> getBookList() throws android.os.RemoteException;
    public void addBook(com.xinluqishi.myfirstapp.bean.Book book) throws android.os.RemoteException;

    List<Book> mBookList = new ArrayList<>();

    IBookManager.Stub mBinder = new IBookManager.Stub() {

        @Override
        public List<Book> getBookList() throws RemoteException {
            synchronized (mBookList) {
                return mBookList;
            }
        }

        @Override
        public void addBook(Book book) throws RemoteException {
            synchronized (mBookList) {
                if (!mBookList.contains(book)) {
                    mBookList.add(book);
                }
            }
        }

        @Override
        public void registerListener(INewBookArrivedListener listener) throws RemoteException {

        }

        @Override
        public void unregisterListener(INewBookArrivedListener listener) throws RemoteException {

        }

        @Override
        public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {

        }
    };


}
