package org.jetbrains.kotlin.utils;

import java.io.Closeable;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.joni.constants.internal.OPCode;

/* JADX INFO: loaded from: /workspace/dex_all/classes3.dex */
@Metadata(d1 = {"\u0000$\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\u001a\u0012\u0010\u0000\u001a\u00060\u0001j\u0002`\u00022\u0006\u0010\u0003\u001a\u00020\u0004\u001a\u0010\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\b\u001a\n\u0010\t\u001a\u00020\n*\u00020\u0004¨\u0006\u000b"}, d2 = {"rethrow", "Ljava/lang/RuntimeException;", "Lkotlin/RuntimeException;", "e", "", "closeQuietly", "", "closeable", "Ljava/io/Closeable;", "isProcessCanceledException", "", "org.jetbrains.kotlin:util.runtime"}, k = 2, mv = {2, 2, 0}, xi = OPCode.BACKREFN)
public final class ExceptionUtilsKt {
    public static final void closeQuietly(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (Throwable unused) {
            }
        }
    }

    public static final boolean isProcessCanceledException(Throwable th) {
        th.getClass();
        Class<?> superclass = th.getClass();
        while (!Intrinsics.areEqual(superclass.getCanonicalName(), "com.intellij.openapi.progress.ProcessCanceledException")) {
            superclass = superclass.getSuperclass();
            if (superclass == null) {
                return false;
            }
        }
        return true;
    }

    public static final RuntimeException rethrow(Throwable th) throws Throwable {
        th.getClass();
        throw th;
    }
}
