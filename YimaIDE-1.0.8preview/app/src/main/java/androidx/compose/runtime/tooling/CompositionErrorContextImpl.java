package androidx.compose.runtime.tooling;

import androidx.compose.runtime.ComposerImpl;
import androidx.compose.runtime.changelist.OperationErrorContext;
import androidx.compose.runtime.tooling.CompositionErrorContextImpl;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import java.util.List;
import kotlin.Metadata;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\u0010\u0003\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0001\u0018\u0000 \u00172\u00020\u00012\u00020\u00022\u00020\u0003:\u0001\u0017B\u000f\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u0014\u0010\b\u001a\u00020\t*\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0016J\u001d\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u0016¢\u0006\u0002\u0010\u0012R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0018\u0010\u0013\u001a\u0006\u0012\u0002\b\u00030\u00148VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u0016¨\u0006\u0018"}, d2 = {"Landroidx/compose/runtime/tooling/CompositionErrorContextImpl;", "Landroidx/compose/runtime/tooling/CompositionErrorContext;", "Landroidx/compose/runtime/changelist/OperationErrorContext;", "Lkotlin/coroutines/CoroutineContext$Element;", "composer", "Landroidx/compose/runtime/ComposerImpl;", "<init>", "(Landroidx/compose/runtime/ComposerImpl;)V", "attachComposeStackTrace", "", "", "composeNode", "", "buildStackTrace", "", "Landroidx/compose/runtime/tooling/ComposeStackTraceFrame;", "currentOffset", "", "(Ljava/lang/Integer;)Ljava/util/List;", "key", "Lkotlin/coroutines/CoroutineContext$Key;", "getKey", "()Lkotlin/coroutines/CoroutineContext$Key;", "Key", "runtime"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class CompositionErrorContextImpl implements CompositionErrorContext, OperationErrorContext, CoroutineContext.Element {
    private final ComposerImpl composer;

    /* JADX INFO: renamed from: Key, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final int $stable = 8;

    public CompositionErrorContextImpl(ComposerImpl composerImpl) {
        this.composer = composerImpl;
    }

    public static ComposeStackTrace a(CompositionErrorContextImpl compositionErrorContextImpl, Object obj) {
        return compositionErrorContextImpl.composer.stackTraceForValue$runtime(obj);
    }

    @Override // androidx.compose.runtime.tooling.CompositionErrorContext
    public boolean attachComposeStackTrace(Throwable th, final Object obj) {
        return ComposeStackTraceKt.tryAttachComposeStackTrace(th, new Function0() { // from class: no2
            public final Object invoke() {
                return CompositionErrorContextImpl.a(this.b, obj);
            }
        });
    }

    @Override // androidx.compose.runtime.changelist.OperationErrorContext
    public List<ComposeStackTraceFrame> buildStackTrace(Integer currentOffset) {
        return this.composer.parentStackTrace();
    }

    public /* bridge */ <R> R fold(R r, Function2<? super R, ? super CoroutineContext.Element, ? extends R> function2) {
        return (R) CoroutineContext.Element.DefaultImpls.fold(this, r, function2);
    }

    public /* bridge */ <E extends CoroutineContext.Element> E get(CoroutineContext.Key<E> key) {
        return (E) CoroutineContext.Element.DefaultImpls.get(this, key);
    }

    public CoroutineContext.Key<?> getKey() {
        return INSTANCE;
    }

    public /* bridge */ CoroutineContext minusKey(CoroutineContext.Key<?> key) {
        return CoroutineContext.Element.DefaultImpls.minusKey(this, key);
    }

    public /* bridge */ CoroutineContext plus(CoroutineContext coroutineContext) {
        return CoroutineContext.Element.DefaultImpls.plus(this, coroutineContext);
    }

    /* JADX INFO: renamed from: androidx.compose.runtime.tooling.CompositionErrorContextImpl$Key, reason: from kotlin metadata */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004J\b\u0010\u0005\u001a\u00020\u0006H\u0016¨\u0006\u0007"}, d2 = {"Landroidx/compose/runtime/tooling/CompositionErrorContextImpl$Key;", "Lkotlin/coroutines/CoroutineContext$Key;", "Landroidx/compose/runtime/tooling/CompositionErrorContextImpl;", "<init>", "()V", "toString", "", "runtime"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
    public static final class Companion implements CoroutineContext.Key<CompositionErrorContextImpl> {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public String toString() {
            return "CompositionErrorContext";
        }

        private Companion() {
        }
    }
}
