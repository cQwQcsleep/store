package androidx.compose.ui;

import androidx.compose.ui.tooling.preview.AndroidUiModes;
import kotlin.Metadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\u001a\u0014\u0010\u0000\u001a\u00020\u0001*\u00020\u00012\b\b\u0002\u0010\u0002\u001a\u00020\u0003¨\u0006\u0004"}, d2 = {"sensitiveContent", "Landroidx/compose/ui/Modifier;", "isContentSensitive", "", "ui"}, k = 2, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class SensitiveContentKt {
    public static final Modifier sensitiveContent(Modifier modifier, boolean z) {
        return modifier.then(new SensitiveNodeElement(z));
    }

    public static /* synthetic */ Modifier sensitiveContent$default(Modifier modifier, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            z = true;
        }
        return sensitiveContent(modifier, z);
    }
}
