package com.reandroid.dex.id;

import com.reandroid.dex.base.UsageMarker;
import com.reandroid.dex.common.SectionTool;
import com.reandroid.dex.data.TypeList;
import com.reandroid.dex.key.Key;
import com.reandroid.dex.key.ProtoKey;
import com.reandroid.dex.key.StringKey;
import com.reandroid.dex.key.TypeKey;
import com.reandroid.dex.key.TypeListKey;
import com.reandroid.dex.reference.IdItemIndirectReference;
import com.reandroid.dex.reference.IndirectStringReference;
import com.reandroid.dex.reference.TypeListReference;
import com.reandroid.dex.sections.SectionType;
import com.reandroid.dex.smali.SmaliWriter;
import com.reandroid.utils.collection.CombiningIterator;
import com.reandroid.utils.collection.EmptyIterator;
import com.reandroid.utils.collection.SingleIterator;
import java.io.IOException;
import java.util.Iterator;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class ProtoId extends IdItem implements Comparable<ProtoId> {
    private static final int SIZE = 12;
    private final TypeListReference parameters;
    private final IdItemIndirectReference<TypeId> returnType;
    private final IndirectStringReference shorty;

    public ProtoId() {
        super(12);
        this.shorty = new IndirectStringReference(this, 0, UsageMarker.USAGE_SHORTY);
        SectionType<TypeId> sectionType = SectionType.TYPE_ID;
        int i = UsageMarker.USAGE_PROTO;
        this.returnType = new IdItemIndirectReference<>(sectionType, this, 4, i);
        this.parameters = new TypeListReference(this, 8, i);
    }

    @Override // com.reandroid.dex.smali.SmaliFormat
    public void append(SmaliWriter smaliWriter) throws IOException {
        smaliWriter.append('(');
        Iterator<TypeId> parameterIds = getParameterIds();
        while (parameterIds.hasNext()) {
            parameterIds.next().append(smaliWriter);
        }
        smaliWriter.append(')');
        smaliWriter.appendRequired(getReturnTypeId());
    }

    @Override // com.reandroid.dex.id.IdItem
    public void cacheItems() {
        this.shorty.pullItem();
        this.returnType.pullItem();
        this.parameters.pullItem();
    }

    @Override // java.lang.Comparable
    public int compareTo(ProtoId protoId) {
        if (protoId == this) {
            return 0;
        }
        if (protoId == null) {
            return -1;
        }
        int iCompareIdx = SectionTool.compareIdx(getReturnTypeId(), protoId.getReturnTypeId());
        return iCompareIdx != 0 ? iCompareIdx : SectionTool.compareIdx(getParameterIds(), protoId.getParameterIds());
    }

    @Override // com.reandroid.dex.id.IdItem, com.reandroid.dex.common.SectionItem, com.reandroid.dex.key.KeyItem
    public ProtoKey getKey() {
        return (ProtoKey) checkKey(ProtoKey.create(getParameters(), getReturnType()));
    }

    public TypeId getParameter(int i) {
        return this.parameters.get(i);
    }

    public Iterator<TypeId> getParameterIds() {
        TypeList typeList = getTypeList();
        return typeList != null ? typeList.iterator() : EmptyIterator.of();
    }

    public int getParameterRegistersCount() {
        TypeList typeList = getTypeList();
        if (typeList != null) {
            return typeList.getParameterRegistersCount();
        }
        return 0;
    }

    public TypeListKey getParameters() {
        return this.parameters.getKey();
    }

    public int getParametersCount() {
        TypeList typeList = getTypeList();
        if (typeList != null) {
            return typeList.size();
        }
        return 0;
    }

    public TypeKey getReturnType() {
        return (TypeKey) this.returnType.getKey();
    }

    public TypeId getReturnTypeId() {
        return (TypeId) this.returnType.getItem();
    }

    @Override // com.reandroid.dex.id.IdItem, com.reandroid.dex.common.SectionItem
    public SectionType<ProtoId> getSectionType() {
        return SectionType.PROTO_ID;
    }

    public String getShorty() {
        return this.shorty.getString();
    }

    public TypeList getTypeList() {
        return this.parameters.getItem();
    }

    public void rebuildShorty() {
        this.shorty.setString(getKey().getShorty());
    }

    public void refresh() {
        this.shorty.refresh();
        this.returnType.refresh();
        this.parameters.refresh();
    }

    public void setKey(ProtoKey protoKey) {
        ProtoKey key = getKey();
        if (protoKey.equals(key)) {
            return;
        }
        this.shorty.setString(protoKey.getShorty());
        this.returnType.setKey(protoKey.getReturnType());
        TypeListKey parameters = protoKey.getParameters();
        if (parameters != null && parameters.isEmpty()) {
            parameters = null;
        }
        this.parameters.setKey(parameters);
        keyChanged(key);
    }

    public void setShorty(StringKey stringKey) {
        this.shorty.setKey(stringKey);
    }

    public String toString() {
        ProtoKey key = getKey();
        if (key != null) {
            return key.toString();
        }
        return "(" + getTypeList() + ")" + getReturnTypeId();
    }

    @Override // com.reandroid.dex.id.IdItem, com.reandroid.dex.common.IdUsageIterator
    public Iterator<IdItem> usedIds() {
        return CombiningIterator.singleThree(this, SingleIterator.of((TypeId) this.returnType.getItem()), getParameterIds(), SingleIterator.of(this.shorty.getItem()));
    }

    public void setShorty(String str) {
        this.shorty.setString(str);
    }

    @Override // com.reandroid.dex.id.IdItem, com.reandroid.dex.key.KeyReference
    public void setKey(Key key) {
        setKey((ProtoKey) key);
    }
}
