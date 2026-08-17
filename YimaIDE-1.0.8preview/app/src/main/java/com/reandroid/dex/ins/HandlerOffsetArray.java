package com.reandroid.dex.ins;

import com.reandroid.arsc.base.Block;
import com.reandroid.arsc.base.Creator;
import com.reandroid.arsc.container.CountedBlockList;
import com.reandroid.arsc.io.BlockReader;
import com.reandroid.arsc.item.IntegerReference;
import com.reandroid.dex.ins.HandlerOffset;
import com.reandroid.utils.StringsUtil;
import java.io.IOException;
import java.util.Iterator;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class HandlerOffsetArray extends CountedBlockList<HandlerOffset> {
    private static final Creator<HandlerOffset> CREATOR = new Creator() { // from class: k46
        @Override // com.reandroid.arsc.base.Creator
        public final Block newInstance() {
            return new HandlerOffset();
        }
    };
    private int itemsStart;

    public HandlerOffsetArray(IntegerReference integerReference) {
        super(CREATOR, integerReference);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public HandlerOffset getHandlerOffset(int i) {
        HandlerOffset handlerOffset = (HandlerOffset) get(i);
        if (handlerOffset == null) {
            return null;
        }
        if (handlerOffset.getIdx() == i) {
            return handlerOffset;
        }
        int size = size();
        for (int i2 = 0; i2 < size; i2++) {
            HandlerOffset handlerOffset2 = (HandlerOffset) get(i2);
            if (handlerOffset2.getIdx() == i) {
                return handlerOffset2;
            }
        }
        return null;
    }

    public int getItemsStart() {
        return this.itemsStart;
    }

    public int getMinStart() {
        int size = size();
        int i = 0;
        for (int i2 = 0; i2 < size; i2++) {
            int offset = getOffset(i2);
            if (i2 == 0 || offset < i) {
                i = offset;
            }
        }
        return i;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public int getOffset(int i) {
        HandlerOffset handlerOffset = (HandlerOffset) get(i);
        if (handlerOffset != null) {
            return handlerOffset.getOffset();
        }
        return -1;
    }

    public HandlerOffset getOrCreate(int i) {
        ensureSize(i + 1);
        return getHandlerOffset(i);
    }

    public int indexOf(int i) {
        if (i < 0) {
            return -1;
        }
        int size = size();
        for (int i2 = 0; i2 < size; i2++) {
            if (i == getOffset(i2)) {
                return i2;
            }
        }
        return -1;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void merge(HandlerOffsetArray handlerOffsetArray) {
        if (handlerOffsetArray == this) {
            return;
        }
        int size = handlerOffsetArray.size();
        setSize(size);
        for (int i = 0; i < size; i++) {
            ((HandlerOffset) get(i)).merge((HandlerOffset) handlerOffsetArray.get(i));
        }
    }

    @Override // com.reandroid.arsc.container.CountedBlockList
    public void onReadBytes(BlockReader blockReader) throws IOException {
        super.onReadBytes(blockReader);
        this.itemsStart = blockReader.getPosition();
    }

    @Override // com.reandroid.arsc.container.BlockList
    public String toString() {
        return "size = " + size() + '[' + StringsUtil.join((Iterator<?>) iterator(), (Object) ", ") + ']';
    }
}
