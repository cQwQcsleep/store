package com.reandroid.dex.data;

import com.reandroid.arsc.base.Block;
import com.reandroid.arsc.base.Creator;
import com.reandroid.arsc.container.BlockList;
import com.reandroid.arsc.io.BlockReader;
import com.reandroid.dex.base.Ule128Item;
import com.reandroid.dex.debug.DebugElement;
import com.reandroid.dex.debug.DebugParameter;
import com.reandroid.dex.debug.DebugSequence;
import com.reandroid.dex.id.IdItem;
import com.reandroid.dex.key.DataKey;
import com.reandroid.dex.key.Key;
import com.reandroid.dex.key.KeyReference;
import com.reandroid.dex.sections.SectionType;
import com.reandroid.utils.collection.CombiningIterator;
import com.reandroid.utils.collection.EmptyIterator;
import com.reandroid.utils.collection.IterableIterator;
import java.io.IOException;
import java.util.Iterator;
import java.util.Objects;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class DebugInfo extends DataItem implements KeyReference {
    private static final Creator<DebugParameter> CREATOR = new Creator() { // from class: ec3
        @Override // com.reandroid.arsc.base.Creator
        public final Block newInstance() {
            return new DebugParameter();
        }
    };
    private final DataKey<DebugInfo> debugKey;
    private final Ule128Item debugParameterCount;
    private BlockList<DebugParameter> debugParametersArray;
    private final DebugSequence debugSequence;
    private final Ule128Item lineStart;

    public DebugInfo() {
        super(4);
        Ule128Item ule128Item = new Ule128Item(true);
        this.lineStart = ule128Item;
        Ule128Item ule128Item2 = new Ule128Item();
        this.debugParameterCount = ule128Item2;
        DebugSequence debugSequence = new DebugSequence(ule128Item);
        this.debugSequence = debugSequence;
        addChildBlock(0, ule128Item);
        addChildBlock(1, ule128Item2);
        addChildBlock(3, debugSequence);
        this.debugKey = new DataKey<>(this);
    }

    private BlockList<DebugParameter> initParametersArray() {
        BlockList<DebugParameter> blockList = this.debugParametersArray;
        if (blockList != null) {
            return blockList;
        }
        BlockList<DebugParameter> blockList2 = new BlockList<>();
        this.debugParametersArray = blockList2;
        addChildBlock(2, blockList2);
        blockList2.setCreator(CREATOR);
        return blockList2;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && getClass() == obj.getClass()) {
            DebugInfo debugInfo = (DebugInfo) obj;
            if (this.lineStart.get() == this.lineStart.get() && Objects.equals(this.debugParametersArray, debugInfo.debugParametersArray) && Objects.equals(this.debugSequence, debugInfo.debugSequence)) {
                return true;
            }
        }
        return false;
    }

    public DebugParameter getDebugParameter(int i) {
        BlockList<DebugParameter> blockList = this.debugParametersArray;
        if (blockList != null) {
            return blockList.get(i);
        }
        return null;
    }

    public DebugSequence getDebugSequence() {
        return this.debugSequence;
    }

    public Iterator<DebugElement> getExtraLines() {
        return getDebugSequence().getExtraLines();
    }

    public DebugParameter getOrCreateDebugParameter(int i) {
        BlockList<DebugParameter> blockListInitParametersArray = initParametersArray();
        blockListInitParametersArray.ensureSize(i + 1);
        return blockListInitParametersArray.get(i);
    }

    public int getParameterCount() {
        BlockList<DebugParameter> blockList = this.debugParametersArray;
        if (blockList != null) {
            return blockList.size();
        }
        return 0;
    }

    public Iterator<DebugParameter> getParameters() {
        BlockList<DebugParameter> blockList = this.debugParametersArray;
        return blockList != null ? blockList.iterator() : EmptyIterator.of();
    }

    @Override // com.reandroid.dex.common.SectionItem
    public SectionType<DebugInfo> getSectionType() {
        return SectionType.DEBUG_INFO;
    }

    public int hashCode() {
        int i = this.lineStart.get() + 31;
        BlockList<DebugParameter> blockList = this.debugParametersArray;
        int iHashCode = i * 31;
        if (blockList != null) {
            iHashCode += blockList.hashCode();
        }
        return (iHashCode * 31) + this.debugSequence.hashCode();
    }

    public void merge(DebugInfo debugInfo) {
        if (debugInfo == this) {
            return;
        }
        this.lineStart.set(debugInfo.lineStart.get());
        int parameterCount = debugInfo.getParameterCount();
        this.debugParameterCount.set(parameterCount);
        if (parameterCount != 0) {
            BlockList<DebugParameter> blockListInitParametersArray = initParametersArray();
            blockListInitParametersArray.setSize(parameterCount);
            for (int i = 0; i < parameterCount; i++) {
                blockListInitParametersArray.get(i).merge(debugInfo.getDebugParameter(i));
            }
        }
        getDebugSequence().merge(debugInfo.getDebugSequence());
    }

    @Override // com.reandroid.dex.common.SectionItemContainer
    public void onPreRefresh() {
        super.onPreRefresh();
        removeInvalidElements();
    }

    @Override // com.reandroid.dex.common.SectionItemContainer
    public void onReadBytes(BlockReader blockReader) throws IOException {
        this.lineStart.onReadBytes(blockReader);
        this.debugParameterCount.onReadBytes(blockReader);
        if (this.debugParameterCount.get() > 0) {
            BlockList<DebugParameter> blockListInitParametersArray = initParametersArray();
            blockListInitParametersArray.setSize(this.debugParameterCount.get());
            blockListInitParametersArray.readChildes(blockReader);
        }
        this.debugSequence.onReadBytes(blockReader);
    }

    @Override // com.reandroid.dex.data.DataItem, com.reandroid.dex.common.SectionItemContainer
    public void onRefreshed() {
        super.onRefreshed();
        BlockList<DebugParameter> blockList = this.debugParametersArray;
        this.debugParameterCount.set(blockList != null ? blockList.size() : 0);
    }

    public void onRemove() {
        BlockList<DebugParameter> blockList = this.debugParametersArray;
        if (blockList != null) {
            this.debugParametersArray = null;
            blockList.clearChildes();
        }
        this.debugSequence.clear();
    }

    public void removeDebugParameter(int i) {
        DebugParameter debugParameter;
        BlockList<DebugParameter> blockList = this.debugParametersArray;
        if (blockList == null || (debugParameter = blockList.get(i)) == null) {
            return;
        }
        if (i == blockList.getCount() - 1) {
            blockList.remove(debugParameter);
        } else {
            debugParameter.set(0);
        }
        blockList.refresh();
    }

    public void removeInvalidElements() {
        DebugSequence debugSequence = getDebugSequence();
        if (debugSequence != null) {
            debugSequence.removeInvalid();
        }
    }

    @Override // com.reandroid.dex.key.KeyReference
    public void setKey(Key key) {
        merge((DebugInfo) ((DataKey) key).getItem());
    }

    public String toString() {
        return "DebugInfo{lineStart=" + this.lineStart.get() + ", parameterCount=" + this.debugParameterCount.get() + ", sequence=(" + this.debugSequence + ")}";
    }

    @Override // com.reandroid.dex.data.DataItem, com.reandroid.dex.common.IdUsageIterator
    public Iterator<IdItem> usedIds() {
        return CombiningIterator.two(new IterableIterator<DebugParameter, IdItem>(getParameters()) { // from class: com.reandroid.dex.data.DebugInfo.1
            public Iterator<IdItem> iterator(DebugParameter debugParameter) {
                return debugParameter.usedIds();
            }
        }, getDebugSequence().usedIds());
    }

    @Override // com.reandroid.dex.data.DataItem, com.reandroid.dex.common.SectionItem, com.reandroid.dex.key.KeyItem
    public DataKey<DebugInfo> getKey() {
        return this.debugKey;
    }
}
