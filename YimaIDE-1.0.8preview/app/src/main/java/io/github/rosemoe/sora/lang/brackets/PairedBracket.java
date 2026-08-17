package io.github.rosemoe.sora.lang.brackets;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public class PairedBracket {
    public final int leftIndex;
    public final int leftLength;
    public final int rightIndex;
    public final int rightLength;

    public PairedBracket(int i, int i2, int i3, int i4) {
        this.leftIndex = i;
        this.leftLength = i2;
        this.rightIndex = i3;
        this.rightLength = i4;
    }

    public PairedBracket(int i, int i2) {
        this(i, 1, i2, 1);
    }
}
