package com.reandroid.dex.id;

import com.reandroid.arsc.base.Block;
import com.reandroid.arsc.base.BlockCreator;
import com.reandroid.arsc.base.BlockRefresh;
import com.reandroid.arsc.io.BlockReader;
import com.reandroid.dex.base.BlockListArray;
import com.reandroid.dex.base.FixedSizeBlock;
import com.reandroid.dex.common.EditableItem;
import com.reandroid.dex.common.IdUsageIterator;
import com.reandroid.dex.common.SectionItem;
import com.reandroid.dex.key.Key;
import com.reandroid.dex.key.KeyReference;
import com.reandroid.dex.sections.SectionType;
import com.reandroid.dex.smali.SmaliFormat;
import java.io.IOException;
import java.util.Iterator;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public abstract class IdItem extends SectionItem implements SmaliFormat, BlockRefresh, EditableItem, KeyReference, FixedSizeBlock, IdUsageIterator {
    public IdItem(int i) {
        super(i);
    }

    public abstract void cacheItems();

    @Override // com.reandroid.dex.common.SectionItem
    public int getIdx() {
        return getIndex();
    }

    @Override // com.reandroid.dex.common.SectionItem, com.reandroid.dex.key.KeyItem
    public abstract Key getKey();

    @Override // com.reandroid.dex.common.SectionItem
    public abstract SectionType<? extends IdItem> getSectionType();

    public void onReadBytes(BlockReader blockReader) throws IOException {
        super.onReadBytes(blockReader);
        cacheItems();
    }

    @Override // com.reandroid.dex.common.SectionItem
    public void removeSelf() {
        BlockCreator blockCreator = (BlockListArray) getParentInstance(BlockListArray.class);
        if (blockCreator != null) {
            blockCreator.remove(this);
            setParent((Block) null);
            setIndex(-1);
        }
    }

    public abstract void setKey(Key key);

    public abstract Iterator<IdItem> usedIds();
}
