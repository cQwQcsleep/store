package com.reandroid.dex.id;

import com.reandroid.dex.base.IndirectShort;
import com.reandroid.dex.base.UsageMarker;
import com.reandroid.dex.common.MethodHandleType;
import com.reandroid.dex.key.Key;
import com.reandroid.dex.key.MethodHandleKey;
import com.reandroid.dex.reference.IdItemIndirectReference;
import com.reandroid.dex.reference.MethodHandleTypeReference;
import com.reandroid.dex.sections.SectionType;
import com.reandroid.dex.smali.SmaliWriter;
import com.reandroid.utils.CompareUtil;
import com.reandroid.utils.collection.CombiningIterator;
import java.io.IOException;
import java.util.Iterator;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class MethodHandleId extends IdItem implements Comparable<MethodHandleId> {
    private final MethodHandleTypeReference handleType;
    private final IdItemIndirectReference<IdItem> member;
    private final IndirectShort unused1;
    private final IndirectShort unused2;

    public MethodHandleId() {
        super(8);
        final MethodHandleTypeReference methodHandleTypeReference = new MethodHandleTypeReference(this, 0);
        this.handleType = methodHandleTypeReference;
        this.unused1 = new IndirectShort(this, 2);
        this.member = new IdItemIndirectReference<IdItem>(null, this, 4, UsageMarker.USAGE_METHOD_HANDLE) { // from class: com.reandroid.dex.id.MethodHandleId.1
            @Override // com.reandroid.dex.reference.IdItemIndirectReference, com.reandroid.dex.reference.DexReference
            public SectionType<IdItem> getSectionType() {
                return methodHandleTypeReference.getSectionType();
            }
        };
        this.unused2 = new IndirectShort(this, 6);
    }

    public void append(SmaliWriter smaliWriter, boolean z) throws IOException {
        if (z) {
            MethodHandleType handleType = getHandleType();
            if (handleType == null) {
                smaliWriter.append("unknown handle = ");
                smaliWriter.append(this.handleType.get());
            } else {
                handleType.append(smaliWriter);
            }
        }
        smaliWriter.append('@');
        IdItem memberId = getMemberId();
        if (memberId != null) {
            memberId.append(smaliWriter);
        } else {
            smaliWriter.append("error id = ");
            smaliWriter.appendInteger(this.member.get());
        }
    }

    @Override // com.reandroid.dex.id.IdItem
    public void cacheItems() {
        this.member.pullItem();
    }

    @Override // java.lang.Comparable
    public int compareTo(MethodHandleId methodHandleId) {
        if (methodHandleId == null) {
            return -1;
        }
        int iCompare = CompareUtil.compare(getHandleType(), methodHandleId.getHandleType());
        if (iCompare != 0) {
            return iCompare;
        }
        IdItem memberId = getMemberId();
        return memberId instanceof FieldId ? CompareUtil.compare((FieldId) memberId, (FieldId) methodHandleId.getMemberId()) : CompareUtil.compare((MethodId) memberId, (MethodId) methodHandleId.getMemberId());
    }

    public MethodHandleType getHandleType() {
        return this.handleType.getHandleType();
    }

    @Override // com.reandroid.dex.id.IdItem, com.reandroid.dex.common.SectionItem, com.reandroid.dex.key.KeyItem
    public MethodHandleKey getKey() {
        return (MethodHandleKey) checkKey(new MethodHandleKey(getHandleType(), getMember()));
    }

    public Key getMember() {
        return this.member.getKey();
    }

    public IdItem getMemberId() {
        return this.member.getItem();
    }

    public SectionType<?> getMemberSectionType() {
        return this.handleType.getSectionType();
    }

    @Override // com.reandroid.dex.id.IdItem, com.reandroid.dex.common.SectionItem
    public SectionType<MethodHandleId> getSectionType() {
        return SectionType.METHOD_HANDLE;
    }

    public void refresh() {
        this.member.refresh();
    }

    public void set(MethodHandleType methodHandleType, Key key) {
        setMethodHandleType(methodHandleType);
        setMember(key);
    }

    @Override // com.reandroid.dex.id.IdItem, com.reandroid.dex.key.KeyReference
    public void setKey(Key key) {
        MethodHandleKey methodHandleKey = (MethodHandleKey) key;
        setMethodHandleType(methodHandleKey.getHandleType());
        setMember(methodHandleKey.getMember());
    }

    public void setMember(IdItem idItem) {
        this.member.setItem(idItem);
    }

    public void setMethodHandleType(MethodHandleType methodHandleType) {
        this.handleType.setMethodHandleType(methodHandleType);
    }

    public String toString() {
        String stringSafe = SmaliWriter.toStringSafe(this);
        if (this.unused1.get() == 0 && this.unused2.get() == 0) {
            return stringSafe;
        }
        return "unused1 = " + this.unused1.get() + ", unused2 = " + this.unused2.get() + ": " + stringSafe;
    }

    @Override // com.reandroid.dex.id.IdItem, com.reandroid.dex.common.IdUsageIterator
    public Iterator<IdItem> usedIds() {
        return CombiningIterator.singleOne(this, getMemberId().usedIds());
    }

    public void setMember(Key key) {
        this.member.setKey(key);
    }

    @Override // com.reandroid.dex.smali.SmaliFormat
    public void append(SmaliWriter smaliWriter) throws IOException {
        append(smaliWriter, true);
    }
}
