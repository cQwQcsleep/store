package androidx.compose.compiler.plugins.kotlin.lower;

import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import org.jetbrains.kotlin.utils.exceptions.PlatformExceptionUtilsKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b\u0002\u0018\u0000*\u0006\b\u0000\u0010\u0001 \u00012\u00020\u0002B\u0015\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004¢\u0006\u0004\b\u0005\u0010\u0006J\u0013\u0010\t\u001a\u00028\u00002\u0006\u0010\n\u001a\u00020\u000b¢\u0006\u0002\u0010\fR\u0010\u0010\u0007\u001a\u0004\u0018\u00010\u0002X\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\b\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Landroidx/compose/compiler/plugins/kotlin/lower/GuardedLazy;", "T", "", "initializer", "Lkotlin/Function0;", "<init>", "(Lkotlin/jvm/functions/Function0;)V", "_value", "_initializer", "value", "name", "", "(Ljava/lang/String;)Ljava/lang/Object;", "org.jetbrains.kotlin:kotlin-compose-compiler-plugin"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final class GuardedLazy<T> {
    private Function0<? extends T> _initializer;
    private Object _value;

    public GuardedLazy(Function0<? extends T> function0) {
        function0.getClass();
        this._value = UNINITIALIZED_VALUE.INSTANCE;
        this._initializer = function0;
    }

    public final T value(String name) throws Throwable {
        name.getClass();
        if (this._value == UNINITIALIZED_VALUE.INSTANCE) {
            try {
                Function0<? extends T> function0 = this._initializer;
                function0.getClass();
                this._value = function0.invoke();
                this._initializer = null;
            } catch (Throwable th) {
                PlatformExceptionUtilsKt.rethrowIntellijPlatformExceptionIfNeeded(th);
                throw new IllegalStateException("Error initializing " + name, th);
            }
        }
        return (T) this._value;
    }
}
