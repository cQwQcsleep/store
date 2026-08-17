package androidx.compose.ui.layout;

import androidx.compose.runtime.CompositionLocalKt;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0019\u0010\u0000\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u0001¢\u0006\b\n\u0000\u001a\u0004\b\u0003\u0010\u0004¨\u0006\u0005"}, d2 = {"LocalPinnableContainer", "Landroidx/compose/runtime/ProvidableCompositionLocal;", "Landroidx/compose/ui/layout/PinnableContainer;", "getLocalPinnableContainer", "()Landroidx/compose/runtime/ProvidableCompositionLocal;", "ui"}, k = 2, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class PinnableContainerKt {
    private static final ProvidableCompositionLocal<PinnableContainer> LocalPinnableContainer = CompositionLocalKt.compositionLocalOf$default(null, new Function0<PinnableContainer>() { // from class: androidx.compose.ui.layout.PinnableContainerKt$LocalPinnableContainer$1
        /* JADX INFO: renamed from: invoke, reason: merged with bridge method [inline-methods] */
        public final PinnableContainer m4666invoke() {
            return null;
        }
    }, 1, null);

    public static final ProvidableCompositionLocal<PinnableContainer> getLocalPinnableContainer() {
        return LocalPinnableContainer;
    }
}
