package androidx.compose.ui.autofill;

import androidx.compose.ui.tooling.preview.AndroidUiModes;
import kotlin.Metadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\u001a\u000e\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003\"\u0015\u0010\u0002\u001a\u00020\u0003*\u00020\u00018F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"ContentDataType", "Landroidx/compose/ui/autofill/ContentDataType;", "dataType", "", "getDataType", "(Landroidx/compose/ui/autofill/ContentDataType;)I", "ui"}, k = 2, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class ContentDataType_androidKt {
    public static final ContentDataType ContentDataType(int i) {
        return AndroidContentDataType.m2620boximpl(AndroidContentDataType.m2621constructorimpl(i));
    }

    public static final int getDataType(ContentDataType contentDataType) {
        contentDataType.getClass();
        return ((AndroidContentDataType) contentDataType).m2626unboximpl();
    }
}
