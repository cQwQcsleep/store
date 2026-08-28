package com.shadow.kotlin;

import com.shadow.kotlin.Result;
import com.shadow.kotlin.internal.PlatformImplementationsKt;
import com.shadow.kotlin.io.CloseableKt;
import com.shadow.kotlin.jvm.functions.Function0;
import com.shadow.kotlin.jvm.internal.DefaultConstructorMarker;

/* loaded from: /workspace/unpacked/classes2.dex */
public abstract class LazyKt {
    public static void a(Throwable th, Throwable th2) {
        CloseableKt.checkNotNullParameter(th, "<this>");
        CloseableKt.checkNotNullParameter(th2, "exception");
        if (th != th2) {
            PlatformImplementationsKt.IMPLEMENTATIONS.addSuppressed(th, th2);
        }
    }

    public static Lazy b(Function0 function0) {
        DefaultConstructorMarker defaultConstructorMarker = null;
        return new SynchronizedLazyImpl(function0, defaultConstructorMarker, 2, defaultConstructorMarker);
    }

    public static final void throwOnFailure(Object obj) throws Throwable {
        if (obj instanceof Result.Failure) {
            throw ((Result.Failure) obj).exception;
        }
    }
}
