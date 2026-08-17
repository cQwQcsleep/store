package kotlin;

import defpackage.b6c;
import io.github.rosemoe.sora.widget.schemes.EditorColorScheme;
import kotlin.jvm.functions.Function0;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
@Metadata(d1 = {"\u0000.\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0001\n\u0002\b\u0002\u001a!\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u0087\u0088\u0004b\u0002\b\u0004\u0082\u0002\b\n\u0006\b\u0000\u001a\u0002\u0010\u0001\u001a2\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006H\u0087\u0088\u0004b\u0002\b\u0004ø\u0001\u0000\u0082\u0002\b\n\u0006\b\u0000\u001a\u0002\u0010\u0001\u001a8\u0010\b\u001a\u0002H\t\"\b\b\u0000\u0010\t*\u00020\u00072\b\u0010\u0002\u001a\u0004\u0018\u0001H\tH\u0087\u0088\bb\u0002\b\u0004b\u0002\b\u000b\u0082\u0002\n\n\b\b\u0000\u001a\u0004\b\u0003\u0010\u0001¢\u0006\u0002\u0010\n\u001aI\u0010\b\u001a\u0002H\t\"\b\b\u0000\u0010\t*\u00020\u00072\b\u0010\u0002\u001a\u0004\u0018\u0001H\t2\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006H\u0087\u0088\bb\u0002\b\u0004b\u0002\b\u000bø\u0001\u0000\u0082\u0002\n\n\b\b\u0000\u001a\u0004\b\u0003\u0010\u0001¢\u0006\u0002\u0010\f\u001a!\u0010\r\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u0087\u0088\u0004b\u0002\b\u0004\u0082\u0002\b\n\u0006\b\u0000\u001a\u0002\u0010\u0001\u001a2\u0010\r\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006H\u0087\u0088\u0004b\u0002\b\u0004ø\u0001\u0000\u0082\u0002\b\n\u0006\b\u0000\u001a\u0002\u0010\u0001\u001a8\u0010\u000e\u001a\u0002H\t\"\b\b\u0000\u0010\t*\u00020\u00072\b\u0010\u0002\u001a\u0004\u0018\u0001H\tH\u0087\u0088\bb\u0002\b\u0004b\u0002\b\u000b\u0082\u0002\n\n\b\b\u0000\u001a\u0004\b\u0003\u0010\u0001¢\u0006\u0002\u0010\n\u001aI\u0010\u000e\u001a\u0002H\t\"\b\b\u0000\u0010\t*\u00020\u00072\b\u0010\u0002\u001a\u0004\u0018\u0001H\t2\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006H\u0087\u0088\bb\u0002\b\u0004b\u0002\b\u000bø\u0001\u0000\u0082\u0002\n\n\b\b\u0000\u001a\u0004\b\u0003\u0010\u0001¢\u0006\u0002\u0010\f\u001a\u0016\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0007H\u0087\u0088\u0004b\u0002\b\u0004\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006\u0012"}, d2 = {"require", "", "value", "", "Lkotlin/internal/InlineOnly;", "lazyMessage", "Lkotlin/Function0;", "", "requireNotNull", "T", "(Ljava/lang/Object;)Ljava/lang/Object;", "Lkotlin/IgnorableReturnValue;", "(Ljava/lang/Object;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "check", "checkNotNull", "error", "", "message", "kotlin-stdlib"}, k = 5, mv = {2, 4, 0}, xi = EditorColorScheme.TEXT_INLAY_HINT_BACKGROUND, xs = "kotlin/PreconditionsKt")
class PreconditionsKt__PreconditionsKt extends PreconditionsKt__AssertionsJVMKt {
    private static final void check(boolean z, Function0<? extends Object> function0) {
        function0.getClass();
        if (z) {
            return;
        }
        mx5.a(function0.invoke());
    }

    @IgnorableReturnValue
    private static final <T> T checkNotNull(T t, Function0<? extends Object> function0) {
        function0.getClass();
        if (t != null) {
            return t;
        }
        mx5.a(function0.invoke());
        return null;
    }

    private static final Void error(Object obj) {
        obj.getClass();
        throw new IllegalStateException(obj.toString());
    }

    private static final void require(boolean z, Function0<? extends Object> function0) {
        function0.getClass();
        if (z) {
            return;
        }
        b6c.a(function0.invoke());
    }

    @IgnorableReturnValue
    private static final <T> T requireNotNull(T t, Function0<? extends Object> function0) {
        function0.getClass();
        if (t != null) {
            return t;
        }
        b6c.a(function0.invoke());
        return null;
    }

    private static final void check(boolean z) {
        if (z) {
            return;
        }
        k2d.a("Check failed.");
    }

    private static final void require(boolean z) {
        if (z) {
            return;
        }
        w01.a("Failed requirement.");
    }

    @IgnorableReturnValue
    private static final <T> T checkNotNull(T t) {
        if (t != null) {
            return t;
        }
        k2d.a("Required value was null.");
        return null;
    }

    @IgnorableReturnValue
    private static final <T> T requireNotNull(T t) {
        if (t != null) {
            return t;
        }
        w01.a("Required value was null.");
        return null;
    }
}
