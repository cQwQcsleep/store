package com.shadow.okio;

import com.shadow.kotlin.LazyKt;
import com.shadow.kotlin.io.CloseableKt;
import java.io.Closeable;
import kotlin.jvm.functions.Function1;

/* loaded from: /workspace/unpacked/classes2.dex */
final /* synthetic */ class Okio__OkioKt {
    public static final Sink blackhole() {
        return new BlackholeSink();
    }

    public static final BufferedSource buffer(Source source) {
        CloseableKt.checkNotNullParameter(source, "<this>");
        return new RealBufferedSource(source);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r3v1, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r3v2, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r3v3, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r3v4 */
    public static final <T extends Closeable, R> R use(T t, Function1<? super T, ? extends R> function1) {
        CloseableKt.checkNotNullParameter(function1, "block");
        R th = null;
        try {
            ?? Invoke = function1.invoke(t);
            if (t != null) {
                try {
                    t.close();
                } catch (Throwable th2) {
                    th = th2;
                }
            }
            R r = th;
            th = Invoke;
            th = r;
        } catch (Throwable th3) {
            th = th3;
            if (t != null) {
                try {
                    t.close();
                } catch (Throwable th4) {
                    LazyKt.a(th, th4);
                }
            }
        }
        if (th == 0) {
            return th;
        }
        throw th;
    }

    public static final BufferedSink buffer(Sink sink) {
        CloseableKt.checkNotNullParameter(sink, "<this>");
        return new RealBufferedSink(sink);
    }
}
