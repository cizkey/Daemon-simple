package com.revenco.daemon.utils;

import android.os.Environment;
import android.util.Log;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 2016/11/9.
 */
public class XLog {
    private static boolean isDebug = false;
    /**
     * 是否写入SDcard日志
     */
    private static boolean isWriteSDCardLog = false;
    private static String LOG_PATH;

    /**
     * 设置是否需要将日志写入SDCard
     *
     * @param iswrite 默认为false
     * @param isdebug 默认为false
     * @param logPath log 的跟目录，建议设置 StorageUtils.getDirOnData(context, "daemonLog");
     */
    public static void setLogConfig(boolean iswrite, boolean isdebug, String logPath) {
        if (!logPath.endsWith("/"))
            logPath += "/";
        isWriteSDCardLog = iswrite;
        isDebug = isdebug;
        LOG_PATH = logPath;
    }

    public static void setIsDebug(boolean isdebug) {
        isDebug = isdebug;
    }

    public static void d(String tag, String log) {
        if (isDebug)
            Log.d(tag, log);
        if (isWriteSDCardLog)
            writeLog2File(tag, log);
    }

    /**
     * 将日志写入SDCard，便宜查看追踪
     *
     * @param tag
     * @param log
     */
    public static void log2Sdcard(String tag, String log) {
        if (isDebug)
            Log.d(tag, log);
        if (isWriteSDCardLog)
            writeLog2File(tag, log);
    }

    /**
     * 文件全路径例如：/sdcard/_xlog/MainActivity_2016-12-29-20_log.txt
     *
     * @param tag tag拼接为文件名一部分
     * @param log
     */
    public static void writeLog(String tag, String log) {
        if (isDebug) {
            Log.d(tag, log);
        }
        if (isWriteSDCardLog)
            writeLog2File(tag, log);
    }

    public static void w(String tag, String log) {
        if (isDebug) {
            Log.w(tag, log);
        }
        if (isWriteSDCardLog)
            writeLog2File(tag, log);
    }

    public static void i(String tag, String log) {
        if (isDebug)
            Log.i(tag, log);
        if (isWriteSDCardLog)
            writeLog2File(tag, log);
    }

    public static void v(String tag, String log) {
        if (isDebug) {
            Log.v(tag, log);
        }
        if (isWriteSDCardLog)
            writeLog2File(tag, log);
    }

    public static void e(String tag, String log) {
        if (isDebug) {
            Log.e(tag, log);
        }
        if (isWriteSDCardLog)
            writeLog2File(tag, log);
    }

    public static void e(Throwable e, String tag, String log) {
        e.printStackTrace();
        if (isDebug) {
            Log.e(tag, log);
        }
        if (isWriteSDCardLog)
            writeLog2File(tag, log);
    }

    public static void w(Throwable e, String tag, String log) {
        e.printStackTrace();
        if (isDebug) {
            Log.w(tag, log);
        }
        if (isWriteSDCardLog)
            writeLog2File(tag, log);
    }

    public static synchronized void writeLog2File(String tag, String text) {
        if (LOG_PATH == null && LOG_PATH.isEmpty())
            return;
        try {
            text = new String(text.getBytes(), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        final String LOG_FILE_NAME = "_log.txt";
        final SimpleDateFormat contentPrefix = new SimpleDateFormat("yyyy-MM-dd-HH HH:mm:ss.sss");
        final SimpleDateFormat logFileName = new SimpleDateFormat("yyyy-MM-dd-HH");
        Date date = new Date();
        String fullPathName = LOG_PATH + logFileName.format(date).trim() + LOG_FILE_NAME;
        String content = contentPrefix.format(date).trim() + "   " + tag + "   " + text + "\r\n";
        try {
            XFile.writeFile(fullPathName, content, true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 文件全路径例如：/sdcard/_xlog/Statistics_2016-12-29_log.txt
     * 统计信息
     *
     * @param tag
     * @param text
     */
    public static void writeLog2FileWithName(String tag, String text) {
        String LOG_PATH = Environment.getExternalStorageDirectory() + "/_xlog/";
        final String LOG_FILE_NAME = "_log.txt";
        final SimpleDateFormat contentPrefix = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:sss");
        final SimpleDateFormat logFileName = new SimpleDateFormat("yyyy-MM-dd");
        Date nowtime = new Date();
        String fullPathName = LOG_PATH + "Statistics_" + logFileName.format(nowtime).trim() + LOG_FILE_NAME;
        String content = contentPrefix.format(nowtime).trim() + "   " + tag + "   " + text + "\r\n";
        try {
            XFile.writeFile(fullPathName, content, true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
