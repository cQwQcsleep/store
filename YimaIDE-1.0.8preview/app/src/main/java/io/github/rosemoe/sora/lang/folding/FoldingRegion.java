package io.github.rosemoe.sora.lang.folding;

import io.github.rosemoe.sora.util.IntPair;
import java.util.List;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public class FoldingRegion {
    private List<FoldingRegion> children;
    private boolean collapsed;
    private long end;
    private long start;

    public FoldingRegion(int i, int i2, int i3, int i4) {
        this(IntPair.pack(i, i2), IntPair.pack(i3, i4));
        if (i > i3 || (i == i3 && i2 > i4)) {
            w01.a("start > end");
            throw null;
        }
    }

    public FoldingRegion createChild(int i, int i2, int i3, int i4) {
        if (i < getStartLine() || (i == getStartLine() && i2 < getStartColumn())) {
            w01.a("child start is before parent start");
            return null;
        }
        if (i3 > getEndLine() || (i3 == getEndLine() && i4 > getEndColumn())) {
            w01.a("child end is beyond parent end");
            return null;
        }
        FoldingRegion foldingRegion = new FoldingRegion(i, i2, i3, i4);
        this.children.add(foldingRegion);
        return foldingRegion;
    }

    public int getEndColumn() {
        return IntPair.getSecond(this.end);
    }

    public int getEndLine() {
        return IntPair.getFirst(this.end);
    }

    public int getStartColumn() {
        return IntPair.getSecond(this.start);
    }

    public int getStartLine() {
        return IntPair.getFirst(this.start);
    }

    public boolean isCollapsed() {
        return this.collapsed;
    }

    public void setCollapsed(boolean z) {
        this.collapsed = z;
    }

    public FoldingRegion(long j, long j2) {
        this.start = j;
        this.end = j2;
    }
}
