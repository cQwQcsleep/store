package com.reandroid.dex.id;

import com.reandroid.dex.base.UsageMarker;
import com.reandroid.dex.data.EncodedArray;
import com.reandroid.dex.key.ArrayKey;
import com.reandroid.dex.key.ArrayValueKey;
import com.reandroid.dex.key.CallSiteKey;
import com.reandroid.dex.key.Key;
import com.reandroid.dex.key.MethodHandleKey;
import com.reandroid.dex.key.ProtoKey;
import com.reandroid.dex.key.StringKey;
import com.reandroid.dex.reference.DataItemIndirectReference;
import com.reandroid.dex.sections.SectionType;
import com.reandroid.dex.smali.SmaliWriter;
import com.reandroid.dex.value.DexValueBlock;
import com.reandroid.dex.value.SectionValue;
import com.reandroid.utils.CompareUtil;
import com.reandroid.utils.ObjectsUtil;
import com.reandroid.utils.collection.EmptyIterator;
import java.io.IOException;
import java.util.Iterator;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class CallSiteId extends IdItem implements Comparable<CallSiteId> {
    public static final String NAME_PREFIX = ObjectsUtil.of("call_site_");
    private final DataItemIndirectReference<EncodedArray> encodedArrayReference;

    public CallSiteId() {
        super(4);
        this.encodedArrayReference = new DataItemIndirectReference<>(SectionType.ENCODED_ARRAY, this, 0, UsageMarker.USAGE_CALL_SITE);
    }

    private <T1 extends IdItem> T1 getValue(SectionType<T1> sectionType, int i) {
        EncodedArray encodedArray = getEncodedArray();
        if (encodedArray == null) {
            return null;
        }
        DexValueBlock<?> dexValueBlock = encodedArray.get(i);
        if (!(dexValueBlock instanceof SectionValue)) {
            return null;
        }
        SectionValue sectionValue = (SectionValue) dexValueBlock;
        if (sectionValue.getSectionType() != sectionType) {
            return null;
        }
        return (T1) sectionValue.getItem();
    }

    @Override // com.reandroid.dex.smali.SmaliFormat
    public void append(SmaliWriter smaliWriter) throws IOException {
        smaliWriter.append((CharSequence) callSiteName());
        smaliWriter.append('(');
        getMethodNameId().append(smaliWriter);
        smaliWriter.append(", ");
        getProtoId().append(smaliWriter);
        Iterator<DexValueBlock<?>> argumentValues = getArgumentValues();
        while (argumentValues.hasNext()) {
            smaliWriter.append(", ");
            argumentValues.next().append(smaliWriter);
        }
        smaliWriter.append(')');
        getMethodHandle().append(smaliWriter, false);
    }

    @Override // com.reandroid.dex.id.IdItem
    public void cacheItems() {
        this.encodedArrayReference.pullItem();
    }

    public String callSiteName() {
        return NAME_PREFIX + getIdx();
    }

    @Override // java.lang.Comparable
    public int compareTo(CallSiteId callSiteId) {
        if (callSiteId == this) {
            return 0;
        }
        if (callSiteId == null) {
            return -1;
        }
        int iCompare = CompareUtil.compare(getMethodHandleId(), callSiteId.getMethodHandleId());
        if (iCompare != 0) {
            return iCompare;
        }
        int iCompare2 = CompareUtil.compare(getArguments(), callSiteId.getArguments());
        if (iCompare2 != 0) {
            return iCompare2;
        }
        int iCompare3 = CompareUtil.compare(getMethodNameId(), callSiteId.getMethodNameId());
        return iCompare3 != 0 ? iCompare3 : CompareUtil.compare(getProtoId(), callSiteId.getProtoId());
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return ObjectsUtil.equals(getEncodedArray(), ((CallSiteId) obj).getEncodedArray());
    }

    public Key getArgument(int i) {
        DexValueBlock<?> argumentValue = getArgumentValue(i);
        if (argumentValue != null) {
            return argumentValue.getKey();
        }
        return null;
    }

    public DexValueBlock<?> getArgumentValue(int i) {
        EncodedArray encodedArray;
        if (i < 0 || (encodedArray = getEncodedArray()) == null) {
            return null;
        }
        return encodedArray.get(i + 3);
    }

    public Iterator<DexValueBlock<?>> getArgumentValues() {
        EncodedArray encodedArray = getEncodedArray();
        return encodedArray != null ? encodedArray.iterator(3, encodedArray.size() - 3) : EmptyIterator.of();
    }

    public ArrayValueKey getArguments() {
        int argumentsSize = getArgumentsSize();
        Key[] keyArr = new Key[argumentsSize];
        for (int i = 0; i < argumentsSize; i++) {
            keyArr[i] = getArgument(i);
        }
        return ArrayValueKey.of(keyArr);
    }

    public int getArgumentsSize() {
        int size;
        EncodedArray encodedArray = getEncodedArray();
        if (encodedArray == null || (size = encodedArray.size()) <= 0) {
            return 0;
        }
        return size - 3;
    }

    public EncodedArray getEncodedArray() {
        return (EncodedArray) this.encodedArrayReference.getItem();
    }

    @Override // com.reandroid.dex.id.IdItem, com.reandroid.dex.common.SectionItem, com.reandroid.dex.key.KeyItem
    public CallSiteKey getKey() {
        return (CallSiteKey) checkKey(new CallSiteKey(getMethodHandle(), getMethodName(), getProto(), getArguments()));
    }

    public MethodHandleKey getMethodHandle() {
        return getMethodHandleId().getKey();
    }

    public MethodHandleId getMethodHandleId() {
        return (MethodHandleId) getValue(SectionType.METHOD_HANDLE, 0);
    }

    public StringKey getMethodName() {
        StringId methodNameId = getMethodNameId();
        if (methodNameId != null) {
            return methodNameId.getKey();
        }
        return null;
    }

    public StringId getMethodNameId() {
        return (StringId) getValue(SectionType.STRING_ID, 1);
    }

    public EncodedArray getOrCreateEncodedArray() {
        return (EncodedArray) this.encodedArrayReference.getOrCreate();
    }

    public ProtoKey getProto() {
        ProtoId protoId = getProtoId();
        if (protoId != null) {
            return protoId.getKey();
        }
        return null;
    }

    public ProtoId getProtoId() {
        return (ProtoId) getValue(SectionType.PROTO_ID, 2);
    }

    @Override // com.reandroid.dex.id.IdItem, com.reandroid.dex.common.SectionItem
    public SectionType<CallSiteId> getSectionType() {
        return SectionType.CALL_SITE_ID;
    }

    public int hashCode() {
        EncodedArray encodedArray = getEncodedArray();
        if (encodedArray != null) {
            return encodedArray.hashCode();
        }
        return 0;
    }

    public void merge(CallSiteId callSiteId) {
        if (callSiteId == this) {
            return;
        }
        getOrCreateEncodedArray().merge(callSiteId.getEncodedArray());
    }

    public void refresh() {
        this.encodedArrayReference.refresh();
    }

    public void setArguments(ArrayKey<?> arrayKey) {
        if (arrayKey.equals(getArguments())) {
            return;
        }
        setKey(getKey().changeArguments(arrayKey));
    }

    @Override // com.reandroid.dex.id.IdItem, com.reandroid.dex.key.KeyReference
    public void setKey(Key key) {
        this.encodedArrayReference.setKey(((CallSiteKey) key).toArrayKey());
    }

    public void setMethodHandle(MethodHandleKey methodHandleKey) {
        if (methodHandleKey.equals(getMethodHandle())) {
            return;
        }
        setKey(getKey().changeMethodHandle(methodHandleKey));
    }

    public void setMethodName(StringKey stringKey) {
        if (stringKey.equals(getMethodName())) {
            return;
        }
        setKey(getKey().changeName(stringKey));
    }

    public void setProto(ProtoKey protoKey) {
        if (protoKey.equals(getProto())) {
            return;
        }
        setKey(getKey().changeProto(protoKey));
    }

    public String toString() {
        return SmaliWriter.toStringSafe(this);
    }

    @Override // com.reandroid.dex.id.IdItem, com.reandroid.dex.common.IdUsageIterator
    public Iterator<IdItem> usedIds() {
        EncodedArray encodedArray = getEncodedArray();
        return encodedArray == null ? EmptyIterator.of() : encodedArray.usedIds();
    }

    public void setMethodName(String str) {
        setMethodName(StringKey.create(str));
    }
}
