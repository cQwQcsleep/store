package com.reandroid.dex.model;

import com.reandroid.common.ReflectionUtil;
import com.reandroid.dex.common.AccessFlag;
import com.reandroid.dex.dalvik.DalvikInnerClass;
import com.reandroid.dex.data.ClassData;
import com.reandroid.dex.data.FieldDef;
import com.reandroid.dex.data.MethodDef;
import com.reandroid.dex.id.ClassId;
import com.reandroid.dex.id.IdItem;
import com.reandroid.dex.key.FieldKey;
import com.reandroid.dex.key.Key;
import com.reandroid.dex.key.MethodKey;
import com.reandroid.dex.key.TypeKey;
import com.reandroid.dex.key.TypeListKey;
import com.reandroid.dex.model.DexClass;
import com.reandroid.dex.model.DexMethod;
import com.reandroid.dex.program.ClassProgram;
import com.reandroid.dex.smali.SmaliReader;
import com.reandroid.dex.smali.SmaliWriter;
import com.reandroid.dex.smali.model.SmaliField;
import com.reandroid.dex.smali.model.SmaliMethod;
import com.reandroid.utils.collection.CombiningIterator;
import com.reandroid.utils.collection.ComputeIterator;
import com.reandroid.utils.collection.FilterIterator;
import com.reandroid.utils.collection.InstanceIterator;
import com.reandroid.utils.collection.IterableIterator;
import com.reandroid.utils.collection.SingleIterator;
import com.reandroid.utils.collection.UniqueIterator;
import com.reandroid.utils.io.FileUtil;
import defpackage.hp3;
import defpackage.ip3;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class DexClass extends DexDeclaration implements ClassProgram, Comparable<DexClass> {
    private final ClassId classId;
    private final DexLayout dexLayout;

    public DexClass(DexLayout dexLayout, ClassId classId) {
        this.dexLayout = dexLayout;
        this.classId = classId;
    }

    public static /* synthetic */ DexMethod a(MethodKey methodKey, boolean z, TypeKey typeKey, DexClass dexClass) {
        DexMethod declaredMethod = dexClass.getDeclaredMethod(methodKey);
        if (declaredMethod == null || declaredMethod.isPrivate()) {
            return null;
        }
        if (z || declaredMethod.isAccessibleTo(typeKey)) {
            return declaredMethod;
        }
        return null;
    }

    private void fixMethodAccessibility(Set<Key> set) {
        Iterator<DexMethod> declaredMethods = getDeclaredMethods();
        while (declaredMethods.hasNext()) {
            DexMethod next = declaredMethods.next();
            if (!next.isPrivate()) {
                Iterator<DexMethod> methods = getMethods(next.getKey(), true);
                while (methods.hasNext()) {
                    DexMethod next2 = methods.next();
                    if (fixAccessibility(next2)) {
                        set.add(next2.getKey());
                    }
                }
                Iterator<DexMethod> extending = getExtending(next.getKey());
                while (methods.hasNext()) {
                    DexMethod next3 = extending.next();
                    MethodKey key = next3.getKey();
                    if (!set.contains(key) && fixAccessibility(next3)) {
                        set.add(key);
                    }
                }
            }
        }
    }

    private MethodKey getBridging(MethodKey methodKey) {
        DexMethod declaredMethod = getDeclaredMethod(methodKey, false);
        if (declaredMethod != null) {
            return declaredMethod.getBridging();
        }
        return null;
    }

    private void searchRequired(Predicate<TypeKey> predicate, Set<DexClass> set) {
        DexClassRepository classRepository = getClassRepository();
        Iterator<TypeKey> itUsedTypes = usedTypes();
        while (itUsedTypes.hasNext()) {
            TypeKey declaring = itUsedTypes.next().getDeclaring();
            if (predicate == null || predicate.test(declaring)) {
                DexClass dexClass = classRepository.getDexClass(declaring);
                if (dexClass != null && !set.contains(dexClass)) {
                    set.add(dexClass);
                    dexClass.searchRequired(predicate, set);
                }
            }
        }
    }

    private String toFilePath() {
        String typeName = getDefining().getTypeName();
        return typeName.substring(1, typeName.length() - 1).replace('/', File.separatorChar) + ".smali";
    }

    public void addInterface(TypeKey typeKey) {
        getId().setInterfaces(getInterfacesKey().remove(typeKey).add(typeKey));
    }

    @Override // com.reandroid.dex.model.Dex, com.reandroid.dex.smali.SmaliFormat
    public void append(SmaliWriter smaliWriter) throws IOException {
        getClassData();
        getId().append(smaliWriter);
    }

    public void clearDebug() {
        Iterator<DexMethod> declaredMethods = getDeclaredMethods();
        while (declaredMethods.hasNext()) {
            declaredMethods.next().clearDebug();
        }
    }

    public void clearInterfaces() {
        getId().setInterfaces(TypeListKey.empty());
    }

    @Override // java.lang.Comparable
    public int compareTo(DexClass dexClass) {
        return getKey().compareTo(dexClass.getKey());
    }

    public boolean containsDeclaredMethod(MethodKey methodKey) {
        ClassData classData;
        return (methodKey == null || (classData = getClassData()) == null || classData.getMethod(methodKey) == null) ? false : true;
    }

    public boolean containsInterface(TypeKey typeKey) {
        return getInterfacesKey().contains(typeKey);
    }

    public void createDalvikInnerClassName(String str) {
        DalvikInnerClass.getOrCreate(this).setName(str);
    }

    public void decode(SmaliWriter smaliWriter, File file) throws IOException {
        File file2 = new File(file, toFilePath());
        File parentFile = file2.getParentFile();
        if (parentFile != null && !parentFile.exists() && !parentFile.mkdirs()) {
            r8g.a("Failed to create dir: ", parentFile);
            return;
        }
        FileOutputStream fileOutputStream = new FileOutputStream(file2);
        smaliWriter.setWriter(new OutputStreamWriter(fileOutputStream));
        append(smaliWriter);
        smaliWriter.close();
        fileOutputStream.close();
    }

    public void edit() {
        getId().edit();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        DexClass dexClass = (DexClass) obj;
        if (isInSameFile(dexClass)) {
            return getKey().equals(dexClass.getKey());
        }
        return false;
    }

    public Set<Key> fixAccessibility() {
        DexClassRepository classRepository = getClassRepository();
        HashSet hashSet = new HashSet();
        Iterator<Key> itUsedKeys = getId().usedKeys();
        while (itUsedKeys.hasNext()) {
            Key next = itUsedKeys.next();
            if (!hashSet.contains(next) && fixAccessibility(classRepository.getDexDeclaration(next))) {
                hashSet.add(next);
            }
        }
        fixMethodAccessibility(hashSet);
        return hashSet;
    }

    public void fixDalvikInnerClassName() {
        String simpleInnerName;
        DalvikInnerClass dalvikInnerClassOf = DalvikInnerClass.of(this);
        if (dalvikInnerClassOf == null || !dalvikInnerClassOf.hasName() || (simpleInnerName = getKey().getSimpleInnerName()) == null) {
            return;
        }
        dalvikInnerClassOf.setName(simpleInnerName);
    }

    public ClassData getClassData() {
        return getId().getClassData();
    }

    public TypeKey getDalvikEnclosingClass() {
        TypeKey dalvikEnclosing = getId().getDalvikEnclosing();
        if (dalvikEnclosing != null) {
            return dalvikEnclosing.getDeclaring();
        }
        return null;
    }

    public String getDalvikInnerClassName() {
        DalvikInnerClass dalvikInnerClassOf = DalvikInnerClass.of(this);
        if (dalvikInnerClassOf != null) {
            return dalvikInnerClassOf.getName();
        }
        return null;
    }

    public DexField getDeclaredField(FieldKey fieldKey) {
        Iterator<DexField> declaredFields = getDeclaredFields();
        while (declaredFields.hasNext()) {
            DexField next = declaredFields.next();
            if (fieldKey.equalsIgnoreDeclaring(next.getKey())) {
                return next;
            }
        }
        return null;
    }

    public Iterator<DexField> getDeclaredFields() {
        return CombiningIterator.two(getStaticFields(), getInstanceFields());
    }

    public DexMethod getDeclaredMethod(MethodKey methodKey, boolean z) {
        Iterator<DexMethod> declaredMethods = getDeclaredMethods();
        while (declaredMethods.hasNext()) {
            DexMethod next = declaredMethods.next();
            MethodKey key = next.getKey();
            if (methodKey.equalsNameAndParameters(key) && (z || methodKey.equalsReturnType(key))) {
                return next;
            }
        }
        return null;
    }

    public Iterator<DexMethod> getDeclaredMethods(final MethodKey methodKey) {
        return FilterIterator.of(getDeclaredMethods(), new Predicate() { // from class: op3
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return methodKey.equalsNameAndParameters(((DexMethod) obj).getKey());
            }
        });
    }

    @Override // com.reandroid.dex.model.DexDeclaration
    public DexClass getDexClass() {
        return this;
    }

    public Iterator<DexInstruction> getDexInstructions(Predicate<DexMethod> predicate) {
        return new IterableIterator<DexMethod, DexInstruction>(getDeclaredMethods(predicate)) { // from class: com.reandroid.dex.model.DexClass.3
            public Iterator<DexInstruction> iterator(DexMethod dexMethod) {
                return dexMethod.getInstructions();
            }
        };
    }

    @Override // com.reandroid.dex.model.DexDeclaration
    public DexLayout getDexLayout() {
        return this.dexLayout;
    }

    @Override // com.reandroid.dex.program.ClassProgram
    public Iterator<DexMethod> getDirectMethods() {
        return ComputeIterator.of(getId().getDirectMethods(), new hp3(this));
    }

    public Iterator<DexMethod> getExtending(final MethodKey methodKey) {
        return CombiningIterator.of(getDeclaredMethod(methodKey), ComputeIterator.of(getExtending(), new Function() { // from class: jp3
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((DexClass) obj).getExtending(methodKey);
            }
        }));
    }

    public DexField getField(FieldKey fieldKey) {
        DexField field;
        if (!isAccessibleTo(fieldKey.getDeclaring())) {
            return null;
        }
        DexField declaredField = getDeclaredField(fieldKey);
        if (declaredField != null) {
            return declaredField;
        }
        DexClass superClass = getSuperClass();
        if (superClass != null && (field = superClass.getField(fieldKey)) != null) {
            if (field.isAccessibleTo(getDefining())) {
                return field;
            }
            return null;
        }
        Iterator<DexClass> interfaceClasses = getInterfaceClasses();
        while (interfaceClasses.hasNext()) {
            DexField field2 = interfaceClasses.next().getField(fieldKey);
            if (field2 != null && field2.isAccessibleTo(getDefining())) {
                return field2;
            }
        }
        return null;
    }

    public Iterator<DexMethod> getImplementations(final MethodKey methodKey) {
        return CombiningIterator.of(getDeclaredMethod(methodKey), ComputeIterator.of(getImplementations(), new Function() { // from class: mp3
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((DexClass) obj).getImplementations(methodKey);
            }
        }));
    }

    @Override // com.reandroid.dex.program.ClassProgram
    public Iterator<DexField> getInstanceFields() {
        return ComputeIterator.of(getId().getInstanceFields(), new ip3(this));
    }

    public Iterator<DexClass> getInterfaceClasses() {
        return ComputeIterator.of(getInterfacesKey().iterator(), new Function() { // from class: np3
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return this.b.search((TypeKey) obj);
            }
        });
    }

    @Override // com.reandroid.dex.program.ClassProgram
    public TypeListKey getInterfacesKey() {
        return getId().getInterfacesKey();
    }

    @Override // com.reandroid.dex.model.DexDeclaration, com.reandroid.dex.model.AnnotatedDex, com.reandroid.dex.program.ProgramElement, com.reandroid.dex.data.DefIndex
    public TypeKey getKey() {
        return getId().getKey();
    }

    public DexMethod getMethod(MethodKey methodKey) {
        DexMethod declaredMethod = getDeclaredMethod(methodKey);
        if (declaredMethod != null) {
            return declaredMethod;
        }
        Iterator<DexClass> superTypes = getSuperTypes();
        while (superTypes.hasNext()) {
            DexMethod declaredMethod2 = superTypes.next().getDeclaredMethod(methodKey);
            if (declaredMethod2 != null && declaredMethod2.isAccessibleTo(methodKey.getDeclaring())) {
                return declaredMethod2;
            }
        }
        return null;
    }

    public Iterator<DexMethod> getMethods(final MethodKey methodKey, final boolean z) {
        final TypeKey key = getKey();
        return CombiningIterator.two(getDeclaredMethods(methodKey), ComputeIterator.of(getSuperTypes(), new Function() { // from class: lp3
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return DexClass.a(methodKey, z, key, (DexClass) obj);
            }
        }));
    }

    public ClassData getOrCreateClassData() {
        return getId().getOrCreateClassData();
    }

    public DexMethod getOrCreateDirectMethod(MethodKey methodKey) {
        return initializeMethod(getOrCreateClassData().getOrCreateDirect(methodKey));
    }

    public FieldDef getOrCreateInstance(FieldKey fieldKey) {
        return getOrCreateClassData().getOrCreateInstance(fieldKey);
    }

    public DexField getOrCreateInstanceField(FieldKey fieldKey) {
        return initializeField(getOrCreateInstance(fieldKey));
    }

    public FieldDef getOrCreateStatic(FieldKey fieldKey) {
        return getOrCreateClassData().getOrCreateStatic(fieldKey);
    }

    public DexField getOrCreateStaticField(FieldKey fieldKey) {
        return initializeField(getOrCreateStatic(fieldKey));
    }

    public DexMethod getOrCreateStaticMethod(MethodKey methodKey) {
        DexMethod orCreateDirectMethod = getOrCreateDirectMethod(methodKey);
        orCreateDirectMethod.addAccessFlag(AccessFlag.STATIC);
        return orCreateDirectMethod;
    }

    public DexMethod getOrCreateVirtualMethod(MethodKey methodKey) {
        return initializeMethod(getOrCreateClassData().getOrCreateVirtual(methodKey));
    }

    public Iterator<DexClass> getOverriding() {
        return CombiningIterator.two(getExtending(), getImplementations());
    }

    public Iterator<DexClass> getOverridingAndSuperTypes() {
        return CombiningIterator.two(getOverriding(), getSuperTypes());
    }

    public Iterator<MethodKey> getOverridingKeys(MethodKey methodKey) {
        final MethodKey methodKeyChangeDeclaring = methodKey.changeDeclaring(getKey());
        return CombiningIterator.of(CombiningIterator.singleOne(methodKeyChangeDeclaring, SingleIterator.of(getBridging(methodKey))), ComputeIterator.of(getOverriding(), new Function() { // from class: pp3
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((DexClass) obj).getOverridingKeys(methodKeyChangeDeclaring);
            }
        }));
    }

    public Set<DexClass> getRequired(Predicate<TypeKey> predicate) {
        HashSet hashSet = new HashSet();
        hashSet.add(this);
        searchRequired(predicate, hashSet);
        return hashSet;
    }

    @Override // com.reandroid.dex.program.ClassProgram
    public String getSourceFileName() {
        return getId().getSourceFileName();
    }

    public DexMethod getStaticConstructor() {
        return getDeclaredMethod(MethodKey.STATIC_CONSTRUCTOR.changeDeclaring(getDefining()));
    }

    @Override // com.reandroid.dex.program.ClassProgram
    public Iterator<DexField> getStaticFields() {
        return ComputeIterator.of(getId().getStaticFields(), new ip3(this));
    }

    public DexClass getSuperClass() {
        return search(getSuperClassKey());
    }

    @Override // com.reandroid.dex.program.ClassProgram
    public TypeKey getSuperClassKey() {
        return getId().getSuperClassKey();
    }

    public Iterator<DexClass> getSuperTypes() {
        return new UniqueIterator(new IterableIterator<DexClass, DexClass>(CombiningIterator.two(SingleIterator.of(getSuperClass()), getInterfaceClasses())) { // from class: com.reandroid.dex.model.DexClass.2
            public Iterator<DexClass> iterator(DexClass dexClass) {
                return CombiningIterator.two(SingleIterator.of(dexClass), dexClass.getSuperTypes());
            }
        }).exclude(this);
    }

    @Override // com.reandroid.dex.program.ClassProgram
    public Iterator<DexMethod> getVirtualMethods() {
        return ComputeIterator.of(getId().getVirtualMethods(), new hp3(this));
    }

    @Override // com.reandroid.dex.model.DexDeclaration
    public int hashCode() {
        return getKey().hashCode();
    }

    public DexField initializeField(FieldDef fieldDef) {
        return new DexField(this, fieldDef);
    }

    public DexMethod initializeMethod(MethodDef methodDef) {
        return new DexMethod(this, methodDef);
    }

    public boolean isInstance(TypeKey typeKey) {
        if (typeKey == null) {
            return false;
        }
        if (typeKey.equals(TypeKey.OBJECT)) {
            return true;
        }
        TypeKey superClassKey = getSuperClassKey();
        if (typeKey.equals(getKey()) || typeKey.equals(superClassKey)) {
            return true;
        }
        DexClass dexClassSearch = search(superClassKey);
        if (dexClassSearch != null && dexClassSearch.isInstance(typeKey) && dexClassSearch.isInstance(typeKey)) {
            return true;
        }
        TypeListKey interfacesKey = getInterfacesKey();
        if (interfacesKey.contains(typeKey)) {
            return true;
        }
        if (dexClassSearch == null && ReflectionUtil.isInstanceReflection(superClassKey, typeKey)) {
            return true;
        }
        for (TypeKey typeKey2 : interfacesKey) {
            DexClass dexClassSearch2 = search(typeKey2);
            if (dexClassSearch2 != null) {
                if (dexClassSearch2.isInstance(typeKey)) {
                    return true;
                }
            } else if (ReflectionUtil.isInstanceReflection(typeKey2, typeKey)) {
                return true;
            }
        }
        return false;
    }

    public DexField parseField(SmaliReader smaliReader) throws IOException {
        SmaliField smaliField = new SmaliField();
        smaliField.setDefining(getKey());
        smaliField.parse(smaliReader);
        FieldKey key = smaliField.getKey();
        DexField orCreateInstanceField = smaliField.isInstance() ? getOrCreateInstanceField(key) : getOrCreateStaticField(key);
        orCreateInstanceField.getDefinition().fromSmali(smaliField);
        return orCreateInstanceField;
    }

    public DexField parseInterfaces(SmaliReader smaliReader) throws IOException {
        SmaliField smaliField = new SmaliField();
        smaliField.setDefining(getKey());
        smaliField.parse(smaliReader);
        FieldKey key = smaliField.getKey();
        DexField orCreateInstanceField = smaliField.isInstance() ? getOrCreateInstanceField(key) : getOrCreateStaticField(key);
        orCreateInstanceField.getDefinition().fromSmali(smaliField);
        return orCreateInstanceField;
    }

    public DexMethod parseMethod(SmaliReader smaliReader) throws IOException {
        SmaliMethod smaliMethod = new SmaliMethod();
        smaliMethod.setDefining(getKey());
        smaliMethod.parse(smaliReader);
        MethodKey key = smaliMethod.getKey();
        DexMethod orCreateDirectMethod = smaliMethod.isDirect() ? getOrCreateDirectMethod(key) : getOrCreateVirtualMethod(key);
        orCreateDirectMethod.getDefinition().fromSmali(smaliMethod);
        return orCreateDirectMethod;
    }

    public void removeInterface(TypeKey typeKey) {
        getId().setInterfaces(getInterfacesKey().remove(typeKey));
    }

    @Override // com.reandroid.dex.model.Dex
    public void removeSelf() {
        getDefinition().removeSelf();
    }

    public void replaceKeys(Key key, Key key2) {
        getId().replaceKeys(key, key2);
    }

    public DexClass search(TypeKey typeKey) {
        return getClassRepository().getDexClass(typeKey);
    }

    public void setSourceFile(String str) {
        getId().setSourceFile(str);
    }

    public void setSuperClass(TypeKey typeKey) {
        getId().setSuperClass(typeKey);
    }

    public String toSmali() throws IOException {
        return SmaliWriter.toString(this);
    }

    @Override // com.reandroid.dex.model.DexDeclaration, com.reandroid.dex.model.Dex
    public String toString() {
        return SmaliWriter.toStringSafe(this);
    }

    public void updateDalvikInnerClassName(String str) {
        DalvikInnerClass dalvikInnerClassOf = DalvikInnerClass.of(this);
        if (dalvikInnerClassOf != null) {
            dalvikInnerClassOf.setName(str);
        }
    }

    public Iterator<TypeKey> usedTypes() {
        return InstanceIterator.of(new IterableIterator<Key, Key>(ComputeIterator.of(getId().usedIds(), new Function() { // from class: kp3
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((IdItem) obj).getKey();
            }
        })) { // from class: com.reandroid.dex.model.DexClass.1
            public Iterator<Key> iterator(Key key) {
                return key.mentionedKeys();
            }
        }, TypeKey.class);
    }

    public boolean usesNative() {
        if (isNative()) {
            return true;
        }
        Iterator<DexMethod> declaredMethods = getDeclaredMethods();
        while (declaredMethods.hasNext()) {
            if (declaredMethods.next().isNative()) {
                return true;
            }
        }
        Iterator<DexField> declaredFields = getDeclaredFields();
        while (declaredFields.hasNext()) {
            if (declaredFields.next().isNative()) {
                return true;
            }
        }
        return false;
    }

    public void writeSmali(SmaliWriter smaliWriter, File file) throws IOException {
        File file2 = smaliWriter.getFileNameFactory().toFile(file, getKey());
        FileUtil.ensureParentDirectory(file2);
        smaliWriter.setWriter(new FileWriter(file2));
        append(smaliWriter);
        smaliWriter.close();
    }

    @Override // com.reandroid.dex.model.DexDeclaration
    public ClassId getDefinition() {
        return getId();
    }

    @Override // com.reandroid.dex.model.DexDeclaration
    public ClassId getId() {
        return this.classId;
    }

    public String toSmali(SmaliWriter smaliWriter) throws IOException {
        return SmaliWriter.toString(smaliWriter, this);
    }

    public Iterator<DexInstruction> getDexInstructions() {
        return getDexInstructions(null);
    }

    public Set<DexClass> getRequired() {
        return getRequired(null);
    }

    public Iterator<DexMethod> getDeclaredMethods(Predicate<DexMethod> predicate) {
        Iterator<DexMethod> declaredMethods = getDeclaredMethods();
        return predicate == null ? declaredMethods : FilterIterator.of(declaredMethods, predicate);
    }

    public Iterator<DexMethod> getDeclaredMethods() {
        return CombiningIterator.two(getDirectMethods(), getVirtualMethods());
    }

    public Iterator<DexClass> getExtending() {
        return getDexLayout().searchExtending(getKey());
    }

    public Iterator<DexClass> getImplementations() {
        return getDexLayout().searchImplementations(getKey());
    }

    public Iterator<DexMethod> getMethods(MethodKey methodKey) {
        return getMethods(methodKey, false);
    }

    public DexMethod getDeclaredMethod(MethodKey methodKey) {
        return getDeclaredMethod(methodKey, false);
    }

    private boolean fixAccessibility(DexDeclaration dexDeclaration) {
        if (dexDeclaration == null || dexDeclaration.isPrivate() || dexDeclaration.hasAccessFlag(AccessFlag.CONSTRUCTOR, AccessFlag.STATIC) || dexDeclaration.isAccessibleTo(this)) {
            return false;
        }
        dexDeclaration.addAccessFlag(AccessFlag.PUBLIC);
        return true;
    }
}
