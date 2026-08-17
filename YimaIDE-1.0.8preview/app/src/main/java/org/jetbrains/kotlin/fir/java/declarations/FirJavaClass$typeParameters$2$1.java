package org.jetbrains.kotlin.fir.java.declarations;

import java.util.concurrent.locks.ReentrantLock;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.concurrent.LocksKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.CallableReference;
import kotlin.jvm.internal.FunctionReferenceImpl;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final /* synthetic */ class FirJavaClass$typeParameters$2$1 extends FunctionReferenceImpl implements Function1<Function0<? extends Unit>, Unit> {
    public FirJavaClass$typeParameters$2$1(Object obj) {
        super(1, obj, LocksKt.class, "withLock", "withLock(Ljava/util/concurrent/locks/Lock;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", 1);
    }

    public final void invoke(Function0<Unit> function0) {
        function0.getClass();
        ReentrantLock reentrantLock = (ReentrantLock) ((CallableReference) this).receiver;
        reentrantLock.lock();
        try {
            function0.invoke();
        } finally {
            reentrantLock.unlock();
        }
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((Function0<Unit>) obj);
        return Unit.INSTANCE;
    }
}
