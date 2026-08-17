package com.reandroid.arsc.item;

import com.reandroid.arsc.base.Block;
import com.reandroid.arsc.base.Creator;
import com.reandroid.arsc.base.DirectStreamReader;
import com.reandroid.arsc.item.ByteItem;
import com.reandroid.utils.HexUtil;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class ByteItem extends BlockItem implements IntegerReference, DirectStreamReader {
    public static final Creator<ByteItem> CREATOR = new Creator() { // from class: v31
        public final Block newInstance() {
            return new ByteItem();
        }
    };

    public ByteItem() {
        super(1);
    }

    @Override // com.reandroid.arsc.item.IntegerReference
    public int get() {
        return getByte() & 255;
    }

    public boolean getBit(int i) {
        return ((getByte() >> i) & 1) == 1;
    }

    public byte getByte() {
        return getBytesInternal()[0];
    }

    public void putBit(int i, boolean z) {
        byte b = getByte();
        int i2 = b >> i;
        set((byte) (((255 >> (8 - i)) & b) | ((z ? i2 | 1 : i2 & 254) << i)));
    }

    public void set(byte b) {
        getBytesInternal()[0] = b;
    }

    public String toHex() {
        return HexUtil.toHex2(getByte());
    }

    public String toString() {
        return String.valueOf((int) getByte());
    }

    @Override // com.reandroid.arsc.item.IntegerReference
    public void set(int i) {
        set((byte) i);
    }
}
