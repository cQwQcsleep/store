package com.shadow.okio;

import com.shadow.kotlin.io.CloseableKt;
import com.shadow.kotlin.text.Charsets;
import java.util.concurrent.locks.ReentrantLock;
import kotlin.jvm.functions.Function0;

/* loaded from: /workspace/unpacked/classes2.dex */
public final class _JvmPlatformKt {
    public static final byte[] asUtf8ToByteArray(String str) {
        CloseableKt.checkNotNullParameter(str, "<this>");
        byte[] bytes = str.getBytes(Charsets.UTF_8);
        CloseableKt.checkNotNullExpressionValue(bytes, "getBytes(...)");
        return bytes;
    }

    public static final ReentrantLock newLock() {
        return new ReentrantLock();
    }

    public static final String toUtf8String(byte[] bArr) {
        CloseableKt.checkNotNullParameter(bArr, "<this>");
        return new String(bArr, Charsets.UTF_8);
    }

    public static final <T> T withLock(ReentrantLock reentrantLock, Function0<? extends T> function0) {
        CloseableKt.checkNotNullParameter(reentrantLock, "<this>");
        CloseableKt.checkNotNullParameter(function0, "action");
        reentrantLock.lock();
        try {
            return (T) function0.invoke();
        } finally {
            reentrantLock.unlock();
        }
    }
}
