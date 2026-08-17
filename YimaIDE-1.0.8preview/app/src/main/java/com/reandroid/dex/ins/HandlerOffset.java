package com.reandroid.dex.ins;

import com.reandroid.arsc.base.Block;
import com.reandroid.arsc.item.BlockItem;
import com.reandroid.utils.CompareUtil;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class HandlerOffset extends BlockItem implements Comparable<HandlerOffset> {
    private TryItem mTryItem;

    public HandlerOffset() {
        super(8);
    }

    @Override // java.lang.Comparable
    public int compareTo(HandlerOffset handlerOffset) {
        if (handlerOffset == null) {
            return 0;
        }
        TryItem tryItem = getTryItem();
        if (tryItem == null) {
            throw new NullPointerException("Unlinked handler offset: " + toString());
        }
        TryItem tryItem2 = handlerOffset.getTryItem();
        if (tryItem2 != null) {
            return CompareUtil.compare(tryItem.getIndex(), tryItem2.getIndex());
        }
        throw new NullPointerException("Unlinked handler offset: " + handlerOffset.toString());
    }

    public int getCatchCodeUnit() {
        return Block.getShortUnsigned(getBytesInternal(), 4);
    }

    public int getIdx() {
        TryItem tryItem = getTryItem();
        return tryItem != null ? tryItem.getIndex() : getIndex();
    }

    public int getOffset() {
        return Block.getShortUnsigned(getBytesInternal(), 6);
    }

    public int getStartAddress() {
        return Block.getInteger(getBytesInternal(), 0);
    }

    public TryItem getTryItem() {
        return this.mTryItem;
    }

    public void merge(HandlerOffset handlerOffset) {
        setBytes(handlerOffset);
    }

    public void removeSelf() {
        this.mTryItem = null;
        HandlerOffsetArray handlerOffsetArray = (HandlerOffsetArray) getParentInstance(HandlerOffsetArray.class);
        if (handlerOffsetArray != null) {
            handlerOffsetArray.remove(this);
        }
    }

    public void setCatchCodeUnit(int i) {
        Block.putShort(getBytesInternal(), 4, i);
    }

    public void setOffset(int i) {
        Block.putShort(getBytesInternal(), 6, i);
    }

    public void setStartAddress(int i) {
        Block.putInteger(getBytesInternal(), 0, i);
    }

    public void setTryItem(TryItem tryItem) {
        this.mTryItem = tryItem;
    }

    public String toString() {
        return "(" + getIndex() + " : start=" + getStartAddress() + ", catch=" + getCatchCodeUnit() + ", offset=" + getOffset() + ")";
    }
}
