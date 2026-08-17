package io.github.rosemoe.sora.langs.textmate.folding;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public class FoldingRegion {
    private final int index;
    private final FoldingRegions ranges;

    public FoldingRegion(FoldingRegions foldingRegions, int i) {
        this.ranges = foldingRegions;
        this.index = i;
    }

    public int getEndLineNumber() {
        return this.ranges.getEndLineNumber(this.index);
    }

    public int getParentIndex() throws Exception {
        return this.ranges.getParentIndex(this.index);
    }

    public int getRegionIndex() {
        return this.index;
    }

    public int getStartLineNumber() {
        return this.ranges.getStartLineNumber(this.index);
    }
}
