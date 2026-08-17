package androidx.compose.foundation.text.contextmenu.provider;

import androidx.compose.foundation.text.contextmenu.provider.TextContextMenuProviderKt;
import androidx.compose.runtime.CompositionLocalKt;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.SnapshotMutationPolicy;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\"\u0019\u0010\u0000\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u0001¢\u0006\b\n\u0000\u001a\u0004\b\u0003\u0010\u0004\"\u0019\u0010\u0005\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u0001¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0004¨\u0006\u0007"}, d2 = {"LocalTextContextMenuDropdownProvider", "Landroidx/compose/runtime/ProvidableCompositionLocal;", "Landroidx/compose/foundation/text/contextmenu/provider/TextContextMenuProvider;", "getLocalTextContextMenuDropdownProvider", "()Landroidx/compose/runtime/ProvidableCompositionLocal;", "LocalTextContextMenuToolbarProvider", "getLocalTextContextMenuToolbarProvider", "foundation"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class TextContextMenuProviderKt {
    private static final ProvidableCompositionLocal<TextContextMenuProvider> LocalTextContextMenuDropdownProvider = CompositionLocalKt.compositionLocalOf$default((SnapshotMutationPolicy) null, new Function0() { // from class: b5e
        public final Object invoke() {
            return TextContextMenuProviderKt.a();
        }
    }, 1, (Object) null);
    private static final ProvidableCompositionLocal<TextContextMenuProvider> LocalTextContextMenuToolbarProvider = CompositionLocalKt.compositionLocalOf$default((SnapshotMutationPolicy) null, new Function0() { // from class: c5e
        public final Object invoke() {
            return TextContextMenuProviderKt.b();
        }
    }, 1, (Object) null);

    public static TextContextMenuProvider a() {
        return null;
    }

    public static TextContextMenuProvider b() {
        return null;
    }

    public static final ProvidableCompositionLocal<TextContextMenuProvider> getLocalTextContextMenuDropdownProvider() {
        return LocalTextContextMenuDropdownProvider;
    }

    public static final ProvidableCompositionLocal<TextContextMenuProvider> getLocalTextContextMenuToolbarProvider() {
        return LocalTextContextMenuToolbarProvider;
    }
}
