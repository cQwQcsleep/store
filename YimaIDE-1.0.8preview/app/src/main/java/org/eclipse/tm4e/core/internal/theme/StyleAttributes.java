package org.eclipse.tm4e.core.internal.theme;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
public class StyleAttributes {
    private static final StyleAttributes NO_STYLE = new StyleAttributes(-1, 0, 0);
    public final int backgroundId;
    public final int fontStyle;
    public final int foregroundId;

    private StyleAttributes(int i, int i2, int i3) {
        this.fontStyle = i;
        this.foregroundId = i2;
        this.backgroundId = i3;
    }

    public static StyleAttributes of(int i, int i2, int i3) {
        return (i == -1 && i2 == 0 && i3 == 0) ? NO_STYLE : new StyleAttributes(i, i2, i3);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof StyleAttributes) {
            StyleAttributes styleAttributes = (StyleAttributes) obj;
            if (this.backgroundId == styleAttributes.backgroundId && this.fontStyle == styleAttributes.fontStyle && this.foregroundId == styleAttributes.foregroundId) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        return ((((this.backgroundId + 31) * 31) + this.fontStyle) * 31) + this.foregroundId;
    }
}
