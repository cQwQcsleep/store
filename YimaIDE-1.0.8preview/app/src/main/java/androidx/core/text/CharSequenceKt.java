package androidx.core.text;

import android.text.TextUtils;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import kotlin.Metadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u000b\n\u0002\u0010\r\n\u0000\n\u0002\u0010\b\n\u0000\u001a\r\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u0086\b\u001a\r\u0010\u0003\u001a\u00020\u0004*\u00020\u0002H\u0086\b¨\u0006\u0005"}, d2 = {"isDigitsOnly", "", "", "trimmedLength", "", "core"}, k = 2, mv = {2, 1, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class CharSequenceKt {
    public static final boolean isDigitsOnly(CharSequence charSequence) {
        charSequence.getClass();
        return TextUtils.isDigitsOnly(charSequence);
    }

    public static final int trimmedLength(CharSequence charSequence) {
        charSequence.getClass();
        return TextUtils.getTrimmedLength(charSequence);
    }
}
