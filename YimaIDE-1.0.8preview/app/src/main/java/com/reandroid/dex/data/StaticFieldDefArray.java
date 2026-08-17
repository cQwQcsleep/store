package com.reandroid.dex.data;

import com.reandroid.arsc.item.IntegerReference;
import com.reandroid.dex.common.AccessFlag;
import com.reandroid.dex.id.ClassId;
import com.reandroid.dex.ins.Ins;
import com.reandroid.dex.ins.SizeXIns;
import com.reandroid.dex.key.ArrayValueKey;
import com.reandroid.dex.key.FieldKey;
import com.reandroid.dex.key.Key;
import com.reandroid.dex.key.MethodKey;
import com.reandroid.dex.smali.SmaliWriter;
import com.reandroid.dex.smali.model.Smali;
import com.reandroid.dex.value.DexValueType;
import com.reandroid.utils.ObjectsUtil;
import java.io.IOException;
import java.util.Iterator;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class StaticFieldDefArray extends FieldDefArray {
    private Object mLockedBy;

    public StaticFieldDefArray(IntegerReference integerReference) {
        super(integerReference);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private ArrayValueKey buildCachedKey() {
        int iLastCachedKeyIndex = lastCachedKeyIndex();
        if (iLastCachedKeyIndex < 0) {
            return null;
        }
        int i = iLastCachedKeyIndex + 1;
        Key[] keyArr = new Key[i];
        for (int i2 = 0; i2 < i; i2++) {
            FieldDef fieldDef = (FieldDef) get(i2);
            Key keyCachedStaticValue = fieldDef.cachedStaticValue();
            if (keyCachedStaticValue == null) {
                keyCachedStaticValue = DexValueType.createDefaultValue(fieldDef.getKey().getType());
            }
            keyArr[i2] = keyCachedStaticValue;
            fieldDef.cachedStaticValue(null);
        }
        return ArrayValueKey.of(keyArr);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private Object cacheStaticValues() {
        if (this.mLockedBy != null) {
            return null;
        }
        Object obj = new Object();
        this.mLockedBy = obj;
        EncodedArray encodedArray = getEncodedArray();
        if (encodedArray != null) {
            int size = size();
            for (int i = 0; i < size; i++) {
                ((FieldDef) get(i)).cachedStaticValue(encodedArray.getValueKey(i));
            }
        }
        return obj;
    }

    private EncodedArray getEncodedArray() {
        ClassId classId = getClassId();
        if (classId != null) {
            return classId.getStaticValuesEncodedArray();
        }
        return null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private int lastCachedKeyIndex() {
        int size = size();
        int i = -1;
        for (int i2 = 0; i2 < size; i2++) {
            if (((FieldDef) get(i2)).cachedStaticValue() != null) {
                i = i2;
            }
        }
        return i;
    }

    private void releaseStaticValues(Object obj) {
        ClassId classId;
        Object obj2 = this.mLockedBy;
        if (obj2 == null || obj2 != obj || (classId = getClassId()) == null) {
            return;
        }
        ArrayValueKey arrayValueKeyBuildCachedKey = buildCachedKey();
        if (!ObjectsUtil.equals(arrayValueKeyBuildCachedKey, classId.getStaticValues())) {
            classId.setStaticValues(arrayValueKeyBuildCachedKey);
        }
        this.mLockedBy = null;
    }

    @Override // com.reandroid.dex.data.DefArray, com.reandroid.dex.smali.SmaliFormat
    public void append(SmaliWriter smaliWriter) throws IOException {
        smaliWriter.setStateWritingFields(true);
        super.append(smaliWriter);
        smaliWriter.setStateWritingFields(false);
    }

    @Override // com.reandroid.dex.data.DefArray
    public FieldDef createNext() {
        FieldDef fieldDef = (FieldDef) super.createNext();
        fieldDef.addAccessFlag(AccessFlag.STATIC);
        return fieldDef;
    }

    @Override // com.reandroid.dex.data.DefArray
    public void fromSmali(Iterator<? extends Smali> it) {
        Object objCacheStaticValues = cacheStaticValues();
        super.fromSmali(it);
        releaseStaticValues(objCacheStaticValues);
    }

    public Key getStaticValue(FieldDef fieldDef) {
        if (this.mLockedBy != null) {
            return fieldDef.cachedStaticValue();
        }
        EncodedArray encodedArray = getEncodedArray();
        if (encodedArray != null) {
            return encodedArray.getValueKey(fieldDef.getIndex());
        }
        return null;
    }

    public boolean isInitializedInStaticConstructor(FieldDef fieldDef) {
        ClassId classId;
        MethodDef methodDef;
        InstructionList instructionList;
        FieldKey key = fieldDef.getKey();
        if (key == null || (classId = getClassId()) == null || (methodDef = (MethodDef) classId.getDef(MethodKey.STATIC_CONSTRUCTOR.changeDeclaring(key.getDeclaring()))) == null || (instructionList = methodDef.getInstructionList()) == null) {
            return false;
        }
        for (Ins ins : instructionList) {
            if (ins instanceof SizeXIns) {
                SizeXIns sizeXIns = (SizeXIns) ins;
                if (sizeXIns.getOpcode().isFieldPut() && key.equals(sizeXIns.getKey())) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override // com.reandroid.dex.data.DefArray
    public void merge(DefArray<FieldDef> defArray) {
        Object objCacheStaticValues = cacheStaticValues();
        super.merge(defArray);
        releaseStaticValues(objCacheStaticValues);
    }

    @Override // com.reandroid.dex.data.DefArray
    public void onMerged(FieldDef fieldDef, FieldDef fieldDef2) {
        Key staticValue = fieldDef2.getStaticValue();
        if (staticValue != null) {
            setStaticValue(fieldDef, staticValue);
        }
    }

    @Override // com.reandroid.dex.data.DefArray
    public void onPostSort(Object obj) {
        super.onPostSort(obj);
        releaseStaticValues(obj);
    }

    @Override // com.reandroid.dex.data.DefArray, com.reandroid.arsc.container.BlockList
    public void onPreRefresh() {
        super.onPreRefresh();
        releaseStaticValues(this.mLockedBy);
    }

    @Override // com.reandroid.dex.data.DefArray
    public Object onPreSort() {
        super.onPreSort();
        return cacheStaticValues();
    }

    @Override // com.reandroid.dex.data.DefArray, com.reandroid.arsc.container.BlockList
    public void onRemoveRequestCompleted(Object obj) {
        releaseStaticValues(obj);
        super.onRemoveRequestCompleted(obj);
    }

    @Override // com.reandroid.arsc.container.BlockList
    public Object onRemoveRequestStarted() {
        return cacheStaticValues();
    }

    public void setStaticValue(FieldDef fieldDef, Key key) {
        if (this.mLockedBy != null) {
            fieldDef.cachedStaticValue(key);
            return;
        }
        Object objCacheStaticValues = cacheStaticValues();
        fieldDef.cachedStaticValue(key);
        releaseStaticValues(objCacheStaticValues);
    }
}
