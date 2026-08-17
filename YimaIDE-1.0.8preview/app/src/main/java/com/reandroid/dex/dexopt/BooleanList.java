package com.reandroid.dex.dexopt;

import com.reandroid.arsc.base.Block;
import com.reandroid.arsc.base.BlockCounter;
import com.reandroid.arsc.container.CountedBlockList;
import com.reandroid.arsc.io.BlockReader;
import com.reandroid.arsc.item.IntegerReference;
import java.io.IOException;
import java.io.OutputStream;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class BooleanList extends CountedBlockList<BooleanBit> {
    public BooleanList(IntegerReference integerReference) {
        super(BooleanBit.CREATOR, integerReference);
    }

    @Override // com.reandroid.arsc.container.BlockList
    public int countBytes() {
        return BitItem.bitsToBytes(size());
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.reandroid.arsc.container.BlockList
    public byte[] getBytes() {
        int size = size();
        if (size == 0) {
            return new byte[0];
        }
        BitItem bitItem = new BitItem(size);
        for (int i = 0; i < size; i++) {
            bitItem.set(i, ((BooleanBit) get(i)).get());
        }
        return bitItem.getBytes();
    }

    @Override // com.reandroid.arsc.container.BlockList
    public void onCountUpTo(BlockCounter blockCounter) {
        if (blockCounter.FOUND) {
            return;
        }
        blockCounter.setCurrent(this);
        Block block = blockCounter.END;
        if (block == this) {
            blockCounter.FOUND = true;
            return;
        }
        if (block == null || get(block.getIndex()) != block) {
            blockCounter.addCount(countBytes());
            return;
        }
        blockCounter.addCount(BitItem.bitsToBytes(block.getIndex()));
        blockCounter.setCurrent(block);
        blockCounter.FOUND = true;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.reandroid.arsc.container.CountedBlockList
    public void onReadBytes(BlockReader blockReader) throws IOException {
        int i = getCountReference().get();
        setSize(i);
        BitItem bitItem = new BitItem(i);
        bitItem.onReadBytes(blockReader);
        for (int i2 = 0; i2 < i; i2++) {
            ((BooleanBit) get(i2)).set(bitItem.get(i2));
        }
    }

    @Override // com.reandroid.arsc.container.BlockList
    public int onWriteBytes(OutputStream outputStream) throws IOException {
        byte[] bytes = getBytes();
        int length = bytes.length;
        outputStream.write(bytes, 0, length);
        return length;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public int readFrom(BitItem bitItem, int i) {
        int size = size();
        for (int i2 = 0; i2 < size; i2++) {
            ((BooleanBit) get(i2)).set(bitItem.get(i));
            i++;
        }
        return i;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public int writeTo(BitItem bitItem, int i) {
        int size = size();
        bitItem.ensureBitsLength(i + size);
        for (int i2 = 0; i2 < size; i2++) {
            bitItem.set(i, ((BooleanBit) get(i2)).get());
            i++;
        }
        return i;
    }
}
