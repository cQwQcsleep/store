package com.reandroid.arsc.item;

import com.reandroid.arsc.base.Block;
import com.reandroid.arsc.io.BlockReader;
import com.reandroid.utils.HexUtil;
import java.io.IOException;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class AlignItem extends BlockItem {
    private static final int ALIGNMENT = 4;
    private int alignment;
    private byte fill;
    private final boolean readable;

    public AlignItem(int i, boolean z) {
        super(0);
        this.alignment = i;
        this.readable = z;
    }

    public int align(Block block) {
        clear();
        if (getAlignment() <= 0) {
            return 0;
        }
        return align(block.countBytes());
    }

    public void alignSafe(BlockReader blockReader) throws IOException {
        align(blockReader.getPosition());
        int size = size();
        int iAvailable = blockReader.available();
        if (size == 0 || iAvailable < size) {
            return;
        }
        blockReader.readFully(getBytesInternal(), 0, size);
    }

    public void clear() {
        setBytesLength(0, false);
    }

    public void ensureSize(int i) {
        if (i > size()) {
            setSize(i);
        }
    }

    public int getAlignment() {
        return this.alignment;
    }

    public byte getFill() {
        return this.fill;
    }

    @Override // com.reandroid.arsc.item.BlockItem, com.reandroid.arsc.base.Block
    public void onReadBytes(BlockReader blockReader) throws IOException {
        if (this.readable) {
            alignSafe(blockReader);
        } else {
            super.onReadBytes(blockReader);
        }
    }

    public void setAlignment(int i) {
        this.alignment = i;
        if (i <= 0) {
            setBytesLength(0, false);
        }
    }

    public void setFill(byte b) {
        this.fill = b;
        byte[] bytesInternal = getBytesInternal();
        int length = bytesInternal.length;
        for (int i = 0; i < length; i++) {
            bytesInternal[i] = b;
        }
    }

    public void setSize(int i) {
        setBytesLength(i, false);
        setFill(this.fill);
    }

    public int size() {
        return countBytes();
    }

    public String toString() {
        int alignment = getAlignment();
        if (alignment <= 0) {
            return "OFF";
        }
        int size = size();
        byte b = this.fill;
        StringBuilder sb = new StringBuilder();
        if (alignment != 4) {
            sb.append("alignment=");
            sb.append(alignment);
            sb.append(", ");
        }
        if (b != 0) {
            sb.append("fill=");
            sb.append(HexUtil.toHex2(b));
            sb.append(", ");
        }
        sb.append("align=");
        sb.append(size);
        return sb.toString();
    }

    public AlignItem(int i) {
        this(i, false);
    }

    public AlignItem(boolean z) {
        this(4, z);
    }

    public AlignItem() {
        this(4, false);
    }

    public int align(long j) {
        int iAlign = align(getAlignment(), j);
        setSize(iAlign);
        return iAlign;
    }

    public int align(int i) {
        int iAlign = align(getAlignment(), i);
        setSize(iAlign);
        return iAlign;
    }

    public static int align(int i, long j) {
        if (i <= 1) {
            return 0;
        }
        return (i - ((int) (j % ((long) i)))) % i;
    }

    public static int align(int i, int i2) {
        if (i <= 1) {
            return 0;
        }
        return (i - (i2 % i)) % i;
    }
}
