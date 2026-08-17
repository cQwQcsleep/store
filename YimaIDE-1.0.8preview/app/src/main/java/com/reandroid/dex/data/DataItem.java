package com.reandroid.dex.data;

import com.reandroid.arsc.base.Block;
import com.reandroid.arsc.base.OffsetSupplier;
import com.reandroid.arsc.container.BlockList;
import com.reandroid.arsc.io.BlockReader;
import com.reandroid.dex.base.OffsetReceiver;
import com.reandroid.dex.base.PositionedItem;
import com.reandroid.dex.common.IdUsageIterator;
import com.reandroid.dex.common.SectionItem;
import com.reandroid.dex.common.SectionItemContainer;
import com.reandroid.dex.id.IdItem;
import com.reandroid.dex.key.Key;
import com.reandroid.dex.key.KeyItem;
import com.reandroid.dex.key.KeyReference;
import com.reandroid.utils.collection.EmptyIterator;
import java.io.IOException;
import java.util.Iterator;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class DataItem extends SectionItemContainer implements PositionedItem, OffsetSupplier, OffsetReceiver, KeyItem, IdUsageIterator {
    private boolean mShared;
    private Block mUniqueUser;

    public DataItem(int i) {
        super(i);
    }

    public void addUniqueUser(Block block) {
        Block block2;
        if (block == null || this.mShared || block == (block2 = this.mUniqueUser) || block == this) {
            return;
        }
        if (block2 != null) {
            this.mShared = true;
        } else {
            this.mUniqueUser = block;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void copyFrom(DataItem dataItem) {
        if (dataItem == 0) {
            return;
        }
        if (this instanceof KeyReference) {
            ((KeyReference) this).setKey(((KeyReference) dataItem).getKey());
            return;
        }
        try {
            readBytes(new BlockReader(dataItem.getBytes()));
        } catch (IOException e) {
            rc6.a(e);
        }
    }

    @Override // com.reandroid.dex.common.SectionItem, com.reandroid.dex.key.KeyItem
    public Key getKey() {
        return null;
    }

    public boolean isSharedItem(Block block) {
        if (isSharedItem()) {
            return true;
        }
        Block block2 = this.mUniqueUser;
        return (block2 == null || block == null || block2 == block) ? false : true;
    }

    @Override // com.reandroid.dex.common.SectionItemContainer
    public void onRefreshed() {
        super.onRefreshed();
        Block block = this.mUniqueUser;
        if (block == null || block.getParent() != null) {
            return;
        }
        this.mUniqueUser = null;
    }

    @Override // com.reandroid.dex.common.SectionItemContainer, com.reandroid.dex.base.PositionedItem
    public void removeLastAlign() {
    }

    @Override // com.reandroid.dex.common.SectionItem
    public void removeSelf() {
        Block parent = getParent();
        if (parent == null) {
            return;
        }
        ((BlockList) parent).remove(this);
        setPosition(0);
        this.mUniqueUser = null;
    }

    @Override // com.reandroid.dex.common.SectionItem
    public void setReplace(SectionItem sectionItem) {
        super.setReplace(sectionItem);
        SectionItem replace = getReplace();
        if (replace == null || replace == this) {
            return;
        }
        ((DataItem) replace).addUniqueUser(this.mUniqueUser);
    }

    public Iterator<IdItem> usedIds() {
        return EmptyIterator.of();
    }

    public boolean isSharedItem() {
        return this.mShared;
    }
}
