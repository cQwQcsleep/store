package androidx.compose.foundation.text;

import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.SolidColor;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\bÂ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Landroidx/compose/foundation/text/BasicTextFieldDefaults;", "", "<init>", "()V", "CursorBrush", "Landroidx/compose/ui/graphics/SolidColor;", "getCursorBrush", "()Landroidx/compose/ui/graphics/SolidColor;", "foundation"}, k = 1, mv = {2, 0, 0}, xi = 48)
final class BasicTextFieldDefaults {
    public static final BasicTextFieldDefaults INSTANCE = new BasicTextFieldDefaults();
    private static final SolidColor CursorBrush = new SolidColor(Color.Companion.getBlack-0d7_KjU(), (DefaultConstructorMarker) null);

    private BasicTextFieldDefaults() {
    }

    public final SolidColor getCursorBrush() {
        return CursorBrush;
    }
}
