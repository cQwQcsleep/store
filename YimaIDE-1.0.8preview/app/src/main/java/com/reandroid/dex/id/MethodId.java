package com.reandroid.dex.id;

import com.reandroid.dex.base.UsageMarker;
import com.reandroid.dex.data.TypeList;
import com.reandroid.dex.key.Key;
import com.reandroid.dex.key.MethodKey;
import com.reandroid.dex.key.ProtoKey;
import com.reandroid.dex.key.StringKey;
import com.reandroid.dex.key.TypeKey;
import com.reandroid.dex.key.TypeListKey;
import com.reandroid.dex.reference.IdItemIndirectReference;
import com.reandroid.dex.reference.IdItemIndirectShortReference;
import com.reandroid.dex.reference.IdReference;
import com.reandroid.dex.reference.IndirectStringReference;
import com.reandroid.dex.sections.SectionType;
import com.reandroid.dex.smali.SmaliWriter;
import com.reandroid.utils.ObjectsUtil;
import com.reandroid.utils.collection.CombiningIterator;
import com.reandroid.utils.collection.SingleIterator;
import java.io.IOException;
import java.util.Iterator;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class MethodId extends IdItem implements Comparable<MethodId> {
    private static final int SIZE = 8;
    private final IdItemIndirectReference<TypeId> defining;
    private final IndirectStringReference nameReference;
    private final IdItemIndirectReference<ProtoId> proto;

    public MethodId() {
        super(8);
        SectionType<TypeId> sectionType = SectionType.TYPE_ID;
        int i = UsageMarker.USAGE_METHOD;
        this.defining = new IdItemIndirectShortReference(sectionType, this, 0, i);
        this.proto = new IdItemIndirectShortReference(SectionType.PROTO_ID, this, 2, i);
        this.nameReference = new IndirectStringReference(this, 4, UsageMarker.USAGE_METHOD_NAME);
    }

    public static boolean equals(boolean z, MethodId methodId, MethodId methodId2) {
        if (methodId == methodId2) {
            return true;
        }
        if (methodId == null || !IndirectStringReference.equals(methodId.getNameReference(), methodId2.getNameReference())) {
            return false;
        }
        if (z || TypeId.equals(methodId.getDefiningId(), methodId2.getDefiningId())) {
            return ObjectsUtil.equals(methodId.getParameterTypes(), methodId2.getParameterTypes());
        }
        return false;
    }

    public void append(SmaliWriter smaliWriter, boolean z) throws IOException {
        if (z) {
            getDefiningId().append(smaliWriter);
            smaliWriter.append("->");
        }
        smaliWriter.append((CharSequence) getName());
        smaliWriter.appendRequired(getProtoId());
    }

    @Override // com.reandroid.dex.id.IdItem
    public void cacheItems() {
        this.defining.pullItem();
        this.proto.pullItem();
        this.nameReference.pullItem();
    }

    @Override // java.lang.Comparable
    public int compareTo(MethodId methodId) {
        if (methodId == null) {
            return -1;
        }
        int iCompareTo = this.defining.compareTo((IdReference) methodId.defining);
        if (iCompareTo != 0) {
            return iCompareTo;
        }
        int iCompareTo2 = this.nameReference.compareTo((IdReference) methodId.nameReference);
        return iCompareTo2 != 0 ? iCompareTo2 : this.proto.compareTo((IdReference) methodId.proto);
    }

    public TypeKey getDefining() {
        return (TypeKey) this.defining.getKey();
    }

    public TypeId getDefiningId() {
        return (TypeId) this.defining.getItem();
    }

    @Override // com.reandroid.dex.id.IdItem, com.reandroid.dex.common.SectionItem, com.reandroid.dex.key.KeyItem
    public MethodKey getKey() {
        return (MethodKey) checkKey(MethodKey.create(getDefining(), getNameKey(), getProto()));
    }

    public String getName() {
        return this.nameReference.getString();
    }

    public StringKey getNameKey() {
        return this.nameReference.getKey();
    }

    public IndirectStringReference getNameReference() {
        return this.nameReference;
    }

    public int getParameterRegistersCount() {
        ProtoId protoId = getProtoId();
        if (protoId != null) {
            return protoId.getParameterRegistersCount();
        }
        return 0;
    }

    public TypeList getParameterTypes() {
        ProtoId protoId = getProtoId();
        if (protoId != null) {
            return protoId.getTypeList();
        }
        return null;
    }

    public TypeListKey getParameters() {
        ProtoId protoId = getProtoId();
        if (protoId != null) {
            return protoId.getParameters();
        }
        return null;
    }

    public int getParametersCount() {
        ProtoId protoId = getProtoId();
        if (protoId != null) {
            return protoId.getParametersCount();
        }
        return 0;
    }

    public ProtoKey getProto() {
        return (ProtoKey) this.proto.getKey();
    }

    public ProtoId getProtoId() {
        return (ProtoId) this.proto.getItem();
    }

    public TypeKey getReturnType() {
        ProtoId protoId = getProtoId();
        if (protoId != null) {
            return protoId.getReturnType();
        }
        return null;
    }

    @Override // com.reandroid.dex.id.IdItem, com.reandroid.dex.common.SectionItem
    public SectionType<MethodId> getSectionType() {
        return SectionType.METHOD_ID;
    }

    public void refresh() {
        this.defining.refresh();
        this.proto.refresh();
        this.nameReference.refresh();
    }

    public void setKey(MethodKey methodKey) {
        MethodKey key = getKey();
        if (methodKey.equals(key)) {
            return;
        }
        this.defining.setKey(methodKey.getDeclaring());
        this.nameReference.setKey(methodKey.getNameKey());
        this.proto.setKey(methodKey.getProto());
        keyChanged(key);
    }

    public void setName(StringKey stringKey) {
        MethodKey key = getKey();
        if (key == null) {
            this.nameReference.setKey(stringKey);
        } else {
            setKey(key.changeName(stringKey));
        }
    }

    public String toString() {
        return getDefiningId() + "->" + getName() + getProtoId();
    }

    @Override // com.reandroid.dex.id.IdItem, com.reandroid.dex.common.IdUsageIterator
    public Iterator<IdItem> usedIds() {
        return CombiningIterator.singleThree(this, SingleIterator.of((TypeId) this.defining.getItem()), SingleIterator.of(this.nameReference.getItem()), ((ProtoId) this.proto.getItem()).usedIds());
    }

    public void setName(String str) {
        setName(StringKey.create(str));
    }

    @Override // com.reandroid.dex.smali.SmaliFormat
    public void append(SmaliWriter smaliWriter) throws IOException {
        append(smaliWriter, true);
    }

    @Override // com.reandroid.dex.id.IdItem, com.reandroid.dex.key.KeyReference
    public void setKey(Key key) {
        setKey((MethodKey) key);
    }

    public static boolean equals(MethodId methodId, MethodId methodId2) {
        return equals(false, methodId, methodId2);
    }
}
