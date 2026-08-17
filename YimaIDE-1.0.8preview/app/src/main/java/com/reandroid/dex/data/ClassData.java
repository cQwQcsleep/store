package com.reandroid.dex.data;

import com.reandroid.arsc.base.Block;
import com.reandroid.arsc.io.BlockReader;
import com.reandroid.dex.base.Ule128Item;
import com.reandroid.dex.common.AccessFlag;
import com.reandroid.dex.id.ClassId;
import com.reandroid.dex.id.IdItem;
import com.reandroid.dex.key.FieldKey;
import com.reandroid.dex.key.Key;
import com.reandroid.dex.key.MethodKey;
import com.reandroid.dex.sections.SectionType;
import com.reandroid.dex.smali.SmaliFormat;
import com.reandroid.dex.smali.SmaliWriter;
import com.reandroid.dex.smali.model.SmaliClass;
import com.reandroid.dex.smali.model.SmaliField;
import com.reandroid.dex.smali.model.SmaliMethod;
import com.reandroid.utils.ObjectsUtil;
import com.reandroid.utils.collection.CombiningIterator;
import com.reandroid.utils.collection.EmptyIterator;
import com.reandroid.utils.collection.IterableIterator;
import com.reandroid.utils.collection.SingleIterator;
import java.io.IOException;
import java.util.Iterator;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class ClassData extends DataItem implements SmaliFormat {
    private MethodDefArray directMethods;
    private final Ule128Item directMethodsCount;
    private FieldDefArray instanceFields;
    private final Ule128Item instanceFieldsCount;
    private ClassId mClassId;
    private StaticFieldDefArray staticFields;
    private final Ule128Item staticFieldsCount;
    private final Ule128Item virtualMethodCount;
    private MethodDefArray virtualMethods;

    public ClassData() {
        super(8);
        Ule128Item ule128Item = new Ule128Item();
        this.staticFieldsCount = ule128Item;
        Ule128Item ule128Item2 = new Ule128Item();
        this.instanceFieldsCount = ule128Item2;
        Ule128Item ule128Item3 = new Ule128Item();
        this.directMethodsCount = ule128Item3;
        Ule128Item ule128Item4 = new Ule128Item();
        this.virtualMethodCount = ule128Item4;
        addChildBlock(0, ule128Item);
        addChildBlock(1, ule128Item2);
        addChildBlock(2, ule128Item3);
        addChildBlock(3, ule128Item4);
    }

    private Iterator<DefArray<?>> getDefArrays() {
        return CombiningIterator.four(SingleIterator.of(this.staticFields), SingleIterator.of(this.instanceFields), SingleIterator.of(this.directMethods), SingleIterator.of(this.virtualMethods));
    }

    private MethodDefArray initDirectMethodsArray() {
        MethodDefArray methodDefArray = this.directMethods;
        if (methodDefArray != null) {
            return methodDefArray;
        }
        MethodDefArray methodDefArray2 = new MethodDefArray(this.directMethodsCount);
        this.directMethods = methodDefArray2;
        addChildBlock(6, methodDefArray2);
        methodDefArray2.setClassId(getClassId());
        return methodDefArray2;
    }

    private FieldDefArray initInstanceFieldsArray() {
        FieldDefArray fieldDefArray = this.instanceFields;
        if (fieldDefArray != null) {
            return fieldDefArray;
        }
        FieldDefArray fieldDefArray2 = new FieldDefArray(this.instanceFieldsCount);
        this.instanceFields = fieldDefArray2;
        addChildBlock(5, fieldDefArray2);
        fieldDefArray2.setClassId(getClassId());
        return fieldDefArray2;
    }

    private FieldDefArray initStaticFieldsArray() {
        StaticFieldDefArray staticFieldDefArray = this.staticFields;
        if (staticFieldDefArray != null) {
            return staticFieldDefArray;
        }
        StaticFieldDefArray staticFieldDefArray2 = new StaticFieldDefArray(this.staticFieldsCount);
        this.staticFields = staticFieldDefArray2;
        addChildBlock(4, staticFieldDefArray2);
        staticFieldDefArray2.setClassId(getClassId());
        return staticFieldDefArray2;
    }

    private MethodDefArray initVirtualMethodsArray() {
        MethodDefArray methodDefArray = this.virtualMethods;
        if (methodDefArray != null) {
            return methodDefArray;
        }
        MethodDefArray methodDefArray2 = new MethodDefArray(this.virtualMethodCount);
        this.virtualMethods = methodDefArray2;
        addChildBlock(7, methodDefArray2);
        methodDefArray2.setClassId(getClassId());
        return methodDefArray2;
    }

    private static boolean isNullOrEmpty(DefArray<?> defArray) {
        return defArray == null || defArray.size() == 0;
    }

    @Override // com.reandroid.dex.smali.SmaliFormat
    public void append(SmaliWriter smaliWriter) throws IOException {
        smaliWriter.appendOptional(this.staticFields, "static fields");
        smaliWriter.appendOptional(this.instanceFields, "instance fields");
        smaliWriter.appendOptional(this.directMethods, "direct methods");
        smaliWriter.appendOptional(this.virtualMethods, "virtual methods");
    }

    @Override // com.reandroid.dex.common.SectionItem, com.reandroid.dex.common.EditableItem
    public void editInternal(Block block) {
        StaticFieldDefArray staticFieldDefArray = this.staticFields;
        if (staticFieldDefArray != null) {
            staticFieldDefArray.editInternal(block);
        }
        FieldDefArray fieldDefArray = this.instanceFields;
        if (fieldDefArray != null) {
            fieldDefArray.editInternal(block);
        }
        MethodDefArray methodDefArray = this.directMethods;
        if (methodDefArray != null) {
            methodDefArray.editInternal(block);
        }
        MethodDefArray methodDefArray2 = this.virtualMethods;
        if (methodDefArray2 != null) {
            methodDefArray2.editInternal(block);
        }
    }

    public void fromSmali(SmaliClass smaliClass) throws IOException {
        Iterator<SmaliField> staticFields = smaliClass.getStaticFields();
        if (staticFields.hasNext()) {
            initStaticFieldsArray().fromSmali(staticFields);
        }
        Iterator<SmaliField> instanceFields = smaliClass.getInstanceFields();
        if (instanceFields.hasNext()) {
            initInstanceFieldsArray().fromSmali(instanceFields);
        }
        Iterator<SmaliMethod> directMethods = smaliClass.getDirectMethods();
        if (directMethods.hasNext()) {
            initDirectMethodsArray().fromSmali(directMethods);
        }
        Iterator<SmaliMethod> virtualMethods = smaliClass.getVirtualMethods();
        if (virtualMethods.hasNext()) {
            initVirtualMethodsArray().fromSmali(virtualMethods);
        }
    }

    public Def<?> get(Key key) {
        if (key instanceof FieldKey) {
            return getField((FieldKey) key);
        }
        if (key instanceof MethodKey) {
            return getMethod((MethodKey) key);
        }
        if (key == null) {
            return null;
        }
        throw new RuntimeException("Unknown key type: " + key.getClass() + ", '" + key + "'");
    }

    public ClassId getClassId() {
        return this.mClassId;
    }

    public Iterator<MethodDef> getDirectMethods() {
        MethodDefArray methodDefArray = this.directMethods;
        return methodDefArray == null ? EmptyIterator.of() : methodDefArray.arrayIterator();
    }

    public MethodDefArray getDirectMethodsArray() {
        return this.directMethods;
    }

    public FieldDef getField(FieldKey fieldKey) {
        FieldDefArray fieldDefArray;
        StaticFieldDefArray staticFieldDefArray = this.staticFields;
        FieldDef fieldDef = staticFieldDefArray != null ? staticFieldDefArray.get(fieldKey) : null;
        return (fieldDef != null || (fieldDefArray = this.instanceFields) == null) ? fieldDef : fieldDefArray.get(fieldKey);
    }

    public Iterator<FieldDef> getFields() {
        return new CombiningIterator(getStaticFields(), getInstanceFields());
    }

    public Iterator<FieldDef> getInstanceFields() {
        FieldDefArray fieldDefArray = this.instanceFields;
        return fieldDefArray == null ? EmptyIterator.of() : fieldDefArray.arrayIterator();
    }

    public FieldDefArray getInstanceFieldsArray() {
        return this.instanceFields;
    }

    public MethodDef getMethod(MethodKey methodKey) {
        MethodDefArray methodDefArray;
        MethodDefArray methodDefArray2 = this.directMethods;
        MethodDef methodDef = methodDefArray2 != null ? methodDefArray2.get(methodKey) : null;
        return (methodDef != null || (methodDefArray = this.virtualMethods) == null) ? methodDef : methodDefArray.get(methodKey);
    }

    public Iterator<MethodDef> getMethods() {
        return new CombiningIterator(getDirectMethods(), getVirtualMethods());
    }

    public MethodDef getOrCreateDirect(MethodKey methodKey) {
        return initDirectMethodsArray().getOrCreate(methodKey);
    }

    public FieldDef getOrCreateInstance(FieldKey fieldKey) {
        return initInstanceFieldsArray().getOrCreate(fieldKey);
    }

    public FieldDef getOrCreateStatic(FieldKey fieldKey) {
        FieldDef orCreate = initStaticFieldsArray().getOrCreate(fieldKey);
        orCreate.addAccessFlag(AccessFlag.STATIC);
        return orCreate;
    }

    public MethodDef getOrCreateVirtual(MethodKey methodKey) {
        return initVirtualMethodsArray().getOrCreate(methodKey);
    }

    @Override // com.reandroid.dex.common.SectionItem
    public SectionType<ClassData> getSectionType() {
        return SectionType.CLASS_DATA;
    }

    public Iterator<FieldDef> getStaticFields() {
        StaticFieldDefArray staticFieldDefArray = this.staticFields;
        return staticFieldDefArray == null ? EmptyIterator.of() : staticFieldDefArray.arrayIterator();
    }

    public StaticFieldDefArray getStaticFieldsArray() {
        return this.staticFields;
    }

    public MethodDefArray getVirtualMethodArray() {
        return this.virtualMethods;
    }

    public Iterator<MethodDef> getVirtualMethods() {
        MethodDefArray methodDefArray = this.virtualMethods;
        return methodDefArray == null ? EmptyIterator.of() : methodDefArray.arrayIterator();
    }

    @Override // com.reandroid.dex.common.SectionItem
    public boolean isBlank() {
        return isNullOrEmpty(this.staticFields) && isNullOrEmpty(this.instanceFields) && isNullOrEmpty(this.directMethods) && isNullOrEmpty(this.virtualMethods);
    }

    public void merge(ClassData classData) {
        ClassId classId = getClassId();
        if (!isNullOrEmpty(classData.staticFields)) {
            FieldDefArray fieldDefArrayInitStaticFieldsArray = initStaticFieldsArray();
            fieldDefArrayInitStaticFieldsArray.setClassId(classId);
            fieldDefArrayInitStaticFieldsArray.merge(classData.staticFields);
        }
        if (!isNullOrEmpty(classData.instanceFields)) {
            FieldDefArray fieldDefArrayInitInstanceFieldsArray = initInstanceFieldsArray();
            fieldDefArrayInitInstanceFieldsArray.setClassId(classId);
            fieldDefArrayInitInstanceFieldsArray.merge(classData.instanceFields);
        }
        if (!isNullOrEmpty(classData.directMethods)) {
            MethodDefArray methodDefArrayInitDirectMethodsArray = initDirectMethodsArray();
            methodDefArrayInitDirectMethodsArray.setClassId(classId);
            methodDefArrayInitDirectMethodsArray.merge(classData.directMethods);
        }
        if (isNullOrEmpty(classData.virtualMethods)) {
            return;
        }
        MethodDefArray methodDefArrayInitVirtualMethodsArray = initVirtualMethodsArray();
        methodDefArrayInitVirtualMethodsArray.setClassId(classId);
        methodDefArrayInitVirtualMethodsArray.merge(classData.virtualMethods);
    }

    @Override // com.reandroid.dex.common.SectionItemContainer
    public void onReadBytes(BlockReader blockReader) throws IOException {
        super.onReadBytes(blockReader);
        if (this.staticFieldsCount.get() != 0) {
            initStaticFieldsArray().onReadBytes(blockReader);
        }
        if (this.instanceFieldsCount.get() != 0) {
            initInstanceFieldsArray().onReadBytes(blockReader);
        }
        if (this.directMethodsCount.get() != 0) {
            initDirectMethodsArray().onReadBytes(blockReader);
        }
        if (this.virtualMethodCount.get() != 0) {
            initVirtualMethodsArray().onReadBytes(blockReader);
        }
    }

    public void remove(Key key) {
        Def<?> def = get(key);
        if (def != null) {
            def.removeSelf();
        }
    }

    @Override // com.reandroid.dex.data.DataItem, com.reandroid.dex.common.SectionItem
    public void removeSelf() {
        super.removeSelf();
        Iterator<DefArray<?>> defArrays = getDefArrays();
        while (defArrays.hasNext()) {
            defArrays.next().clearChildes();
        }
        setClassId(null);
    }

    public void replaceKeys(Key key, Key key2) {
        Iterator<DefArray<?>> defArrays = getDefArrays();
        while (defArrays.hasNext()) {
            defArrays.next().replaceKeys(key, key2);
        }
    }

    public void setClassId(ClassId classId) {
        this.mClassId = classId;
        Iterator<DefArray<?>> defArrays = getDefArrays();
        while (defArrays.hasNext()) {
            defArrays.next().setClassId(classId);
        }
    }

    public void toSmali(SmaliClass smaliClass) {
        StaticFieldDefArray staticFieldDefArray = this.staticFields;
        if (staticFieldDefArray != null) {
            smaliClass.addFields((Iterator) ObjectsUtil.cast(staticFieldDefArray.toSmali()));
        }
        FieldDefArray fieldDefArray = this.instanceFields;
        if (fieldDefArray != null) {
            smaliClass.addFields((Iterator) ObjectsUtil.cast(fieldDefArray.toSmali()));
        }
        MethodDefArray methodDefArray = this.directMethods;
        if (methodDefArray != null) {
            smaliClass.addMethods((Iterator) ObjectsUtil.cast(methodDefArray.toSmali()));
        }
        MethodDefArray methodDefArray2 = this.virtualMethods;
        if (methodDefArray2 != null) {
            smaliClass.addMethods((Iterator) ObjectsUtil.cast(methodDefArray2.toSmali()));
        }
    }

    public String toString() {
        return "staticFieldsCount=" + this.staticFieldsCount + ", instanceFieldCount=" + this.instanceFieldsCount + ", directMethodCount=" + this.directMethodsCount + ", virtualMethodCount=" + this.virtualMethodCount;
    }

    @Override // com.reandroid.dex.data.DataItem, com.reandroid.dex.common.IdUsageIterator
    public Iterator<IdItem> usedIds() {
        return new IterableIterator<DefArray<?>, IdItem>(getDefArrays()) { // from class: com.reandroid.dex.data.ClassData.1
            public Iterator<IdItem> iterator(DefArray<?> defArray) {
                return defArray.usedIds();
            }
        };
    }
}
