package androidx.compose.ui.text.platform;

import androidx.compose.ui.tooling.preview.AndroidUiModes;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.InlineMarker;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u0015\u0010\u0000\u001a\u00020\u00012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003H\u0080\b\u001a7\u0010\u0004\u001a\u0002H\u0005\"\u0004\b\u0000\u0010\u00052\u0006\u0010\u0006\u001a\u00020\u00012\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u0002H\u00050\bH\u0081\b\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0002 \u0001¢\u0006\u0002\u0010\t¨\u0006\n"}, d2 = {"makeSynchronizedObject", "Landroidx/compose/ui/text/platform/SynchronizedObject;", "ref", "", "synchronized", "R", "lock", "block", "Lkotlin/Function0;", "(Landroidx/compose/ui/text/platform/SynchronizedObject;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "ui-text"}, k = 2, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class Synchronization_jvmKt {
    public static final SynchronizedObject makeSynchronizedObject(Object obj) {
        return new SynchronizedObject();
    }

    public static /* synthetic */ SynchronizedObject makeSynchronizedObject$default(Object obj, int i, Object obj2) {
        return new SynchronizedObject();
    }

    /* JADX INFO: renamed from: synchronized, reason: not valid java name */
    public static final <R> R m5741synchronized(SynchronizedObject synchronizedObject, Function0<? extends R> function0) {
        R r;
        synchronized (synchronizedObject) {
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
