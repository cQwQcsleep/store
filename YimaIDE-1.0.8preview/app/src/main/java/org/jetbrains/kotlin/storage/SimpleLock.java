package org.jetbrains.kotlin.storage;

import java.util.concurrent.locks.Lock;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.joni.constants.internal.OPCode;

/* JADX INFO: loaded from: /workspace/dex_all/classes3.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\bf\u0018\u0000 \u00052\u00020\u0001:\u0001\u0005J\b\u0010\u0002\u001a\u00020\u0003H&J\b\u0010\u0004\u001a\u00020\u0003H&ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0006À\u0006\u0001"}, d2 = {"Lorg/jetbrains/kotlin/storage/SimpleLock;", "", "lock", "", "unlock", "Companion", "org.jetbrains.kotlin:util.runtime"}, k = 1, mv = {2, 2, 0}, xi = OPCode.BACKREFN)
public interface SimpleLock {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = Companion.$$INSTANCE;

    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J*\u0010\u0004\u001a\u00020\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\u0016\b\u0002\u0010\b\u001a\u0010\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010\t¨\u0006\f"}, d2 = {"Lorg/jetbrains/kotlin/storage/SimpleLock$Companion;", "", "<init>", "()V", "simpleLock", "Lorg/jetbrains/kotlin/storage/DefaultSimpleLock;", "checkCancelled", "Ljava/lang/Runnable;", "interruptedExceptionHandler", "Lkotlin/Function1;", "Ljava/lang/InterruptedException;", "", "org.jetbrains.kotlin:util.runtime"}, k = 1, mv = {2, 2, 0}, xi = OPCode.BACKREFN)
    public static final class Companion {
        static final /* synthetic */ Companion $$INSTANCE = new Companion();

        private Companion() {
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ DefaultSimpleLock simpleLock$default(Companion companion, Runnable runnable, Function1 function1, int i, Object obj) {
            if ((i & 1) != 0) {
                runnable = null;
            }
            if ((i & 2) != 0) {
                function1 = null;
            }
            return companion.simpleLock(runnable, function1);
        }

        public final DefaultSimpleLock simpleLock(Runnable checkCancelled, Function1<? super InterruptedException, Unit> interruptedExceptionHandler) {
            return (checkCancelled == null || interruptedExceptionHandler == null) ? new DefaultSimpleLock((Lock) null, 1, (DefaultConstructorMarker) null) : new CancellableSimpleLock(checkCancelled, interruptedExceptionHandler);
        }
    }

    void lock();

    void unlock();
}
