package androidx.compose.runtime;

import androidx.compose.ui.tooling.preview.AndroidUiModes;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.InlineMarker;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000\u0016\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a0\u0010\u0000\u001a\u0002H\u0001\"\u0004\b\u0000\u0010\u00012\n\u0010\u0002\u001a\u00060\u0003j\u0002`\u00042\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u0002H\u00010\u0006H\u0081\b¢\u0006\u0004\b\u0007\u0010\b¨\u0006\t"}, d2 = {"oldSynchronized", "R", "lock", "", "Landroidx/compose/runtime/platform/SynchronizedObject;", "block", "Lkotlin/Function0;", "synchronized", "(Ljava/lang/Object;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "runtime"}, k = 5, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK, xs = "androidx/compose/runtime/ActualJvm_jvmKt")
final /* synthetic */ class ActualJvm_jvmKt__OldSynchronization_jvmKt {
    @Deprecated(level = DeprecationLevel.HIDDEN, message = "not expected to be referenced directly as the old version had to be inlined")
    /* JADX INFO: renamed from: synchronized, reason: not valid java name */
    public static final /* synthetic */ <R> R m2322synchronized(Object obj, Function0<? extends R> function0) {
        R r;
        synchronized (obj) {
            try {
                r = (R) function0.invoke();
                InlineMarker.finallyStart(1);
            } finally {
                InlineMarker.finallyStart(1);
                InlineMarker.finallyEnd(1);
            }
        }
        return r;
    }
}
