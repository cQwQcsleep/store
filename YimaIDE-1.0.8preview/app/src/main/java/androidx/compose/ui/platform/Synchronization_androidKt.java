package androidx.compose.ui.platform;

import androidx.compose.ui.tooling.preview.AndroidUiModes;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.InlineMarker;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u0015\u0010\u0002\u001a\u00020\u00012\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u0001H\u0080\b\u001a;\u0010\u0004\u001a\u0002H\u0005\"\u0004\b\u0000\u0010\u00052\n\u0010\u0006\u001a\u00060\u0001j\u0002`\u00072\f\u0010\b\u001a\b\u0012\u0004\u0012\u0002H\u00050\tH\u0080\b\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0002 \u0001¢\u0006\u0002\u0010\n*\f\b\u0000\u0010\u0000\"\u00020\u00012\u00020\u0001¨\u0006\u000b"}, d2 = {"SynchronizedObject", "", "makeSynchronizedObject", "ref", "synchronized", "R", "lock", "Landroidx/compose/ui/platform/SynchronizedObject;", "block", "Lkotlin/Function0;", "(Ljava/lang/Object;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "ui"}, k = 2, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class Synchronization_androidKt {
    public static final Object makeSynchronizedObject(Object obj) {
        return obj == null ? new Object() : obj;
    }

    public static /* synthetic */ Object makeSynchronizedObject$default(Object obj, int i, Object obj2) {
        if ((i & 1) != 0) {
            obj = null;
        }
        return obj == null ? new Object() : obj;
    }

    /* JADX INFO: renamed from: synchronized, reason: not valid java name */
    public static final <R> R m5214synchronized(Object obj, Function0<? extends R> function0) {
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
