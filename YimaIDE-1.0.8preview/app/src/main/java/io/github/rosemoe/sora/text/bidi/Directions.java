package io.github.rosemoe.sora.text.bidi;

import io.github.rosemoe.sora.util.IntPair;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public class Directions implements IDirections {
    private int length;
    private long[] runs;

    public Directions(long[] jArr, int i) {
        this.runs = jArr;
        this.length = i;
    }

    public int getLength() {
        return this.length;
    }

    @Override // io.github.rosemoe.sora.text.bidi.IDirections
    public int getRunCount() {
        return this.runs.length;
    }

    @Override // io.github.rosemoe.sora.text.bidi.IDirections
    public int getRunEnd(int i) {
        return i == this.runs.length + (-1) ? this.length : getRunStart(i + 1);
    }

    @Override // io.github.rosemoe.sora.text.bidi.IDirections
    public int getRunLevel(int i) {
        return IntPair.getSecond(this.runs[i]);
    }

    @Override // io.github.rosemoe.sora.text.bidi.IDirections
    public int getRunStart(int i) {
        return IntPair.getFirst(this.runs[i]);
    }

    @Override // io.github.rosemoe.sora.text.bidi.IDirections
    public boolean isRunRtl(int i) {
        return (getRunLevel(i) & 1) == 1;
    }

    public void setData(long[] jArr, int i) {
        this.runs = jArr;
        this.length = i;
    }

    public void setLength(int i) {
        this.length = i;
    }
}
