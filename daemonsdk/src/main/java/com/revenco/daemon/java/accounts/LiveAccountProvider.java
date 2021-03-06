package com.revenco.daemon.java.accounts;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.Nullable;

import com.revenco.daemon.utils.XLog;

/**
 * <p>PROJECT : Daemon-simple</p>
 * <p>COMPANY : wanzhong</p>
 * <p>AUTHOR : Administrator on 2017-05-26 12:36.</p>
 * <p>CLASS DESCRIBE :账户提供者，空实现即可</p>
 * <p>CLASS_VERSION : 1.0.0</p>
 */
public class LiveAccountProvider extends ContentProvider {
    /**
     * 账户同步的身份认证提供者
     */
    public static final String AUTHORITY = "com.revenco.daemonsdk.provider";
    public static final String CONTENT_URI_BASE = "content://" + AUTHORITY;
    public static final String TABLE_NAME = "data";
    public static final Uri CONTENT_URI = Uri.parse(CONTENT_URI_BASE + "/" + TABLE_NAME);
    private static final String TAG = Debugger.TAG;

    @Override
    public boolean onCreate() {
        return false;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        XLog.d(TAG, "query() called ");
        return null;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        XLog.d(TAG, "getType() called ");
        return new String();
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        XLog.d(TAG, "insert() called ");
        return null;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        XLog.d(TAG, "delete() called ");
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        XLog.d(TAG, "update() called ");
        return 0;
    }
}
