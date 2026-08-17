package com.reandroid.arsc.item;

import com.reandroid.arsc.base.Block;
import com.reandroid.arsc.base.Creator;
import com.reandroid.arsc.base.DirectStreamReader;
import com.reandroid.arsc.item.ShortItem;
import com.reandroid.utils.HexUtil;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class ShortItem extends BlockItem implements IntegerReference, DirectStreamReader {
    public static final Creator<ShortItem> CREATOR = new Creator() { // from class: fad
        public final Block newInstance() {
            return ShortItem.k();
        }
    };
    public static final Creator<ShortItem> CREATOR_BIG_ENDIAN = new Creator() { // from class: gad
        public final Block newInstance() {
            return ShortItem.b();
        }
    };
    private final boolean bigEndian;
    private int mCache;

    public ShortItem(short s) {
        this(false);
        set(s);
    }

    public static /* synthetic */ ShortItem b() {
        return new ShortItem(true);
    }

    public static /* synthetic */ ShortItem k() {
        return new ShortItem(false);
    }

    @Override // com.reandroid.arsc.item.IntegerReference
    public int get() {
        return this.mCache;
    }

    public short getShort() {
        return (short) this.mCache;
    }

    @Override // com.reandroid.arsc.item.BlockItem
    public void onBytesChanged() {
        byte[] bytesInternal = getBytesInternal();
        this.mCache = this.bigEndian ? Block.getBigEndianShort(bytesInternal, 0) : Block.getShortUnsigned(bytesInternal, 0);
    }

    @Override // com.reandroid.arsc.item.IntegerReference
    public void set(int i) {
        if (i == this.mCache) {
            return;
        }
        this.mCache = i;
        byte[] bytesInternal = getBytesInternal();
        if (this.bigEndian) {
            Block.putBigEndianShort(bytesInternal, 0, i);
        } else {
            Block.putShort(bytesInternal, 0, i);
        }
    }

    public String toHex() {
        return HexUtil.toHex4(getShort());
    }

    public String toString() {
        return String.valueOf(get());
    }

    public int unsignedInt() {
        return get();
    }

    public ShortItem() {
        this(false);
    }

    public ShortItem(boolean z) {
        super(2);
        this.bigEndian = z;
    }

    public void set(short s) {
        set(s & 65535);
    }
}
