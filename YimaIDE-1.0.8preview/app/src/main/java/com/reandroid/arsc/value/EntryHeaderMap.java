package com.reandroid.arsc.value;

import com.intellij.psi.PsiKeyword;
import com.reandroid.arsc.base.Block;
import com.reandroid.arsc.chunk.PackageBlock;
import com.reandroid.arsc.model.ResourceEntry;
import com.reandroid.arsc.refactor.ResourceMergeOption;
import com.reandroid.json.JSONObject;
import com.reandroid.utils.HexUtil;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class EntryHeaderMap extends ValueHeader {
    private static final short HEADER_SIZE_COMPLEX = 16;
    public static final String NAME_parent_id = "parent_id";
    private static final int OFFSET_PARENT_ID = 8;
    private static final int OFFSET_VALUE_COUNT = 12;

    public EntryHeaderMap() {
        super(16);
        setComplex(true);
    }

    @Override // com.reandroid.arsc.value.ValueHeader, com.reandroid.json.JSONConvert
    public void fromJson(JSONObject jSONObject) {
        super.fromJson(jSONObject);
        setComplex(jSONObject.optBoolean(ValueHeader.NAME_is_complex, true));
        setParentId(jSONObject.optInt(NAME_parent_id));
    }

    public int getParentId() {
        return Block.getInteger(getBytesInternal(), 8);
    }

    public int getValuesCount() {
        return Block.getInteger(getBytesInternal(), 12);
    }

    @Override // com.reandroid.arsc.value.ValueHeader
    public void merge(ValueHeader valueHeader) {
        if (valueHeader == this || !(valueHeader instanceof EntryHeaderMap)) {
            return;
        }
        super.merge(valueHeader);
        EntryHeaderMap entryHeaderMap = (EntryHeaderMap) valueHeader;
        setParentId(entryHeaderMap.getParentId());
        setValuesCount(entryHeaderMap.getValuesCount());
    }

    @Override // com.reandroid.arsc.value.ValueHeader
    public void mergeWithName(ResourceMergeOption resourceMergeOption, ValueHeader valueHeader) {
        int resourceId;
        if (valueHeader == this || !(valueHeader instanceof EntryHeaderMap)) {
            return;
        }
        super.merge(valueHeader);
        Block block = (EntryHeaderMap) valueHeader;
        setValuesCount(block.getValuesCount());
        ResourceEntry resourceEntryResolveParentId = block.resolveParentId();
        if (resourceEntryResolveParentId == null) {
            setParentId(block.getParentId());
            return;
        }
        if (resourceEntryResolveParentId.isContext(block)) {
            ResourceEntry resourceEntryMergeWithName = ((PackageBlock) getParentInstance(PackageBlock.class)).mergeWithName(resourceMergeOption, resourceEntryResolveParentId);
            resourceId = resourceEntryMergeWithName != null ? resourceEntryMergeWithName.getResourceId() : 0;
        } else {
            resourceId = resourceEntryResolveParentId.getResourceId();
        }
        setParentId(resourceId);
    }

    public ResourceEntry resolveParentId() {
        Entry entry = (Entry) getParentInstance(Entry.class);
        if (entry != null) {
            return entry.resolve(getParentId());
        }
        return null;
    }

    public void setParentId(int i) {
        Block.putInteger(getBytesInternal(), 8, i);
    }

    public void setValuesCount(int i) {
        Block.putInteger(getBytesInternal(), 12, i);
    }

    @Override // com.reandroid.arsc.value.ValueHeader
    public void toJson(JSONObject jSONObject) {
        super.toJson(jSONObject);
        jSONObject.put(ValueHeader.NAME_is_complex, true);
        int parentId = getParentId();
        if (parentId != 0) {
            jSONObject.put(NAME_parent_id, parentId);
        }
    }

    @Override // com.reandroid.arsc.value.ValueHeader
    public String toString() {
        if (isNull()) {
            return PsiKeyword.NULL;
        }
        StringBuilder sb = new StringBuilder();
        int size = getSize();
        int size2 = readSize();
        if (size != 16) {
            sb.append("size=");
            sb.append(size);
        }
        if (size != size2) {
            sb.append(", readSize=");
            sb.append(size2);
        }
        if (isComplex()) {
            sb.append(" complex");
        }
        if (isPublic()) {
            sb.append(", public");
        }
        if (isWeak()) {
            sb.append(", weak");
        }
        String name = getName();
        if (name != null) {
            sb.append(", name=");
            sb.append(name);
        } else {
            sb.append(", key=");
            sb.append(getKey());
        }
        if (getParentId() != 0) {
            sb.append(", parentId=");
            sb.append(HexUtil.toHex8(getParentId()));
        }
        sb.append(", count=");
        sb.append(getValuesCount());
        return sb.toString();
    }
}
