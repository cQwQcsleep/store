package com.reandroid.arsc.item;

import com.reandroid.arsc.base.Block;
import com.reandroid.arsc.base.Creator;
import com.reandroid.arsc.base.DirectStreamReader;
import com.reandroid.arsc.item.IntegerItem;
import com.reandroid.utils.HexUtil;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class IntegerItem extends BlockItem implements ReferenceItem, DirectStreamReader {
    public static final Creator<IntegerItem> CREATOR = new Creator() { // from class: dt6
        public final Block newInstance() {
            return IntegerItem.k();
        }
    };
    public static final Creator<IntegerItem> CREATOR_BIG_ENDIAN = new Creator() { // from class: et6
        public final Block newInstance() {
            return IntegerItem.b();
        }
    };
    private final boolean bigEndian;
    private int mCache;

    public IntegerItem(int i) {
        this(false);
        set(i);
    }

    public static /* synthetic */ IntegerItem b() {
        return new IntegerItem(true);
    }

    public static /* synthetic */ IntegerItem k() {
        return new IntegerItem(false);
    }

    @Override // com.reandroid.arsc.item.IntegerReference
    public int get() {
        return this.mCache;
    }

    @Override // com.reandroid.arsc.item.ReferenceItem
    public <T1 extends Block> T1 getReferredParent(Class<T1> cls) {
        return (T1) getParentInstance(cls);
    }

    @Override // com.reandroid.arsc.item.BlockItem
    public void onBytesChanged() {
        byte[] bytesInternal = getBytesInternal();
        this.mCache = this.bigEndian ? Block.getBigEndianInteger(bytesInternal, 0) : Block.getInteger(bytesInternal, 0);
    }

    @Override // com.reandroid.arsc.item.IntegerReference
    public void set(int i) {
        if (i == this.mCache) {
            return;
        }
        this.mCache = i;
        byte[] bytesInternal = getBytesInternal();
        if (this.bigEndian) {
            Block.putBigEndianInteger(bytesInternal, 0, i);
        } else {
            Block.putInteger(bytesInternal, 0, i);
        }
    }

    public String toHex() {
        return HexUtil.toHex8(get());
    }

    public String toString() {
        return String.valueOf(get());
    }

    public long unsignedLong() {
        return ((long) get()) & 4294967295L;
    }

    public IntegerItem() {
        this(false);
    }

    public IntegerItem(boolean z) {
        super(4);
        this.bigEndian = z;
    }
}
