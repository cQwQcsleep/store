package androidx.core.text;

import android.text.TextUtils;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import kotlin.Metadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u000e\n\u0000\u001a\r\u0010\u0000\u001a\u00020\u0001*\u00020\u0001H\u0086\b¨\u0006\u0002"}, d2 = {"htmlEncode", "", "core"}, k = 2, mv = {2, 1, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class StringKt {
    public static final String htmlEncode(String str) {
        str.getClass();
        String strHtmlEncode = TextUtils.htmlEncode(str);
        strHtmlEncode.getClass();
        return strHtmlEncode;
    }
}
