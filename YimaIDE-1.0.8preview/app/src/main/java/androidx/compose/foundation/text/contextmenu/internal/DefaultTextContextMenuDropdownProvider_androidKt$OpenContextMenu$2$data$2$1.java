package androidx.compose.foundation.text.contextmenu.internal;

import androidx.compose.foundation.text.contextmenu.data.TextContextMenuData;
import androidx.compose.foundation.text.contextmenu.provider.TextContextMenuDataProvider;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.CallableReference;
import kotlin.jvm.internal.FunctionReferenceImpl;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
public final /* synthetic */ class DefaultTextContextMenuDropdownProvider_androidKt$OpenContextMenu$2$data$2$1 extends FunctionReferenceImpl implements Function0<TextContextMenuData> {
    public DefaultTextContextMenuDropdownProvider_androidKt$OpenContextMenu$2$data$2$1(Object obj) {
        super(0, obj, TextContextMenuDataProvider.class, "data", "data()Landroidx/compose/foundation/text/contextmenu/data/TextContextMenuData;", 0);
    }

    /* JADX INFO: renamed from: invoke, reason: merged with bridge method [inline-methods] */
    public final TextContextMenuData m1456invoke() {
        return ((TextContextMenuDataProvider) ((CallableReference) this).receiver).data();
    }
}
