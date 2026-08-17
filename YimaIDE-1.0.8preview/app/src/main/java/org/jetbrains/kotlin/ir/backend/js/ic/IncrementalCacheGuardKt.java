package org.jetbrains.kotlin.ir.backend.js.ic;

import java.io.IOException;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a/\u0010\u0000\u001a\u0002H\u0001\"\u0004\b\u0000\u0010\u0001*\u00020\u00022\u0012\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u0002H\u00010\u0004H\u0086\bø\u0001\u0000¢\u0006\u0002\u0010\u0006\u001a)\u0010\u0007\u001a\u0002H\u0001\"\u0004\b\u0000\u0010\u0001*\u00020\u00022\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u0002H\u00010\bH\u0086\bø\u0001\u0000¢\u0006\u0002\u0010\t\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006\n"}, d2 = {"acquireAndRelease", "R", "Lorg/jetbrains/kotlin/ir/backend/js/ic/IncrementalCacheGuard;", "block", "Lkotlin/Function1;", "Lorg/jetbrains/kotlin/ir/backend/js/ic/IncrementalCacheGuard$AcquireStatus;", "(Lorg/jetbrains/kotlin/ir/backend/js/ic/IncrementalCacheGuard;Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "tryAcquireAndRelease", "Lkotlin/Function0;", "(Lorg/jetbrains/kotlin/ir/backend/js/ic/IncrementalCacheGuard;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "org.jetbrains.kotlin:backend.js"}, k = 2, mv = {2, 4, 0}, xi = 48)
public final class IncrementalCacheGuardKt {
    public static final <R> R acquireAndRelease(IncrementalCacheGuard incrementalCacheGuard, Function1<? super IncrementalCacheGuard.AcquireStatus, ? extends R> function1) {
        incrementalCacheGuard.getClass();
        function1.getClass();
        R r = (R) function1.invoke(incrementalCacheGuard.acquire());
        incrementalCacheGuard.release();
        return r;
    }

    public static final <R> R tryAcquireAndRelease(IncrementalCacheGuard incrementalCacheGuard, Function0<? extends R> function0) throws IOException {
        incrementalCacheGuard.getClass();
        function0.getClass();
        incrementalCacheGuard.tryAcquire();
        R r = (R) function0.invoke();
        incrementalCacheGuard.release();
        return r;
    }
}
