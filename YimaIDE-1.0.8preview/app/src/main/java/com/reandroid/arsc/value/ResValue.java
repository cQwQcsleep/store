package com.reandroid.arsc.value;

import com.reandroid.arsc.chunk.PackageBlock;
import com.reandroid.arsc.io.BlockReader;
import java.io.IOException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class ResValue extends ValueItem {
    private static final int OFFSET_SIZE = 0;

    public ResValue() {
        super(8, 0);
    }

    private EntryHeader getHeader() {
        ResTableEntry resTableEntry = (ResTableEntry) getParent(ResTableEntry.class);
        if (resTableEntry != null) {
            return resTableEntry.getHeader();
        }
        f63.a("Unreachable");
        return null;
    }

    private void updateBytesLength(boolean z) {
        setBytesLength(z ? 0 : 8, false);
    }

    public int getData() {
        return isCompact() ? getHeader().getData() : super.getData();
    }

    public Entry getEntry() {
        return (Entry) getParent(Entry.class);
    }

    /* JADX INFO: renamed from: getParentChunk, reason: merged with bridge method [inline-methods] */
    public PackageBlock m3getParentChunk() {
        Entry entry = getEntry();
        if (entry != null) {
            return entry.getPackageBlock();
        }
        return null;
    }

    public int getSize() {
        if (isCompact()) {
            return 0;
        }
        return super.getSize();
    }

    public byte getType() {
        return isCompact() ? getHeader().getType() : super.getType();
    }

    public boolean isCompact() {
        return getHeader().isCompact();
    }

    public void onReadBytes(BlockReader blockReader) throws IOException {
        boolean zIsCompact = isCompact();
        updateBytesLength(zIsCompact);
        if (zIsCompact) {
            return;
        }
        super.onReadBytes(blockReader);
    }

    public void setCompact(boolean z) {
        EntryHeader header = getHeader();
        if (z == header.isCompact()) {
            return;
        }
        byte type = getType();
        int data = getData();
        updateBytesLength(z);
        header.setCompact(z);
        setType(type);
        setData(data);
        if (z) {
            setRes0((byte) 0);
        }
    }

    public void setSize(int i) {
        if (isCompact()) {
            return;
        }
        super.setSize(i);
    }

    public void setType(byte b) {
        if (isCompact()) {
            getHeader().setType(b);
        } else {
            super.setType(b);
        }
    }

    public void updateSize() {
        if (isCompact()) {
            return;
        }
        super.updateSize();
    }

    public void writeData(int i) {
        if (isCompact()) {
            getHeader().setData(i);
        } else {
            super.writeData(i);
        }
    }
}
