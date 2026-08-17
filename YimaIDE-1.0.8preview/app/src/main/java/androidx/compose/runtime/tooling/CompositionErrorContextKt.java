package androidx.compose.runtime.tooling;

import androidx.compose.runtime.CompositionLocal;
import androidx.compose.runtime.CompositionLocalKt;
import androidx.compose.runtime.tooling.CompositionErrorContextKt;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0019\u0010\u0000\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u0001¢\u0006\b\n\u0000\u001a\u0004\b\u0003\u0010\u0004¨\u0006\u0005"}, d2 = {"LocalCompositionErrorContext", "Landroidx/compose/runtime/CompositionLocal;", "Landroidx/compose/runtime/tooling/CompositionErrorContext;", "getLocalCompositionErrorContext", "()Landroidx/compose/runtime/CompositionLocal;", "runtime"}, k = 2, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class CompositionErrorContextKt {
    private static final CompositionLocal<CompositionErrorContext> LocalCompositionErrorContext = CompositionLocalKt.staticCompositionLocalOf(new Function0() { // from class: oo2
        public final Object invoke() {
            return CompositionErrorContextKt.a();
        }
    });

    public static CompositionErrorContext a() {
        return null;
    }

    public static final CompositionLocal<CompositionErrorContext> getLocalCompositionErrorContext() {
        return LocalCompositionErrorContext;
    }
}
