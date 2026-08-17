package androidx.core.util;

import android.util.Half;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import kotlin.Metadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\n\n\u0002\u0010\u0007\n\u0002\u0010\u0006\n\u0002\u0010\u000e\n\u0000\u001a\r\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u0087\b\u001a\r\u0010\u0000\u001a\u00020\u0001*\u00020\u0003H\u0087\b\u001a\r\u0010\u0000\u001a\u00020\u0001*\u00020\u0004H\u0087\b\u001a\r\u0010\u0000\u001a\u00020\u0001*\u00020\u0005H\u0087\b¨\u0006\u0006"}, d2 = {"toHalf", "Landroid/util/Half;", "", "", "", "", "core"}, k = 2, mv = {2, 1, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class HalfKt {
    public static final Half toHalf(String str) {
        str.getClass();
        Half halfValueOf = Half.valueOf(str);
        halfValueOf.getClass();
        return halfValueOf;
    }

    public static final Half toHalf(float f) {
        Half halfValueOf = Half.valueOf(f);
        halfValueOf.getClass();
        return halfValueOf;
    }

    public static final Half toHalf(short s) {
        Half halfValueOf = Half.valueOf(s);
        halfValueOf.getClass();
        return halfValueOf;
    }

    public static final Half toHalf(double d) {
        Half halfValueOf = Half.valueOf((float) d);
        halfValueOf.getClass();
        return halfValueOf;
    }
}
