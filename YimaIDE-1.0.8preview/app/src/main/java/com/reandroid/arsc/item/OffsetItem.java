package com.reandroid.arsc.item;

import com.reandroid.arsc.base.Block;
import com.reandroid.arsc.base.Creator;
import com.reandroid.arsc.base.DirectStreamReader;
import com.reandroid.arsc.io.BlockReader;
import com.reandroid.utils.CompareUtil;
import com.reandroid.utils.HexUtil;
import com.reandroid.utils.ObjectsUtil;
import defpackage.lx0;
import java.io.IOException;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class OffsetItem extends BlockItem implements DirectStreamReader, Comparable<OffsetItem> {
    private int mOffset;
    public static int NO_ENTRY = ObjectsUtil.of(-1);
    public static int NO_ENTRY16 = ObjectsUtil.of(65535);
    public static final Creator<OffsetItem> CREATOR_OFFSET16 = Helper.init16();
    public static final Creator<OffsetItem> CREATOR_OFFSET32 = Helper.init32();
    public static final Creator<OffsetItem> CREATOR_SPARSE = Helper.initSparse();

    public static class Helper {
        public static Creator<OffsetItem> init16() {
            return new Creator() { // from class: com.reandroid.arsc.item.c
                public final Block newInstance() {
                    return new OffsetItem.Offset16();
                }
            };
        }

        public static Creator<OffsetItem> init32() {
            return new Creator() { // from class: com.reandroid.arsc.item.b
                public final Block newInstance() {
                    return new OffsetItem.Offset32();
                }
            };
        }

        public static Creator<OffsetItem> initSparse() {
            return new Creator() { // from class: com.reandroid.arsc.item.a
                public final Block newInstance() {
                    return new OffsetItem.Sparse();
                }
            };
        }
    }

    public OffsetItem() {
        this(4);
    }

    public int compareIdx(OffsetItem offsetItem) {
        if (offsetItem == this) {
            return 0;
        }
        return CompareUtil.compare(getIdx(), offsetItem.getIdx());
    }

    public int compareOffset(OffsetItem offsetItem) {
        if (offsetItem == this) {
            return 0;
        }
        return CompareUtil.compare(getOffset(), offsetItem.getOffset());
    }

    public int getIdx() {
        return getIndex();
    }

    public int getOffset() {
        return this.mOffset;
    }

    public boolean isNoEntry() {
        return getOffset() == NO_ENTRY;
    }

    @Override // com.reandroid.arsc.item.BlockItem
    public void onBytesChanged() {
        super.onBytesChanged();
        this.mOffset = readOffset();
    }

    public abstract int readOffset();

    public void readTarget(BlockReader blockReader, Block block, boolean z) throws IOException {
        boolean zIsNoEntry = isNoEntry();
        int offset = getOffset();
        if (!zIsNoEntry) {
            int position = blockReader.getPosition() + blockReader.available();
            if (offset < 0 || offset > position) {
                if (!z) {
                    lx0.a("Offset ", offset, " is out of range ", position);
                    return;
                } else {
                    offset = NO_ENTRY;
                    setOffset(offset);
                    zIsNoEntry = true;
                }
            }
        }
        block.setNull(zIsNoEntry);
        if (zIsNoEntry) {
            return;
        }
        int position2 = blockReader.getPosition();
        blockReader.seek(offset);
        try {
            block.readBytes(blockReader);
            if (blockReader.getPosition() < position2) {
                blockReader.seek(position2);
            }
        } catch (Exception e) {
            throw new IOException("Error at:" + toString() + e.getMessage(), e);
        }
    }

    public void setIdx(int i) {
    }

    public void setOffset(int i) {
        if (i != this.mOffset) {
            writeOffset(i);
            this.mOffset = i;
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("(");
        sb.append(getIdx());
        sb.append(", ");
        if (isNoEntry()) {
            sb.append("NO_ENTRY");
        } else {
            sb.append(getOffset());
        }
        sb.append(')');
        return sb.toString();
    }

    public int updateOffset(Block block, int i) {
        if (block.isNull()) {
            setOffset(NO_ENTRY);
            return i;
        }
        setOffset(i);
        return i + block.countBytes();
    }

    public void validateValueRange(int i) {
        if (i == NO_ENTRY || ((-65536) & i) == 0) {
            return;
        }
        s01.a("Value out of range [0 - 0xffff]: ", HexUtil.toHex(i, 1));
    }

    public abstract void writeOffset(int i);

    public OffsetItem(int i) {
        super(i);
    }

    public static class Offset16 extends OffsetItem {
        public Offset16() {
            super(2);
        }

        @Override // com.reandroid.arsc.item.OffsetItem
        public int readOffset() {
            int shortUnsigned = Block.getShortUnsigned(getBytesInternal(), 0);
            return shortUnsigned == OffsetItem.NO_ENTRY16 ? OffsetItem.NO_ENTRY : shortUnsigned * 4;
        }

        @Override // com.reandroid.arsc.item.OffsetItem
        public void writeOffset(int i) {
            int i2;
            if (i == OffsetItem.NO_ENTRY) {
                i2 = OffsetItem.NO_ENTRY16;
            } else {
                i2 = i / 4;
                validateValueRange(i2);
            }
            Block.putShort(getBytesInternal(), 0, i2);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.reandroid.arsc.item.OffsetItem, java.lang.Comparable
        public int compareTo(OffsetItem offsetItem) {
            return compareIdx(offsetItem);
        }
    }

    public static class Offset32 extends OffsetItem {
        @Override // com.reandroid.arsc.item.OffsetItem
        public int readOffset() {
            return Block.getInteger(getBytesInternal(), 0);
        }

        @Override // com.reandroid.arsc.item.OffsetItem
        public void writeOffset(int i) {
            Block.putInteger(getBytesInternal(), 0, i);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.reandroid.arsc.item.OffsetItem, java.lang.Comparable
        public int compareTo(OffsetItem offsetItem) {
            return compareIdx(offsetItem);
        }
    }

    public static class Sparse extends OffsetItem {
        private int mIdx;

        @Override // com.reandroid.arsc.item.OffsetItem
        public int getIdx() {
            return this.mIdx;
        }

        @Override // com.reandroid.arsc.item.OffsetItem
        public boolean isNoEntry() {
            return false;
        }

        @Override // com.reandroid.arsc.item.OffsetItem, com.reandroid.arsc.item.BlockItem
        public void onBytesChanged() {
            super.onBytesChanged();
            this.mIdx = Block.getShortUnsigned(getBytesInternal(), 0);
        }

        @Override // com.reandroid.arsc.item.OffsetItem
        public int readOffset() {
            return Block.getShortUnsigned(getBytesInternal(), 2) * 4;
        }

        @Override // com.reandroid.arsc.item.OffsetItem
        public void setIdx(int i) {
            if (i != this.mIdx) {
                validateValueRange(i);
                this.mIdx = i;
                Block.putShort(getBytesInternal(), 0, i);
            }
        }

        @Override // com.reandroid.arsc.item.OffsetItem
        public void writeOffset(int i) {
            int i2 = i / 4;
            validateValueRange(i2);
            Block.putShort(getBytesInternal(), 2, i2);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.reandroid.arsc.item.OffsetItem, java.lang.Comparable
        public int compareTo(OffsetItem offsetItem) {
            return compareOffset(offsetItem);
        }
    }

    @Override // java.lang.Comparable
    public int compareTo(OffsetItem offsetItem) {
        return compareIdx(offsetItem);
    }

    public void readTarget(BlockReader blockReader, Block block) throws IOException {
        readTarget(blockReader, block, false);
    }
}
