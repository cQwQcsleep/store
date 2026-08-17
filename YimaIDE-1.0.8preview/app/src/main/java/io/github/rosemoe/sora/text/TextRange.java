package io.github.rosemoe.sora.text;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public class TextRange {
    private CharPosition end;
    private CharPosition start;

    public TextRange(CharPosition charPosition, CharPosition charPosition2) {
        this.start = charPosition;
        this.end = charPosition2;
    }

    public CharPosition getEnd() {
        return this.end;
    }

    public int getEndIndex() {
        return this.end.index;
    }

    public CharPosition getStart() {
        return this.start;
    }

    public int getStartIndex() {
        return this.start.index;
    }

    public boolean isPositionInside(CharPosition charPosition) {
        int i = charPosition.index;
        return i >= this.start.index && i < this.end.index;
    }

    public void setEnd(CharPosition charPosition) {
        this.end = charPosition;
    }

    public void setStart(CharPosition charPosition) {
        this.start = charPosition;
    }

    public String toString() {
        return "TextRange{start=" + this.start + ", end=" + this.end + '}';
    }
}
