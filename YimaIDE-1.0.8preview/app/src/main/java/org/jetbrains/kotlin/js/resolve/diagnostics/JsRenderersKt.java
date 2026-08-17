package org.jetbrains.kotlin.js.resolve.diagnostics;

import kotlin.Metadata;
import kotlin.text.StringsKt;
import org.jetbrains.kotlin.js.parser.UtilsKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\u001a\u001a\u0010\u0000\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0003\u001a\u001a\u0010\u0005\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0003¨\u0006\u0006"}, d2 = {"underlineAsText", "", "from", "", "to", "underlineAsHtml", "org.jetbrains.kotlin:js.frontend"}, k = 2, mv = {2, 4, 0}, xi = 48)
public final class JsRenderersKt {
    public static final String underlineAsHtml(String str, int i, int i2) {
        String str2;
        str.getClass();
        StringBuilder sb = new StringBuilder();
        int length = str.length();
        boolean z = false;
        for (int i3 = 0; i3 < length; i3++) {
            char cCharAt = str.charAt(i3);
            if (i3 == i) {
                z = true;
                str2 = "<u>";
            } else if (i3 == i2) {
                z = false;
                str2 = "</u>";
            } else {
                str2 = "";
            }
            sb.append(str2);
            if (UtilsKt.isEndOfLine(cCharAt) && z) {
                sb.append("</u>" + cCharAt + "<u>");
            } else {
                sb.append(cCharAt);
            }
        }
        return sb.toString();
    }

    public static final String underlineAsText(String str, int i, int i2) {
        str.getClass();
        StringBuilder sb = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();
        int length = str.length();
        int i3 = 0;
        boolean z = false;
        while (i3 < length) {
            char cCharAt = str.charAt(i3);
            char c = (i > i3 || i3 > i2) ? ' ' : '^';
            sb.append(cCharAt);
            sb2.append(c);
            z = z || c != ' ';
            if (UtilsKt.isEndOfLine(cCharAt)) {
                if (z) {
                    sb.append(StringsKt.trimEnd(sb2.toString()).toString());
                    sb.append('\n');
                    z = false;
                }
                sb2 = new StringBuilder();
            }
            i3++;
        }
        if (z) {
            sb.append('\n');
            sb.append(sb2.toString());
        }
        return sb.toString();
    }
}
