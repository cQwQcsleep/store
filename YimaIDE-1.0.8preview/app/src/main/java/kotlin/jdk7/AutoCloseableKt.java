package kotlin.jdk7;

import io.github.rosemoe.sora.widget.schemes.EditorColorScheme;
import kotlin.ExceptionsKt;
import kotlin.IgnorableReturnValue;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.InlineMarker;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
@Metadata(d1 = {"\u0000>\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0002\u0018\u0002\n\u0000\u001a3\u0010\u0000\u001a\u00060\u0001j\u0002`\u00052\u000e\b\u0004\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007H\u0087\u0088\u0004b\f\b\u0002\u0012\b\b\u0003\u0012\u0004\b\b(\u0004b\u0002\b\tø\u0001\u0000\u001ae\u0010\n\u001a\u0002H\u000b\"\u0010\b\u0000\u0010\f*\n\u0018\u00010\u0001j\u0004\u0018\u0001`\u0005\"\u0004\b\u0001\u0010\u000b*\u0002H\f2\u0012\u0010\r\u001a\u000e\u0012\u0004\u0012\u0002H\f\u0012\u0004\u0012\u0002H\u000b0\u000eH\u0087\u0088\bb\f\b\u0002\u0012\b\b\u0003\u0012\u0004\b\b(\u0010b\u0002\b\tb\u0002\b\u0011ø\u0001\u0000\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0001 \u0001¢\u0006\u0002\u0010\u000f\u001a2\u0010\u0012\u001a\u00020\b*\n\u0018\u00010\u0001j\u0004\u0018\u0001`\u00052\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014H\u0081\u0080\u0004b\f\b\u0002\u0012\b\b\u0003\u0012\u0004\b\b(\u0010b\u0002\b\u0015*\u001a\b\u0007\u0010\u0000\"\u00020\u00012\u00020\u0001B\f\b\u0002\u0012\b\b\u0003\u0012\u0004\b\b(\u0004\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006\u0016"}, d2 = {"AutoCloseable", "Ljava/lang/AutoCloseable;", "Lkotlin/SinceKotlin;", "version", "2.0", "Lkotlin/AutoCloseable;", "closeAction", "Lkotlin/Function0;", "", "Lkotlin/internal/InlineOnly;", "use", "R", "T", "block", "Lkotlin/Function1;", "(Ljava/lang/AutoCloseable;Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "1.2", "Lkotlin/IgnorableReturnValue;", "closeFinally", "cause", "", "Lkotlin/PublishedApi;", "kotlin-stdlib"}, k = 2, mv = {2, 4, 0}, pn = "kotlin", xi = EditorColorScheme.SNIPPET_BACKGROUND_EDITING)
public final class AutoCloseableKt {
    private static final AutoCloseable AutoCloseable(final Function0<Unit> function0) {
        function0.getClass();
        return new AutoCloseable() { // from class: kotlin.jdk7.AutoCloseableKt.AutoCloseable.1
            @Override // java.lang.AutoCloseable
            public final void close() {
                function0.invoke();
            }
        };
    }

    public static /* synthetic */ void AutoCloseable$annotations() {
    }

    public static final void closeFinally(AutoCloseable autoCloseable, Throwable th) {
        if (autoCloseable != null) {
            if (th == null) {
                hv3.a(autoCloseable);
                return;
            }
            try {
                hv3.a(autoCloseable);
            } catch (Throwable th2) {
                ExceptionsKt.addSuppressed(th, th2);
            }
        }
    }

    @IgnorableReturnValue
    private static final <T extends AutoCloseable, R> R use(T t, Function1<? super T, ? extends R> function1) {
        function1.getClass();
        try {
            R rInvoke = function1.invoke(t);
            InlineMarker.finallyStart(1);
            closeFinally(t, null);
            InlineMarker.finallyEnd(1);
            return rInvoke;
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                InlineMarker.finallyStart(1);
                closeFinally(t, th);
                InlineMarker.finallyEnd(1);
                throw th2;
            }
        }
    }
}
