package org.eclipse.tm4e.core.internal.theme;

import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
public final class FontStyle {
    public static final int Bold = 2;
    public static final int Italic = 1;
    public static final int None = 0;
    public static final int NotSet = -1;
    public static final int Strikethrough = 8;
    public static final int Underline = 4;

    private FontStyle() {
    }

    public static String fontStyleToString(int i) {
        if (i == -1) {
            return "not set";
        }
        if (i == 0) {
            return AccessibilityNodeInfoCompat.MathInfoCompat.MATH_TAG_NONE_SCRIPT;
        }
        StringBuilder sb = new StringBuilder();
        if ((i & 1) == 1) {
            sb.append("italic ");
        }
        if ((i & 2) == 2) {
            sb.append("bold ");
        }
        if ((i & 4) == 4) {
            sb.append("underline ");
        }
        if ((i & 8) == 8) {
            sb.append("strikethrough ");
        }
        if (sb.length() < 1) {
            return AccessibilityNodeInfoCompat.MathInfoCompat.MATH_TAG_NONE_SCRIPT;
        }
        sb.setLength(sb.length() - 1);
        return sb.toString();
    }
}
