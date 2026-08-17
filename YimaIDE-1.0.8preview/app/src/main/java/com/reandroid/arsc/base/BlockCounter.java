package com.reandroid.arsc.base;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class BlockCounter {
    private int COUNT;
    public final Block END;
    public boolean FOUND;

    public BlockCounter(Block block) {
        this.END = block;
    }

    public void addCount(int i) {
        if (this.FOUND || i == 0) {
            return;
        }
        int i2 = this.COUNT + i;
        this.COUNT = i2;
        onCountAdded(i2);
    }

    public int getCountValue() {
        return this.COUNT;
    }

    public void onCountAdded(int i) {
    }

    public void setCurrent(Block block) {
    }

    public String toString() {
        boolean z = this.FOUND;
        int i = this.COUNT;
        if (!z) {
            return String.valueOf(i);
        }
        return "FOUND=" + i;
    }
}
