package com.reandroid.dex.id;

import com.reandroid.arsc.base.Block;
import com.reandroid.arsc.item.IndirectInteger;
import com.reandroid.dex.base.UsageMarker;
import com.reandroid.dex.common.AccessFlag;
import com.reandroid.dex.common.IdDefinition;
import com.reandroid.dex.common.SectionTool;
import com.reandroid.dex.dalvik.DalvikEnclosing;
import com.reandroid.dex.dalvik.DalvikMemberClass;
import com.reandroid.dex.data.AnnotationsDirectory;
import com.reandroid.dex.data.ClassData;
import com.reandroid.dex.data.Def;
import com.reandroid.dex.data.EncodedArray;
import com.reandroid.dex.data.FieldDef;
import com.reandroid.dex.data.MethodDef;
import com.reandroid.dex.data.TypeList;
import com.reandroid.dex.key.AnnotationSetKey;
import com.reandroid.dex.key.ArrayValueKey;
import com.reandroid.dex.key.Key;
import com.reandroid.dex.key.TypeKey;
import com.reandroid.dex.key.TypeListKey;
import com.reandroid.dex.program.ClassProgram;
import com.reandroid.dex.reference.DataItemIndirectReference;
import com.reandroid.dex.reference.TypeListReference;
import com.reandroid.dex.sections.SectionType;
import com.reandroid.dex.smali.SmaliWriter;
import com.reandroid.dex.smali.model.SmaliClass;
import com.reandroid.utils.collection.ArrayCollection;
import com.reandroid.utils.collection.CombiningIterator;
import com.reandroid.utils.collection.EmptyIterator;
import com.reandroid.utils.collection.InstanceIterator;
import java.io.IOException;
import java.util.Iterator;
import java.util.Objects;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class ClassId extends IdItem implements ClassProgram, IdDefinition<TypeId>, Comparable<ClassId> {
    private static final int SIZE = 32;
    private final IndirectInteger accessFlagValue;
    private final DataItemIndirectReference<AnnotationsDirectory> annotationsDirectory;
    private final DataItemIndirectReference<ClassData> classData;
    private final ClassTypeId classTypeId;
    private final TypeListReference interfaces;
    private final SourceFile sourceFile;
    private final DataItemIndirectReference<EncodedArray> staticValues;
    private final SuperClassId superClassId;

    public ClassId() {
        super(32);
        this.classTypeId = new ClassTypeId(this, 0);
        this.accessFlagValue = new IndirectInteger(this, 4);
        this.superClassId = new SuperClassId(this, 8);
        this.interfaces = new TypeListReference(this, 12, UsageMarker.USAGE_INTERFACE);
        this.sourceFile = new SourceFile(this, 16);
        SectionType<AnnotationsDirectory> sectionType = SectionType.ANNOTATION_DIRECTORY;
        int i = UsageMarker.USAGE_DEFINITION;
        this.annotationsDirectory = new DataItemIndirectReference<>(sectionType, this, 20, i);
        this.classData = new DataItemIndirectReference<>(SectionType.CLASS_DATA, this, 24, i);
        this.staticValues = new DataItemIndirectReference<>(SectionType.ENCODED_ARRAY, this, 28, UsageMarker.USAGE_STATIC_VALUES);
        addUsageType(i);
    }

    private void linkClassData(ClassData classData) {
        if (classData != null) {
            classData.setClassId(this);
        }
    }

    private void writeAnnotation(AnnotationSetKey annotationSetKey) {
        if (annotationSetKey != null && !annotationSetKey.isEmpty()) {
            getOrCreateUniqueAnnotationsDirectory().setClassAnnotations(annotationSetKey);
        } else if (hasAnnotations()) {
            getOrCreateUniqueAnnotationsDirectory().setClassAnnotations((AnnotationSetKey) null);
        }
    }

    @Override // com.reandroid.dex.smali.SmaliFormat
    public void append(SmaliWriter smaliWriter) throws IOException {
        getClassTypeId().append(smaliWriter);
        getSuperClassId().append(smaliWriter);
        getSourceFileReference().append(smaliWriter);
        getInterfacesKey().appendInterfaces(smaliWriter);
        getAnnotation().appendClass(smaliWriter);
        ClassData classData = getClassData();
        if (classData != null) {
            smaliWriter.newLine();
            classData.append(smaliWriter);
        }
    }

    @Override // com.reandroid.dex.id.IdItem
    public void cacheItems() {
        this.classTypeId.pullItem();
        this.superClassId.pullItem();
        this.interfaces.pullItem();
        this.sourceFile.pullItem();
        this.annotationsDirectory.pullItem();
        this.classData.pullItem();
        this.staticValues.pullItem();
        this.annotationsDirectory.addUniqueUser(this);
        this.classData.addUniqueUser(this);
        this.staticValues.addUniqueUser(this);
        linkClassData((ClassData) this.classData.getItem());
    }

    @Override // com.reandroid.dex.program.AnnotatedProgram
    public void clearAnnotations() {
        writeAnnotation(AnnotationSetKey.empty());
    }

    @Override // com.reandroid.dex.common.SectionItem, com.reandroid.dex.base.UsageMarker
    public void clearUsageType() {
    }

    @Override // java.lang.Comparable
    public int compareTo(ClassId classId) {
        if (classId == null) {
            return -1;
        }
        if (classId == this) {
            return 0;
        }
        return SectionTool.compareIdx(getId(), classId.getId());
    }

    @Override // com.reandroid.dex.common.EditableItem
    public void edit() {
        editInternal(this);
    }

    @Override // com.reandroid.dex.common.SectionItem, com.reandroid.dex.common.EditableItem
    public void editInternal(Block block) {
        this.annotationsDirectory.editInternal(this);
        this.classData.editInternal(this);
        this.staticValues.editInternal(this);
    }

    public void fromSmali(SmaliClass smaliClass) throws IOException {
        setKey(smaliClass.getKey());
        setAccessFlagsValue(smaliClass.getAccessFlagsValue());
        setSuperClass(smaliClass.getSuperClassKey());
        setSourceFile(smaliClass.getSourceFileName());
        setInterfaces(smaliClass.getInterfacesKey());
        if (smaliClass.hasClassData()) {
            getOrCreateClassData().fromSmali(smaliClass);
        }
        if (smaliClass.hasAnnotation()) {
            setAnnotation(smaliClass.getAnnotationSetKey());
        }
    }

    @Override // com.reandroid.dex.program.AccessibleProgram
    public int getAccessFlagsValue() {
        return this.accessFlagValue.get();
    }

    @Override // com.reandroid.dex.program.AnnotatedProgram
    public AnnotationSetKey getAnnotation() {
        AnnotationsDirectory annotationsDirectory = getAnnotationsDirectory();
        return annotationsDirectory != null ? annotationsDirectory.getClassAnnotation() : AnnotationSetKey.empty();
    }

    public AnnotationsDirectory getAnnotationsDirectory() {
        return (AnnotationsDirectory) this.annotationsDirectory.getItem();
    }

    public ClassData getClassData() {
        ClassData classData = (ClassData) this.classData.getItem();
        linkClassData(classData);
        return classData;
    }

    public ClassTypeId getClassTypeId() {
        return this.classTypeId;
    }

    public TypeKey getDalvikEnclosing() {
        DalvikEnclosing<?> dalvikEnclosingOf = DalvikEnclosing.of(this);
        if (dalvikEnclosingOf != null) {
            return dalvikEnclosingOf.getEnclosingClass();
        }
        return null;
    }

    public Iterator<TypeKey> getDalvikMemberClasses() {
        DalvikMemberClass dalvikMemberClassOf = DalvikMemberClass.of(this);
        return dalvikMemberClassOf != null ? dalvikMemberClassOf.getMembers() : EmptyIterator.of();
    }

    public Def<?> getDef(Key key) {
        ClassData classData = getClassData();
        if (classData != null) {
            return classData.get(key);
        }
        return null;
    }

    @Override // com.reandroid.dex.program.ClassProgram
    public Iterator<MethodDef> getDirectMethods() {
        ClassData classData = getClassData();
        return classData != null ? classData.getDirectMethods() : EmptyIterator.of();
    }

    @Override // com.reandroid.dex.common.IdDefinition
    public TypeId getId() {
        return getClassTypeId().getItem();
    }

    @Override // com.reandroid.dex.program.ClassProgram
    public Iterator<FieldDef> getInstanceFields() {
        ClassData classData = getClassData();
        return classData != null ? classData.getInstanceFields() : EmptyIterator.of();
    }

    public Iterator<TypeKey> getInstanceKeys() {
        return CombiningIterator.singleOne(getSuperClassKey(), getInterfacesKey().iterator());
    }

    public TypeList getInterfaceTypeList() {
        return this.interfaces.getItem();
    }

    @Override // com.reandroid.dex.program.ClassProgram
    public TypeListKey getInterfacesKey() {
        TypeListKey key = this.interfaces.getKey();
        return key == null ? TypeListKey.empty() : key;
    }

    public TypeListReference getInterfacesReference() {
        return this.interfaces;
    }

    @Override // com.reandroid.dex.id.IdItem, com.reandroid.dex.common.SectionItem, com.reandroid.dex.key.KeyItem
    public TypeKey getKey() {
        return (TypeKey) checkKey(TypeKey.create(getName()));
    }

    public String getName() {
        TypeId id = getId();
        if (id != null) {
            return id.getName();
        }
        return null;
    }

    public ClassData getOrCreateClassData() {
        ClassData classData = getClassData();
        if (classData != null) {
            return classData;
        }
        ClassData classData2 = (ClassData) getSection(SectionType.CLASS_DATA).createItem();
        setClassData(classData2);
        return classData2;
    }

    public AnnotationsDirectory getOrCreateUniqueAnnotationsDirectory() {
        return (AnnotationsDirectory) this.annotationsDirectory.getOrCreateUniqueItem(this);
    }

    @Override // com.reandroid.dex.id.IdItem, com.reandroid.dex.common.SectionItem
    public SectionType<ClassId> getSectionType() {
        return SectionType.CLASS_ID;
    }

    @Override // com.reandroid.dex.program.ClassProgram
    public String getSourceFileName() {
        return getSourceFileReference().getString();
    }

    public SourceFile getSourceFileReference() {
        return this.sourceFile;
    }

    @Override // com.reandroid.dex.program.ClassProgram
    public Iterator<FieldDef> getStaticFields() {
        ClassData classData = getClassData();
        return classData != null ? classData.getStaticFields() : EmptyIterator.of();
    }

    public ArrayValueKey getStaticValues() {
        return (ArrayValueKey) this.staticValues.getKey();
    }

    public EncodedArray getStaticValuesEncodedArray() {
        EncodedArray encodedArray = (EncodedArray) this.staticValues.getItem();
        if (encodedArray != null) {
            encodedArray.addUniqueUser(this);
        }
        return encodedArray;
    }

    public SuperClassId getSuperClassId() {
        return this.superClassId;
    }

    @Override // com.reandroid.dex.program.ClassProgram
    public TypeKey getSuperClassKey() {
        return getSuperClassId().getKey();
    }

    public TypeId getSuperClassType() {
        return getSuperClassId().getItem();
    }

    public AnnotationsDirectory getUniqueAnnotationsDirectory() {
        return (AnnotationsDirectory) this.annotationsDirectory.getUniqueItem(this);
    }

    @Override // com.reandroid.dex.program.ClassProgram
    public Iterator<MethodDef> getVirtualMethods() {
        ClassData classData = getClassData();
        return classData != null ? classData.getVirtualMethods() : EmptyIterator.of();
    }

    @Override // com.reandroid.dex.program.AnnotatedProgram
    public boolean hasAnnotations() {
        AnnotationsDirectory annotationsDirectory = getAnnotationsDirectory();
        if (annotationsDirectory != null) {
            return annotationsDirectory.hasClassAnnotation();
        }
        return false;
    }

    @Override // com.reandroid.dex.common.SectionItem, com.reandroid.dex.common.IdDefinition
    public boolean isRemoved() {
        return super.isRemoved();
    }

    public ArrayCollection<IdItem> listUsedIds() {
        ArrayCollection<IdItem> arrayCollection = new ArrayCollection<>(200);
        arrayCollection.add(this.classTypeId.getItem());
        arrayCollection.add(this.superClassId.getItem());
        arrayCollection.add(this.sourceFile.getItem());
        arrayCollection.addAll(this.interfaces.iterator());
        AnnotationsDirectory annotationsDirectory = getAnnotationsDirectory();
        if (annotationsDirectory != null) {
            arrayCollection.addAll(annotationsDirectory.usedIds());
        }
        ClassData classData = getClassData();
        if (classData != null) {
            arrayCollection.addAll(classData.usedIds());
        }
        EncodedArray staticValuesEncodedArray = getStaticValuesEncodedArray();
        if (staticValuesEncodedArray != null) {
            arrayCollection.addAll(staticValuesEncodedArray.usedIds());
        }
        int size = arrayCollection.size();
        for (int i = 0; i < size; i++) {
            arrayCollection.addAll(arrayCollection.get(i).usedIds());
        }
        return arrayCollection;
    }

    public void merge(ClassId classId) {
        if (classId == this) {
            return;
        }
        this.accessFlagValue.set(classId.accessFlagValue.get());
        this.superClassId.setKey(classId.superClassId.getKey());
        this.sourceFile.setKey(classId.sourceFile.getKey());
        this.interfaces.setKey(classId.interfaces.getKey());
        this.annotationsDirectory.setKey(classId.annotationsDirectory.getKey());
        EncodedArray staticValuesEncodedArray = classId.getStaticValuesEncodedArray();
        if (staticValuesEncodedArray != null) {
            ((EncodedArray) this.staticValues.getOrCreate()).merge(staticValuesEncodedArray);
        }
        ClassData classData = classId.getClassData();
        if (classData != null) {
            getOrCreateClassData().merge(classData);
        }
    }

    public void refresh() {
        this.annotationsDirectory.addUniqueUser(this);
        this.classData.addUniqueUser(this);
        this.staticValues.addUniqueUser(this);
        this.classTypeId.refresh();
        this.superClassId.refresh();
        this.interfaces.refresh();
        this.sourceFile.refresh();
        this.annotationsDirectory.refresh();
        this.classData.refresh();
        this.staticValues.refresh();
    }

    @Override // com.reandroid.dex.id.IdItem, com.reandroid.dex.common.SectionItem
    public void removeSelf() {
        super.removeSelf();
        this.classTypeId.unlink();
        this.superClassId.unlink();
        this.sourceFile.unlink();
        this.classData.unlink();
        this.annotationsDirectory.unlink();
        this.staticValues.unlink();
    }

    public void replaceKeys(Key key, Key key2) {
        this.classTypeId.replaceKeys(key, key2);
        this.superClassId.replaceKeys(key, key2);
        if (getAnnotationsDirectory() != null) {
            getUniqueAnnotationsDirectory().replaceKeys(key, key2);
        }
        this.interfaces.replaceKeys(key, key2);
        ClassData classData = getClassData();
        if (classData != null) {
            classData.replaceKeys(key, key2);
        }
    }

    @Override // com.reandroid.dex.program.AccessibleProgram
    public void setAccessFlagsValue(int i) {
        this.accessFlagValue.set(i);
    }

    @Override // com.reandroid.dex.program.AnnotatedProgram
    public void setAnnotation(AnnotationSetKey annotationSetKey) {
        clearAnnotations();
        writeAnnotation(annotationSetKey);
    }

    public void setAnnotationsDirectory(AnnotationsDirectory annotationsDirectory) {
        this.annotationsDirectory.setItem(annotationsDirectory);
    }

    public void setClassData(ClassData classData) {
        this.classData.setItem(classData);
        linkClassData(classData);
    }

    public void setId(TypeId typeId) {
        this.classTypeId.setItem(typeId);
    }

    public void setInterfaces(TypeListKey typeListKey) {
        this.interfaces.setKey(typeListKey);
    }

    @Override // com.reandroid.dex.id.IdItem, com.reandroid.dex.key.KeyReference
    public void setKey(Key key) {
        TypeKey key2 = getKey();
        if (Objects.equals(key2, key)) {
            return;
        }
        this.classTypeId.setKey(key);
        keyChanged(key2);
    }

    public void setName(String str) {
        setKey(new TypeKey(str));
    }

    public void setSourceFile(String str) {
        getSourceFileReference().setString(str);
    }

    public void setStaticValues(ArrayValueKey arrayValueKey) {
        this.staticValues.setKey(arrayValueKey);
        this.staticValues.addUniqueUser(this);
    }

    public void setSuperClass(TypeKey typeKey) {
        this.superClassId.setKey(typeKey);
    }

    public SmaliClass toSmali() {
        SmaliClass smaliClass = new SmaliClass();
        smaliClass.setKey(getKey());
        smaliClass.setAccessFlags(InstanceIterator.of(getAccessFlags(), AccessFlag.class));
        smaliClass.setSuperClass(getSuperClassKey());
        smaliClass.setSourceFile(getSourceFileReference().getKey());
        smaliClass.setInterfaces(getInterfacesReference().getKey());
        smaliClass.setAnnotation(getAnnotation());
        ClassData classData = getClassData();
        if (classData != null) {
            classData.toSmali(smaliClass);
        }
        return smaliClass;
    }

    public String toString() {
        if (!isReading()) {
            return SmaliWriter.toStringSafe(this);
        }
        return ".class " + getKey();
    }

    @Override // com.reandroid.dex.id.IdItem, com.reandroid.dex.common.IdUsageIterator
    public Iterator<IdItem> usedIds() {
        return listUsedIds().iterator();
    }

    public void setStaticValues(EncodedArray encodedArray) {
        this.staticValues.setItem(encodedArray);
    }
}
