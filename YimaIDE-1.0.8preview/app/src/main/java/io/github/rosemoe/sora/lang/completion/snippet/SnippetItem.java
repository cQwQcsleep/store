package io.github.rosemoe.sora.lang.completion.snippet;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public abstract class SnippetItem implements Cloneable {
    private int end;
    private int start;

    public SnippetItem(int i, int i2) {
        setIndex(i, i2);
    }

    @Override // 
    /* JADX INFO: renamed from: clone, reason: merged with bridge method [inline-methods] */
    public abstract SnippetItem mo1clone();

    public int getEndIndex() {
        return this.end;
    }

    public int getStartIndex() {
        return this.start;
    }

    public void setIndex(int i, int i2) {
        this.start = i;
        this.end = i2;
    }

    public void shiftIndex(int i) {
        this.start += i;
        this.end += i;
    }

    public void setIndex(int i) {
        setIndex(i, i);
    }

    public SnippetItem(int i) {
        this(i, i);
    }

    public SnippetItem() {
        this(0);
    }
}
