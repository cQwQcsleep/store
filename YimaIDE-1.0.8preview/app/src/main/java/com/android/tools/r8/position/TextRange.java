package com.android.tools.r8.position;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class TextRange implements Position {
    private final TextPosition a;
    private final TextPosition b;

    public TextRange(TextPosition textPosition, TextPosition textPosition2) {
        this.a = textPosition;
        this.b = textPosition2;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj != null && obj.getClass().equals(getClass())) {
            TextRange textRange = (TextRange) obj;
            if (this.a.equals(textRange.getStart()) && this.b.equals(textRange.getEnd())) {
                return true;
            }
        }
        return false;
    }

    @Override // com.android.tools.r8.position.Position
    public String getDescription() {
        return this.a.getDescription();
    }

    public TextPosition getEnd() {
        return this.b;
    }

    public TextPosition getStart() {
        return this.a;
    }

    public int hashCode() {
        return this.b.hashCode() ^ this.a.hashCode();
    }

    public String toString() {
        return "Text range from: '" + getStart() + "', to: '" + getEnd() + "'";
    }
}
