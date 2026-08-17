package com.reandroid.dex.data;

import com.reandroid.arsc.base.Block;
import com.reandroid.arsc.io.BlockReader;
import com.reandroid.arsc.item.IntegerItem;
import com.reandroid.dex.base.UsageMarker;
import com.reandroid.dex.id.ClassId;
import com.reandroid.dex.key.Key;
import com.reandroid.dex.sections.Section;
import com.reandroid.dex.sections.SectionType;
import java.io.IOException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class HiddenApiRestrictions extends DataItem {
    private final HiddenApiDataList hiddenApiDataList;
    private final HiddenApiIndexList hiddenApiIndexList;
    private final IntegerItem sizeReference;

    public HiddenApiRestrictions() {
        super(3);
        Block integerItem = new IntegerItem();
        this.sizeReference = integerItem;
        HiddenApiIndexList hiddenApiIndexList = new HiddenApiIndexList();
        this.hiddenApiIndexList = hiddenApiIndexList;
        Block hiddenApiDataList = new HiddenApiDataList();
        this.hiddenApiDataList = hiddenApiDataList;
        addChildBlock(0, integerItem);
        addChildBlock(1, hiddenApiIndexList);
        addChildBlock(2, hiddenApiDataList);
    }

    private void updateSizeFast() {
        int iCountBytes;
        HiddenApiData last = this.hiddenApiDataList.getLast();
        if (last != null) {
            iCountBytes = last.getOffset();
            if (iCountBytes != 0) {
                iCountBytes += last.countBytes();
            }
        } else {
            iCountBytes = 0;
        }
        if (iCountBytes == 0 || iCountBytes != this.sizeReference.get()) {
            iCountBytes = countBytes();
        }
        this.sizeReference.set(iCountBytes);
    }

    private void updateUsageType() {
        Section section;
        if (getIndex() == 0 && getUsageType() == UsageMarker.USAGE_NONE && (section = getSection(SectionType.CLASS_ID)) != null && !section.isEmpty()) {
            addUsageType(UsageMarker.USAGE_DEFINITION);
        }
    }

    public HiddenApiIndex createNew(ClassId classId) {
        return getHiddenApiIndexList().createNext(classId, getHiddenApiDataList().createNext());
    }

    public HiddenApiFlagValue getFlagValue(Key key) {
        return getHiddenApiIndexList().getFlagValue(key);
    }

    public HiddenApiDataList getHiddenApiDataList() {
        return this.hiddenApiDataList;
    }

    public int getHiddenApiDataListOffset() {
        return this.sizeReference.countBytes() + this.hiddenApiIndexList.countBytes();
    }

    public HiddenApiIndexList getHiddenApiIndexList() {
        return this.hiddenApiIndexList;
    }

    @Override // com.reandroid.dex.common.SectionItem
    public SectionType<HiddenApiRestrictions> getSectionType() {
        return SectionType.HIDDEN_API;
    }

    public boolean isAllNoRestrictions() {
        return getHiddenApiIndexList().isAllNoRestrictions();
    }

    @Override // com.reandroid.dex.common.SectionItemContainer
    public void onReadBytes(BlockReader blockReader) throws IOException {
        this.sizeReference.readBytes(blockReader);
        blockReader.offset(-this.sizeReference.countBytes());
        int i = this.sizeReference.get();
        BlockReader blockReaderCreate = blockReader.create(i);
        blockReaderCreate.offset(this.sizeReference.countBytes());
        this.hiddenApiIndexList.onReadBytes(blockReaderCreate);
        this.hiddenApiDataList.onReadBytes(blockReaderCreate);
        blockReaderCreate.close();
        blockReader.seek(i);
    }

    @Override // com.reandroid.dex.data.DataItem, com.reandroid.dex.common.SectionItemContainer
    public void onRefreshed() {
        super.onRefreshed();
        updateSizeFast();
        updateUsageType();
    }

    public void removeIfAllNoRestrictions() {
        Section section = getSection(SectionType.HIDDEN_API);
        if (section != null && isAllNoRestrictions()) {
            getHiddenApiIndexList().clearChildes();
            HiddenApiDataList hiddenApiDataList = getHiddenApiDataList();
            hiddenApiDataList.setItem((Block) null);
            hiddenApiDataList.setParent((Block) null);
            removeSelf();
            section.removeSelf();
        }
    }

    public String toString() {
        return "index = " + this.hiddenApiIndexList + ", data = " + this.hiddenApiDataList;
    }
}
