package com.shadow.okhttp3.internal.platform.android;

import android.util.Log;
import com.shadow.kotlin.collections.MapsKt;
import com.shadow.kotlin.io.CloseableKt;
import com.shadow.kotlin.text.StringsKt;
import com.shadow.okhttp3.OkHttpClient;
import com.shadow.okhttp3.internal.SuppressSignatureCheck;
import com.shadow.okhttp3.internal.concurrent.TaskRunner;
import com.shadow.okhttp3.internal.http2.Http2;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.logging.Level;
import java.util.logging.Logger;

@SuppressSignatureCheck
/* loaded from: /workspace/unpacked/classes2.dex */
public final class AndroidLog {
    private static final int MAX_LOG_LENGTH = 4000;
    private static final Map<String, String> knownLoggers;
    public static final AndroidLog INSTANCE = new AndroidLog();
    private static final CopyOnWriteArraySet<Logger> configuredLoggers = new CopyOnWriteArraySet<>();

    static {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        Package r2 = OkHttpClient.class.getPackage();
        String name = r2 != null ? r2.getName() : null;
        if (name != null) {
            linkedHashMap.put(name, "OkHttp");
        }
        linkedHashMap.put(OkHttpClient.class.getName(), "okhttp.OkHttpClient");
        linkedHashMap.put(Http2.class.getName(), "okhttp.Http2");
        linkedHashMap.put(TaskRunner.class.getName(), "okhttp.TaskRunner");
        linkedHashMap.put("com.shadow.okhttp3.mockwebserver.MockWebServer", "okhttp.MockWebServer");
        knownLoggers = MapsKt.c(linkedHashMap);
    }

    private AndroidLog() {
    }

    private final void enableLogging(String str, String str2) throws SecurityException {
        Logger logger = Logger.getLogger(str);
        if (configuredLoggers.add(logger)) {
            logger.setUseParentHandlers(false);
            logger.setLevel(Log.isLoggable(str2, 3) ? Level.FINE : Log.isLoggable(str2, 4) ? Level.INFO : Level.WARNING);
            logger.addHandler(AndroidLogHandler.INSTANCE);
        }
    }

    private final String loggerTag(String str) {
        String str2 = knownLoggers.get(str);
        return str2 == null ? StringsKt.u(23, str) : str2;
    }

    public final void androidLog$okhttp(String str, int i, String str2, Throwable th) {
        int iMin;
        CloseableKt.checkNotNullParameter(str, "loggerName");
        CloseableKt.checkNotNullParameter(str2, "message");
        String strLoggerTag = loggerTag(str);
        if (Log.isLoggable(strLoggerTag, i)) {
            if (th != null) {
                str2 = str2 + '\n' + Log.getStackTraceString(th);
            }
            int length = str2.length();
            int i2 = 0;
            while (i2 < length) {
                int iH = StringsKt.h(str2, '\n', i2, false, 4);
                if (iH == -1) {
                    iH = length;
                }
                while (true) {
                    iMin = Math.min(iH, i2 + MAX_LOG_LENGTH);
                    String strSubstring = str2.substring(i2, iMin);
                    CloseableKt.checkNotNullExpressionValue(strSubstring, "this as java.lang.String…ing(startIndex, endIndex)");
                    Log.println(i, strLoggerTag, strSubstring);
                    if (iMin >= iH) {
                        break;
                    } else {
                        i2 = iMin;
                    }
                }
                i2 = iMin + 1;
            }
        }
    }

    public final void enable() {
        for (Map.Entry<String, String> entry : knownLoggers.entrySet()) {
            enableLogging(entry.getKey(), entry.getValue());
        }
    }
}
