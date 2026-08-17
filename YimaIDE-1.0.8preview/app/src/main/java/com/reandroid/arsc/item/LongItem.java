package com.reandroid.arsc.item;

import com.reandroid.arsc.base.Block;
import com.reandroid.arsc.base.Creator;
import com.reandroid.arsc.base.DirectStreamReader;
import com.reandroid.arsc.item.LongItem;
import com.reandroid.utils.HexUtil;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class LongItem extends BlockItem implements LongReference, DirectStreamReader {
    public static final Creator<LongItem> CREATOR = new Creator() { // from class: sh9
        public final Block newInstance() {
            return LongItem.k();
        }
    };
    public static final Creator<LongItem> CREATOR_BIG_ENDIAN = new Creator() { // from class: th9
        public final Block newInstance() {
            return LongItem.b();
        }
    };
    private final boolean bigEndian;
    private long mCache;

    public LongItem(boolean z) {
        super(8);
        this.bigEndian = z;
    }

    public static /* synthetic */ LongItem b() {
        return new LongItem(true);
    }

    public static /* synthetic */ LongItem k() {
        return new LongItem(false);
    }

    @Override // com.reandroid.arsc.item.IntegerReference
    public int get() {
        return (int) getLong();
    }

    @Override // com.reandroid.arsc.item.LongReference
    public long getLong() {
        return this.mCache;
    }

    @Override // com.reandroid.arsc.item.BlockItem
    public void onBytesChanged() {
        byte[] bytesInternal = getBytesInternal();
        this.mCache = this.bigEndian ? Block.getBigEndianLong(bytesInternal, 0) : Block.getLong(bytesInternal, 0);
    }

    @Override // com.reandroid.arsc.item.LongReference
    public void set(long j) {
        if (j == this.mCache) {
            return;
        }
        this.mCache = j;
        byte[] bytesInternal = getBytesInternal();
        if (this.bigEndian) {
            Block.putBigEndianLong(bytesInternal, 0, j);
        } else {
            Block.putLong(bytesInternal, 0, j);
        }
    }

    public String toHex() {
        return HexUtil.toHex(getLong(), 16);
    }

    public String toString() {
        return String.valueOf(getLong());
    }

    public LongItem() {
        this(false);
    }

    @Override // com.reandroid.arsc.item.IntegerReference
    public void set(int i) {
        set(((long) i) & 4294967295L);
    }
}
