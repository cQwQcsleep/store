package com.reandroid.arsc.item;

import com.reandroid.arsc.base.Block;
import com.reandroid.arsc.chunk.SpecBlock;
import com.reandroid.utils.HexUtil;
import org.xmlpull.v1.XmlPullParser;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class SpecFlag extends IndirectItem<SpecFlagsArray> {
    private static final int OFFSET_FLAG = 3;

    public SpecFlag(SpecFlagsArray specFlagsArray, int i) {
        super(specFlagsArray, i);
    }

    public void addFlag(SpecBlock.Flag flag) {
        addFlagByte(flag.getFlag());
    }

    public void addFlagByte(byte b) {
        setFlagByte((byte) ((b & 255) | (getFlagByte() & 255)));
    }

    public byte getFlagByte() {
        return getBlockItem().getBytesInternal()[getOffset() + 3];
    }

    public int getInteger() {
        return Block.getInteger(getBlockItem().getBytesInternal(), getOffset());
    }

    public boolean isPublic() {
        return SpecBlock.Flag.isPublic(getFlagByte());
    }

    public void setFlagByte(byte b) {
        getBlockItem().getBytesInternal()[getOffset() + 3] = b;
    }

    public void setInteger(int i) {
        if (i == getInteger()) {
            return;
        }
        Block.putInteger(getBlockItem().getBytesInternal(), getOffset(), i);
        getBlockItem().onBytesChanged();
    }

    public void setPublic() {
        addFlag(SpecBlock.Flag.SPEC_PUBLIC);
    }

    public String toString() {
        if (getFlagByte() != 0) {
            return SpecBlock.Flag.toString(getFlagByte());
        }
        int integer = getInteger();
        return integer != 0 ? HexUtil.toHex8(integer) : XmlPullParser.NO_NAMESPACE;
    }
}
