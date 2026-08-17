package com.reandroid.dex.sections;

import com.reandroid.arsc.base.Creator;
import com.reandroid.dex.base.BlockListArray;
import com.reandroid.dex.base.DexPositionAlign;
import com.reandroid.dex.base.IntegerPair;
import com.reandroid.dex.base.PositionAlignedItem;
import com.reandroid.dex.base.PositionedItem;
import com.reandroid.dex.base.UsageMarker;
import com.reandroid.dex.common.FullRefresh;
import com.reandroid.dex.common.SectionItem;
import java.util.Iterator;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class SectionArray<T extends SectionItem> extends BlockListArray<T> implements FullRefresh {
    public SectionArray(IntegerPair integerPair, Creator<T> creator) {
        super(integerPair, creator);
    }

    private void notifyBeforeRemoved(T t) {
        Section<T> parentSection = getParentSection();
        if (parentSection != null) {
            parentSection.onRemoving(t);
        }
        t.onRemovedInternal();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void clear() {
        clearChildes();
        destroy();
    }

    public Section<T> getParentSection() {
        return (Section) getParentInstance(Section.class);
    }

    @Override // 
    public void onPreRemove(T t) {
        notifyBeforeRemoved(t);
        super.onPreRemove(t);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.reandroid.dex.common.FullRefresh
    public void refreshFull() {
        if (getFirst() instanceof FullRefresh) {
            Iterator it = iterator();
            while (it.hasNext()) {
                ((FullRefresh) it.next()).refreshFull();
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public int updatePositionedItemOffsets(int i) {
        DexPositionAlign positionAlign;
        int count = getCount();
        getCountAndOffset().getFirst().set(count);
        if (!(getFirst() instanceof PositionedItem)) {
            return i;
        }
        DexPositionAlign dexPositionAlign = null;
        for (int i2 = 0; i2 < count; i2++) {
            UsageMarker usageMarker = (SectionItem) get(i2);
            if (usageMarker == null) {
                dexPositionAlign = null;
            } else {
                if (usageMarker instanceof PositionAlignedItem) {
                    positionAlign = ((PositionAlignedItem) usageMarker).getPositionAlign();
                    if (positionAlign != null) {
                        positionAlign.setSize(0);
                    }
                    if (dexPositionAlign != null) {
                        dexPositionAlign.align(i);
                        i += dexPositionAlign.size();
                    }
                } else {
                    positionAlign = null;
                }
                if (i2 == count - 1) {
                    ((PositionedItem) usageMarker).removeLastAlign();
                }
                ((PositionedItem) usageMarker).setPosition(i);
                i += usageMarker.countBytes();
                dexPositionAlign = positionAlign;
            }
        }
        return i;
    }
}
