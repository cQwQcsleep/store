package kotlin;

import io.github.rosemoe.sora.widget.schemes.EditorColorScheme;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
@Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0001\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\b7\u0018\u0000*\u0004\b\u0000\u0010\u0001*\u0004\b\u0001\u0010\u00022\u00020\u0003B\t\bD¢\u0006\u0004\b\u0004\u0010\u0005J\u0017\u0010\u0006\u001a\u00028\u00012\u0006\u0010\u0007\u001a\u00028\u0000H¦À\u0004¢\u0006\u0002\u0010\bJ3\u0010\u0006\u001a\u0002H\t\"\u0004\b\u0002\u0010\n\"\u0004\b\u0003\u0010\t*\u000e\u0012\u0004\u0012\u0002H\n\u0012\u0004\u0012\u0002H\t0\u000b2\u0006\u0010\u0007\u001a\u0002H\nH¦À\u0004¢\u0006\u0002\u0010\fJX\u0010\r\u001a\u00020\u000e*\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u000b2\b\u0010\u0007\u001a\u0004\u0018\u00010\u0003H\u0087\u0082\u0004b6\b\u000f\u0012\n\b\u0010\u0012\u0006\b\n0\u00118\u0012\u0012\b\b\u0013\u0012\u0004\b\b(\u0014\u0012\u001c\b\u0015\u0012\u0018\b\u000bB\u0014\b\u0016\u0012\b\b\u0017\u0012\u0004\b\b(\u0018\u0012\u0006\b\u0019\u0012\u0002\b\f\u0082\u0001\u0001\u001aÊ\u0001\u0002\b\u001cÊ\u0001\f\b\u001d\u0012\b\b\u001e\u0012\u0004\b\b(\u001fÊ\u0001\u0010\b \u0012\f\b!\u0012\b\b\fJ\u0004\b\t0\"¨\u0006\u001b"}, d2 = {"Lkotlin/DeepRecursiveScope;", "T", "R", "", "<init>", "()V", "callRecursive", "value", "(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "S", "U", "Lkotlin/DeepRecursiveFunction;", "(Lkotlin/DeepRecursiveFunction;Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "invoke", "", "Lkotlin/Deprecated;", "level", "Lkotlin/DeprecationLevel;", "ERROR", "message", "'invoke' should not be called from DeepRecursiveScope. Use 'callRecursive' to do recursion in the heap instead of the call stack.", "replaceWith", "Lkotlin/ReplaceWith;", "expression", "this.callRecursive(value)", "imports", "Lkotlin/DeepRecursiveScopeImpl;", "kotlin-stdlib", "Lkotlin/coroutines/RestrictsSuspension;", "Lkotlin/SinceKotlin;", "version", "1.7", "Lkotlin/WasExperimental;", "markerClass", "Lkotlin/ExperimentalStdlibApi;"}, k = 1, mv = {2, 4, 0}, xi = EditorColorScheme.SNIPPET_BACKGROUND_EDITING)
public abstract class DeepRecursiveScope<T, R> {
    public /* synthetic */ DeepRecursiveScope(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    public abstract Object callRecursive(T t, Continuation<? super R> continuation);

    public abstract <U, S> Object callRecursive(DeepRecursiveFunction<U, S> deepRecursiveFunction, U u, Continuation<? super S> continuation);

    @Deprecated(level = DeprecationLevel.ERROR, message = "'invoke' should not be called from DeepRecursiveScope. Use 'callRecursive' to do recursion in the heap instead of the call stack.", replaceWith = @ReplaceWith(expression = "this.callRecursive(value)", imports = {}))
    public final Void invoke(DeepRecursiveFunction<?, ?> deepRecursiveFunction, Object obj) {
        deepRecursiveFunction.getClass();
        throw new UnsupportedOperationException("Should not be called from DeepRecursiveScope");
    }

    private DeepRecursiveScope() {
    }
}
