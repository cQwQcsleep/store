package com.reandroid.dex.data;

import com.reandroid.arsc.base.BlockRefresh;
import com.reandroid.arsc.item.BlockItem;
import com.reandroid.arsc.item.IndirectInteger;
import com.reandroid.arsc.item.IntegerReference;
import com.reandroid.dex.common.SectionTool;
import com.reandroid.dex.id.ClassId;
import com.reandroid.dex.key.Key;
import com.reandroid.dex.key.TypeKey;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
class HiddenApiIndex extends BlockItem implements Comparable<HiddenApiIndex>, BlockRefresh {
    private final ClassId classId;
    private final IntegerReference dataOffset;
    private HiddenApiData hiddenApiData;

    public HiddenApiIndex(ClassId classId) {
        super(4);
        this.dataOffset = new IndirectInteger(this, 0);
        this.classId = classId;
    }

    @Override // java.lang.Comparable
    public int compareTo(HiddenApiIndex hiddenApiIndex) {
        if (hiddenApiIndex == null) {
            return -1;
        }
        return SectionTool.compareIdx(getClassId(), hiddenApiIndex.getClassId());
    }

    public HiddenApiFlagValue get(Key key) {
        HiddenApiData hiddenApiData = getHiddenApiData();
        if (hiddenApiData != null) {
            return hiddenApiData.get(getClassId().getDef(key));
        }
        return null;
    }

    public ClassId getClassId() {
        return this.classId;
    }

    public TypeKey getClassType() {
        ClassId classId = getClassId();
        if (classId != null) {
            return classId.getKey();
        }
        return null;
    }

    public IntegerReference getDataOffset() {
        return this.dataOffset;
    }

    public HiddenApiData getHiddenApiData() {
        return this.hiddenApiData;
    }

    public boolean hasValidDataOffset() {
        return this.dataOffset.get() != 0;
    }

    public boolean isAllNoRestrictions() {
        HiddenApiData hiddenApiData = getHiddenApiData();
        return hiddenApiData == null || hiddenApiData.isAllNoRestrictions();
    }

    public boolean isNull() {
        ClassId classId = getClassId();
        return classId == null || classId.isRemoved();
    }

    public void linkData(HiddenApiData hiddenApiData) {
        this.hiddenApiData = hiddenApiData;
        hiddenApiData.setOffsetReference(getDataOffset());
        hiddenApiData.setClassId(getClassId());
    }

    public void refresh() {
        if (getClassId().isRemoved()) {
            removeSelf();
        } else {
            HiddenApiData hiddenApiData = getHiddenApiData();
            this.dataOffset.set(hiddenApiData == null ? 0 : hiddenApiData.getOffset());
        }
    }

    public void removeSelf() {
        HiddenApiIndexList hiddenApiIndexList = (HiddenApiIndexList) getParentInstance(HiddenApiIndexList.class);
        if (hiddenApiIndexList != null) {
            hiddenApiIndexList.remove(this);
        }
    }

    public String toString() {
        return getClassType() + " {" + getDataOffset() + "}";
    }
}
