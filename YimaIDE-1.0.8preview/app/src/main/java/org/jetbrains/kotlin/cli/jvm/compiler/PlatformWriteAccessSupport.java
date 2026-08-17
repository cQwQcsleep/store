package org.jetbrains.kotlin.cli.jvm.compiler;

import java.util.function.Supplier;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.InlineMarker;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\bÂ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0006\u0010\u0007\u001a\u00020\u0006J%\u0010\b\u001a\u0002H\t\"\u0004\b\u0000\u0010\t2\f\u0010\n\u001a\b\u0012\u0004\u0012\u0002H\t0\u000bH\u0086\bø\u0001\u0000¢\u0006\u0002\u0010\fR\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006\r"}, d2 = {"Lorg/jetbrains/kotlin/cli/jvm/compiler/PlatformWriteAccessSupport;", Argument.Delimiters.none, "<init>", "()V", "isWriteAccessAllowedInThread", "Ljava/lang/ThreadLocal;", Argument.Delimiters.none, "isWriteAccessAllowed", "withWriteAccessAllowedInThread", "A", "action", "Lkotlin/Function0;", "(Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "org.jetbrains.kotlin:cli-base"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
final class PlatformWriteAccessSupport {
    public static final PlatformWriteAccessSupport INSTANCE = new PlatformWriteAccessSupport();
    private static final ThreadLocal<Boolean> isWriteAccessAllowedInThread;

    static {
        ThreadLocal<Boolean> threadLocalWithInitial = ThreadLocal.withInitial(new Supplier() { // from class: org.jetbrains.kotlin.cli.jvm.compiler.d
            @Override // java.util.function.Supplier
            public final Object get() {
                return PlatformWriteAccessSupport.a();
            }
        });
        threadLocalWithInitial.getClass();
        isWriteAccessAllowedInThread = threadLocalWithInitial;
    }

    private PlatformWriteAccessSupport() {
    }

    public static Boolean a() {
        return Boolean.FALSE;
    }

    public final boolean isWriteAccessAllowed() {
        Boolean bool = isWriteAccessAllowedInThread.get();
        bool.getClass();
        return bool.booleanValue();
    }

    public final <A> A withWriteAccessAllowedInThread(Function0<? extends A> action) {
        action.getClass();
        isWriteAccessAllowedInThread.set(Boolean.TRUE);
        try {
            return (A) action.invoke();
        } finally {
            InlineMarker.finallyStart(1);
            isWriteAccessAllowedInThread.set(Boolean.FALSE);
            InlineMarker.finallyEnd(1);
        }
    }
}
