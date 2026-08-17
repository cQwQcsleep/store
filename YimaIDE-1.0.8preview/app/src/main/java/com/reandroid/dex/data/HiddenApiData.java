package com.reandroid.dex.data;

import com.reandroid.arsc.base.OffsetSupplier;
import com.reandroid.arsc.io.BlockReader;
import com.reandroid.arsc.item.IntegerReference;
import com.reandroid.arsc.item.NumberIntegerReference;
import com.reandroid.dex.base.DexException;
import com.reandroid.dex.base.FixedDexContainer;
import com.reandroid.dex.base.OffsetReceiver;
import com.reandroid.dex.id.ClassId;
import com.reandroid.dex.key.FieldKey;
import com.reandroid.dex.key.MethodKey;
import com.reandroid.dex.key.ProgramKey;
import com.reandroid.utils.ObjectsUtil;
import java.io.IOException;
import java.io.OutputStream;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
class HiddenApiData extends FixedDexContainer implements OffsetSupplier, OffsetReceiver {
    private ClassId classId;
    private HiddenApiFlagValueList directMethods;
    private HiddenApiFlagValueList instanceFields;
    private IntegerReference offsetReference;
    private HiddenApiFlagValueList staticFields;
    private HiddenApiFlagValueList virtualMethods;

    public static class Compact extends HiddenApiData {
        private final HiddenApiData source;

        public Compact(HiddenApiData hiddenApiData) {
            this.source = hiddenApiData;
        }

        public int countBytes() {
            return 0;
        }

        @Override // com.reandroid.dex.data.HiddenApiData
        public HiddenApiFlagValueList[] createHiddenApiFlagValueList() {
            return new HiddenApiFlagValueList[]{this.source.getStaticFields().newCompact(), this.source.getInstanceFields().newCompact(), this.source.getDirectMethods().newCompact(), this.source.getVirtualMethods().newCompact()};
        }

        @Override // com.reandroid.dex.data.HiddenApiData
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (obj instanceof Compact) {
                return this.source.equals(obj);
            }
            return false;
        }

        public byte[] getBytes() {
            return null;
        }

        @Override // com.reandroid.dex.data.HiddenApiData, com.reandroid.arsc.base.OffsetSupplier, com.reandroid.dex.base.DexArraySupplier
        public IntegerReference getOffsetReference() {
            return this.source.getOffsetReference();
        }

        @Override // com.reandroid.dex.data.HiddenApiData
        public int hashCode() {
            return this.source.hashCode() * 31;
        }

        @Override // com.reandroid.dex.data.HiddenApiData
        public HiddenApiData newCompact() {
            return this.source.newCompact();
        }

        @Override // com.reandroid.dex.data.HiddenApiData, com.reandroid.dex.base.FixedDexContainer
        public void onReadBytes(BlockReader blockReader) throws IOException {
        }

        public int onWriteBytes(OutputStream outputStream) throws IOException {
            return 0;
        }
    }

    public HiddenApiData() {
        super(4);
    }

    private void initializeValueList() {
        HiddenApiFlagValueList[] hiddenApiFlagValueListArrCreateHiddenApiFlagValueList = createHiddenApiFlagValueList();
        this.staticFields = hiddenApiFlagValueListArrCreateHiddenApiFlagValueList[0];
        this.instanceFields = hiddenApiFlagValueListArrCreateHiddenApiFlagValueList[1];
        this.directMethods = hiddenApiFlagValueListArrCreateHiddenApiFlagValueList[2];
        this.virtualMethods = hiddenApiFlagValueListArrCreateHiddenApiFlagValueList[3];
    }

    public HiddenApiFlagValueList[] createHiddenApiFlagValueList() {
        return new HiddenApiFlagValueList[]{new HiddenApiFlagValueList(), new HiddenApiFlagValueList(), new HiddenApiFlagValueList(), new HiddenApiFlagValueList()};
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && getClass() == obj.getClass()) {
            HiddenApiData hiddenApiData = (HiddenApiData) obj;
            if (ObjectsUtil.equals(this.staticFields, hiddenApiData.staticFields) && ObjectsUtil.equals(this.instanceFields, hiddenApiData.instanceFields) && ObjectsUtil.equals(this.directMethods, hiddenApiData.directMethods) && ObjectsUtil.equals(this.virtualMethods, hiddenApiData.virtualMethods)) {
                return true;
            }
        }
        return false;
    }

    public HiddenApiFlagValue get(Def<?> def) {
        HiddenApiFlagValueList flagValueList;
        if (def == null || (flagValueList = getFlagValueList(def)) == null) {
            return null;
        }
        return flagValueList.get(def);
    }

    public ClassId getClassId() {
        return this.classId;
    }

    public HiddenApiFlagValueList getDirectMethods() {
        return this.directMethods;
    }

    public HiddenApiFlagValueList getFlagValueList(Def<?> def) {
        ProgramKey key = def.getKey();
        if (key instanceof FieldKey) {
            return def.isStatic() ? getStaticFields() : getInstanceFields();
        }
        if (key instanceof MethodKey) {
            return ((MethodDef) def).isDirect() ? getDirectMethods() : getVirtualMethods();
        }
        return null;
    }

    public HiddenApiFlagValueList getInstanceFields() {
        return this.instanceFields;
    }

    public int getOffset() {
        IntegerReference offsetReference = getOffsetReference();
        if (offsetReference != null) {
            return offsetReference.get();
        }
        return 0;
    }

    @Override // com.reandroid.arsc.base.OffsetSupplier, com.reandroid.dex.base.DexArraySupplier
    public IntegerReference getOffsetReference() {
        return this.offsetReference;
    }

    public HiddenApiFlagValueList getStaticFields() {
        return this.staticFields;
    }

    public HiddenApiFlagValueList getVirtualMethods() {
        return this.virtualMethods;
    }

    public int hashCode() {
        return ObjectsUtil.hash(this.staticFields, this.instanceFields, this.directMethods, this.virtualMethods);
    }

    public boolean isAllNoRestrictions() {
        HiddenApiFlagValueList hiddenApiFlagValueList = this.staticFields;
        if (hiddenApiFlagValueList != null && !hiddenApiFlagValueList.isAllNoRestrictions()) {
            return false;
        }
        HiddenApiFlagValueList hiddenApiFlagValueList2 = this.instanceFields;
        if (hiddenApiFlagValueList2 != null && !hiddenApiFlagValueList2.isAllNoRestrictions()) {
            return false;
        }
        HiddenApiFlagValueList hiddenApiFlagValueList3 = this.directMethods;
        if (hiddenApiFlagValueList3 != null && !hiddenApiFlagValueList3.isAllNoRestrictions()) {
            return false;
        }
        HiddenApiFlagValueList hiddenApiFlagValueList4 = this.virtualMethods;
        return hiddenApiFlagValueList4 == null || hiddenApiFlagValueList4.isAllNoRestrictions();
    }

    public void linkDefArray(ClassId classId) {
        ClassData classData = classId.getClassData();
        if (classData == null) {
            return;
        }
        this.staticFields.linkDefArray(classData.getStaticFieldsArray());
        this.instanceFields.linkDefArray(classData.getInstanceFieldsArray());
        this.directMethods.linkDefArray(classData.getDirectMethodsArray());
        this.virtualMethods.linkDefArray(classData.getVirtualMethodArray());
    }

    public HiddenApiData newCompact() {
        return new Compact(this);
    }

    @Override // com.reandroid.dex.base.FixedDexContainer
    public void onReadBytes(BlockReader blockReader) throws IOException {
        int offset = getOffset();
        if (offset == 0) {
            a16.a("Can not read at zero");
        } else {
            blockReader.seek(offset);
            super.onReadBytes(blockReader);
        }
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: com.reandroid.dex.base.DexException */
    public void setClassId(ClassId classId) throws DexException {
        ClassId classId2 = this.classId;
        if (classId2 == classId) {
            return;
        }
        if (classId2 != null) {
            throw new DexException("Invalid class link state");
        }
        this.classId = classId;
        initializeValueList();
        addChild(0, this.staticFields);
        addChild(1, this.instanceFields);
        addChild(2, this.directMethods);
        addChild(3, this.virtualMethods);
        linkDefArray(classId);
    }

    public void setOffset(int i) {
        IntegerReference offsetReference = getOffsetReference();
        if (offsetReference == null) {
            offsetReference = new NumberIntegerReference();
            setOffsetReference(offsetReference);
        }
        offsetReference.set(i);
    }

    @Override // com.reandroid.dex.base.OffsetReceiver
    public void setOffsetReference(IntegerReference integerReference) {
        this.offsetReference = integerReference;
    }
}
