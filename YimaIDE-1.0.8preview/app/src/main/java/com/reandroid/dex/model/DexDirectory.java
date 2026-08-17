package com.reandroid.dex.model;

import com.reandroid.archive.ZipEntryMap;
import com.reandroid.arsc.base.BlockCreator;
import com.reandroid.dex.common.FullRefresh;
import com.reandroid.dex.common.SectionItem;
import com.reandroid.dex.id.ClassId;
import com.reandroid.dex.id.FieldId;
import com.reandroid.dex.id.MethodId;
import com.reandroid.dex.id.StringId;
import com.reandroid.dex.key.FieldKey;
import com.reandroid.dex.key.Key;
import com.reandroid.dex.key.KeyPair;
import com.reandroid.dex.key.MethodKey;
import com.reandroid.dex.key.TypeKey;
import com.reandroid.dex.key.TypeKeyReference;
import com.reandroid.dex.model.DexDirectory;
import com.reandroid.dex.sections.MergeOptions;
import com.reandroid.dex.sections.SectionType;
import com.reandroid.dex.smali.SmaliWriter;
import com.reandroid.utils.collection.ArrayCollection;
import com.reandroid.utils.collection.CollectionUtil;
import com.reandroid.utils.collection.EmptyList;
import com.reandroid.utils.collection.FilterIterator;
import com.reandroid.utils.collection.IterableIterator;
import com.sun.org.apache.xalan.internal.templates.Constants;
import defpackage.aca;
import defpackage.bq3;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class DexDirectory implements Iterable<DexFile>, Closeable, DexClassRepository, FullRefresh {
    private final DexFileSourceSet dexSourceSet = new DexFileSourceSet();
    private final ArrayCollection<TypeKeyReference> externalTypeKeyReferenceList = new ArrayCollection<>();
    private Object mTag;

    public static DexDirectory fromDexFilesDirectory(File file, Predicate<SectionType<?>> predicate) throws IOException {
        DexDirectory dexDirectory = new DexDirectory();
        DexFileSourceSet dexSourceSet = dexDirectory.getDexSourceSet();
        dexSourceSet.setReadFilter(predicate);
        dexSourceSet.addAll(file);
        dexDirectory.updateDexFileList();
        return dexDirectory;
    }

    public static DexDirectory fromZip(ZipEntryMap zipEntryMap, Predicate<SectionType<?>> predicate) throws IOException {
        DexDirectory dexDirectory = new DexDirectory();
        DexFileSourceSet dexSourceSet = dexDirectory.getDexSourceSet();
        dexSourceSet.setReadFilter(predicate);
        dexSourceSet.addAll(zipEntryMap);
        dexDirectory.updateDexFileList();
        return dexDirectory;
    }

    private DexFile getLastNonEmpty(MergeOptions mergeOptions, int i) {
        for (int size = size() - 1; size >= i; size--) {
            DexFile dexFile = get(size);
            if (!mergeOptions.isEmptyDexFile(dexFile.getContainerBlock())) {
                return dexFile;
            }
        }
        return null;
    }

    public static /* synthetic */ boolean k(SectionType sectionType) {
        return sectionType == SectionType.STRING_ID || sectionType == SectionType.STRING_DATA;
    }

    public static DexDirectory readMapList(ZipEntryMap zipEntryMap) throws IOException {
        return fromZip(zipEntryMap, CollectionUtil.getRejectAll());
    }

    public static DexDirectory readStrings(ZipEntryMap zipEntryMap) throws IOException {
        return fromZip(zipEntryMap, new Predicate() { // from class: cq3
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return DexDirectory.k((SectionType) obj);
            }
        });
    }

    private boolean renameTypeString(StringId stringId, KeyPair<TypeKey, TypeKey> keyPair, boolean z, boolean z2) {
        String string = stringId.getString();
        TypeKey typeKey = (TypeKey) keyPair.getFirst();
        TypeKey typeKey2 = (TypeKey) keyPair.getSecond();
        String typeName = typeKey.getTypeName();
        String typeName2 = typeKey2.getTypeName();
        if (typeName.equals(string)) {
            stringId.setString(typeName2);
            return true;
        }
        if (z) {
            String strReplace = typeName.replace(';', '$');
            if (string.startsWith(strReplace)) {
                stringId.setString(typeKey2.getTypeName().replace(';', '$') + string.substring(strReplace.length()));
                return true;
            }
        }
        if (typeKey.getSignatureTypeName().equals(string)) {
            stringId.setString(typeKey2.getSignatureTypeName());
            return true;
        }
        String arrayType = typeKey.getArrayType(1);
        if (arrayType.equals(string)) {
            stringId.setString(typeKey2.getArrayType(1));
            return true;
        }
        if (z) {
            String strReplace2 = arrayType.replace(';', '$');
            if (string.startsWith(strReplace2)) {
                stringId.setString(typeKey2.getArrayType(1).replace(';', '$') + string.substring(strReplace2.length()));
                return true;
            }
        }
        String arrayType2 = typeKey.getArrayType(2);
        if (arrayType2.equals(string)) {
            stringId.setString(typeKey2.getArrayType(2));
            return true;
        }
        if (z) {
            String strReplace3 = arrayType2.replace(';', '$');
            if (string.startsWith(strReplace3)) {
                stringId.setString(typeKey2.getArrayType(2).replace(';', '$') + string.substring(strReplace3.length()));
                return true;
            }
        }
        String arrayType3 = typeKey.getArrayType(3);
        if (arrayType3.equals(string)) {
            stringId.setString(typeKey2.getArrayType(3));
            return true;
        }
        if (z) {
            String strReplace4 = arrayType3.replace(';', '$');
            if (string.startsWith(strReplace4)) {
                stringId.setString(typeKey2.getArrayType(3).replace(';', '$') + string.substring(strReplace4.length()));
                return true;
            }
        }
        if (!z2) {
            return false;
        }
        String sourceName = typeKey.getSourceName();
        if (sourceName.equals(string)) {
            stringId.setString(typeKey2.getSourceName());
            return true;
        }
        if (!z) {
            return false;
        }
        String strConcat = sourceName.concat("$");
        if (string.startsWith(strConcat)) {
            stringId.setString((typeKey2.getSourceName() + "$").concat(string.substring(strConcat.length())));
            return true;
        }
        String strConcat2 = strConcat.concat(Constants.ATTRVAL_THIS);
        if (!string.startsWith(strConcat2)) {
            return false;
        }
        stringId.setString((typeKey2.getSourceName() + Constants.ATTRVAL_THIS).concat(string.substring(strConcat2.length())));
        return true;
    }

    public void addApk(ZipEntryMap zipEntryMap) throws IOException {
        addZip(zipEntryMap, "");
    }

    public void addDirectory(File file) throws IOException {
        getDexSourceSet().addAll(file);
        Iterator<DexFile> it = iterator();
        while (it.hasNext()) {
            it.next().setDexDirectory(this);
        }
    }

    public void addExternalTypeKeyReference(TypeKeyReference typeKeyReference) {
        if (typeKeyReference == null || this.externalTypeKeyReferenceList.contains(typeKeyReference)) {
            return;
        }
        this.externalTypeKeyReferenceList.add(typeKeyReference);
    }

    public void addFile(File file) throws IOException {
        DexSource<DexFile> dexSourceAdd = getDexSourceSet().add(file);
        if (file.isFile()) {
            dexSourceAdd.get().setDexDirectory(this);
        }
    }

    public void addZip(ZipEntryMap zipEntryMap, String str) throws IOException {
        getDexSourceSet().addAll(zipEntryMap, str);
        Iterator<DexFile> it = iterator();
        while (it.hasNext()) {
            it.next().setDexDirectory(this);
        }
    }

    public int clearDuplicateData() {
        Iterator<DexFile> it = iterator();
        int iClearDuplicateData = 0;
        while (it.hasNext()) {
            iClearDuplicateData += it.next().clearDuplicateData();
        }
        return iClearDuplicateData;
    }

    public void clearExternalTypeKeyReferences() {
        this.externalTypeKeyReferenceList.clear();
    }

    public int clearUnused() {
        Iterator<DexFile> it = iterator();
        int iClearUnused = 0;
        while (it.hasNext()) {
            iClearUnused += it.next().clearUnused();
        }
        return iClearUnused;
    }

    public Iterator<DexFile> clonedIterator() {
        return this.dexSourceSet.getClonedDexFiles();
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.dexSourceSet.close();
        clearExternalTypeKeyReferences();
    }

    public boolean containsDeepSearch(FieldKey fieldKey) {
        DexClass dexClass = getDexClass(fieldKey.getDeclaring());
        if (dexClass == null) {
            return false;
        }
        Iterator<DexClass> overridingAndSuperTypes = dexClass.getOverridingAndSuperTypes();
        while (overridingAndSuperTypes.hasNext()) {
            if (fieldKey.equals(fieldKey.changeDeclaring(overridingAndSuperTypes.next().getKey()))) {
                return true;
            }
        }
        return false;
    }

    public DexFile createDefault() {
        DexFileSourceSet dexSourceSet = getDexSourceSet();
        if (size() == 0 && dexSourceSet.getZipEntryMap() == null) {
            dexSourceSet.setZipEntryMap(new ZipEntryMap());
        }
        DexSource<DexFile> dexSourceCreateNext = dexSourceSet.createNext();
        DexFile dexFileCreateDefault = DexFile.createDefault();
        dexSourceCreateNext.set(dexFileCreateDefault);
        dexFileCreateDefault.setDexDirectory(this);
        dexFileCreateDefault.setSimpleName(dexSourceCreateNext.toString());
        int version = getVersion();
        if (version != 0) {
            dexFileCreateDefault.setVersion(version);
        }
        return dexFileCreateDefault;
    }

    public int distributeClasses(int i) {
        if (i <= 0) {
            qf1.a("Classes per dex must be greater than zero: ", i);
            return 0;
        }
        int size = size();
        if (size == 0) {
            return 0;
        }
        int dexClassesCount = getDexClassesCount();
        int i2 = dexClassesCount / size;
        while (i2 > i) {
            createDefault();
            int size2 = size();
            if (size2 <= size) {
                w01.a("Failed to create next dex");
                return 0;
            }
            i2 = dexClassesCount / size2;
            size = size2;
        }
        int iDistributeClasses = 0;
        for (int i3 = 0; i3 < size; i3++) {
            iDistributeClasses += distributeClasses(get(i3), i2);
        }
        return iDistributeClasses;
    }

    public <T1 extends SectionItem> T1 get(SectionType<T1> sectionType, Key key) {
        Iterator<DexFile> it = iterator();
        while (it.hasNext()) {
            T1 t1 = (T1) it.next().getItem(sectionType, key);
            if (t1 != null) {
                return t1;
            }
        }
        return null;
    }

    public Iterator<ClassId> getClassIds() {
        return getItems(SectionType.CLASS_ID);
    }

    public Iterator<DexInstruction> getDexInstructions() {
        return new IterableIterator<DexFile, DexInstruction>(iterator()) { // from class: com.reandroid.dex.model.DexDirectory.1
            public Iterator<DexInstruction> iterator(DexFile dexFile) {
                return dexFile.getDexInstructions();
            }
        };
    }

    public Iterator<DexInstruction> getDexInstructionsCloned() {
        return new IterableIterator<DexFile, DexInstruction>(clonedIterator()) { // from class: com.reandroid.dex.model.DexDirectory.2
            public Iterator<DexInstruction> iterator(DexFile dexFile) {
                return dexFile.getDexInstructionsCloned();
            }
        };
    }

    public DexFileSourceSet getDexSourceSet() {
        return this.dexSourceSet;
    }

    @Override // com.reandroid.dex.model.DexClassRepository
    public List<TypeKeyReference> getExternalTypeKeyReferenceList() {
        return this.externalTypeKeyReferenceList;
    }

    public DexFile getFirst() {
        DexSource<DexFile> first = this.dexSourceSet.getFirst();
        if (first != null) {
            return first.get();
        }
        return null;
    }

    public DexFile getLast() {
        DexSource<DexFile> last = this.dexSourceSet.getLast();
        if (last != null) {
            return last.get();
        }
        return null;
    }

    @Override // com.reandroid.dex.model.DexClassRepository
    public DexClassRepository getRootRepository() {
        return this;
    }

    public Object getTag() {
        return this.mTag;
    }

    public ZipEntryMap getZipEntryMap() {
        return getDexSourceSet().getZipEntryMap();
    }

    public int indexOf(DexFile dexFile) {
        int size = size();
        for (int i = 0; i < size; i++) {
            if (dexFile == get(i)) {
                return i;
            }
        }
        return -1;
    }

    @Override // java.lang.Iterable
    public Iterator<DexFile> iterator() {
        return this.dexSourceSet.getDexFiles();
    }

    public boolean merge(MergeOptions mergeOptions, DexClass dexClass) {
        if (dexClass.isInSameDirectory(this)) {
            return false;
        }
        if (containsClass(dexClass.getKey())) {
            mergeOptions.onDuplicate(dexClass.getId());
            return false;
        }
        int mergeStartDexFile = mergeOptions.getMergeStartDexFile();
        boolean z = false;
        while (mergeStartDexFile < size()) {
            if (get(mergeStartDexFile).merge(mergeOptions, dexClass)) {
                if (z) {
                    mergeOptions.setMergeStartDexFile(mergeStartDexFile);
                }
                return true;
            }
            mergeStartDexFile++;
            z = true;
        }
        return false;
    }

    public int mergeAll(MergeOptions mergeOptions, Iterator<DexClass> it) {
        int i = 0;
        while (it.hasNext()) {
            if (merge(mergeOptions, it.next())) {
                i++;
            }
        }
        return i;
    }

    @Override // com.reandroid.dex.model.DexClassRepository
    public Iterator<DexClassModule> modules() {
        return new IterableIterator<DexFile, DexClassModule>(iterator()) { // from class: com.reandroid.dex.model.DexDirectory.3
            public Iterator<DexClassModule> iterator(DexFile dexFile) {
                return dexFile.modules();
            }
        };
    }

    public void refresh() {
        for (DexFile dexFile : this) {
            dexFile.setDexDirectory(this);
            dexFile.refresh();
        }
    }

    @Override // com.reandroid.dex.common.FullRefresh
    public void refreshFull() {
        for (DexFile dexFile : this) {
            dexFile.setDexDirectory(this);
            dexFile.refreshFull();
        }
    }

    public List<FieldKey> rename(FieldKey fieldKey, String str) {
        ArrayCollection arrayCollectionOf = ArrayCollection.of((Iterator) findEquivalentFields(fieldKey.changeName(str)));
        ArrayCollection<FieldId> arrayCollectionOf2 = ArrayCollection.of(getItems(SectionType.FIELD_ID, fieldKey));
        if (arrayCollectionOf2.isEmpty()) {
            return EmptyList.of();
        }
        if (!arrayCollectionOf.isEmpty()) {
            z01.a("Conflicting fields: ", arrayCollectionOf.getFirst());
            return null;
        }
        FieldKey fieldKeyChangeName = fieldKey.changeName(str);
        Iterator it = arrayCollectionOf2.iterator();
        while (it.hasNext()) {
            if (fieldKeyChangeName.equals(((FieldId) it.next()).getKey())) {
                aca.a("Duplicate: ", fieldKeyChangeName);
                return null;
            }
        }
        ArrayCollection arrayCollection = new ArrayCollection(arrayCollectionOf2.size());
        for (FieldId fieldId : arrayCollectionOf2) {
            fieldId.setName(str);
            arrayCollection.add(fieldId.getKey());
        }
        return arrayCollection;
    }

    public boolean renameTypes(StringId stringId, Iterable<KeyPair<TypeKey, TypeKey>> iterable, boolean z, boolean z2) {
        Iterator<KeyPair<TypeKey, TypeKey>> it = iterable.iterator();
        while (it.hasNext()) {
            if (renameTypes(stringId, it.next(), z, z2)) {
                return true;
            }
        }
        return false;
    }

    public List<MethodKey> replace(MethodKey methodKey, String str) {
        List<MethodKey> listRename = rename(methodKey, str);
        if (!listRename.isEmpty()) {
            return listRename;
        }
        List<MethodId> list = CollectionUtil.toList(getItems(SectionType.METHOD_ID, methodKey));
        int size = list.size();
        if (size == 0) {
            return EmptyList.of();
        }
        ArrayCollection arrayCollection = new ArrayCollection(size);
        for (MethodId methodId : list) {
            methodId.setName(str);
            arrayCollection.add(methodId.getKey());
        }
        return arrayCollection;
    }

    public void save() throws IOException {
        this.dexSourceSet.saveAll();
    }

    public void setTag(Object obj) {
        this.mTag = obj;
    }

    public void setZipEntryMap(ZipEntryMap zipEntryMap) {
        getDexSourceSet().setZipEntryMap(zipEntryMap);
    }

    public int size() {
        return this.dexSourceSet.size();
    }

    public String toString() {
        return "DexFiles = " + size();
    }

    public void updateDexFileList() {
        Iterator<DexFile> it = iterator();
        while (it.hasNext()) {
            it.next().setDexDirectory(this);
        }
    }

    public void writeSmali(SmaliWriter smaliWriter, File file) throws IOException {
        Iterator<DexFile> it = iterator();
        while (it.hasNext()) {
            it.next().writeSmali(smaliWriter, file);
        }
    }

    public void save(File file) throws IOException {
        this.dexSourceSet.saveAll(file);
    }

    public static DexDirectory fromZip(ZipEntryMap zipEntryMap) throws IOException {
        return fromZip(zipEntryMap, null);
    }

    public int mergeAll(MergeOptions mergeOptions, Iterable<DexClass> iterable) {
        return mergeAll(mergeOptions, iterable.iterator());
    }

    public DexFile get(int i) {
        return this.dexSourceSet.getDexFile(i);
    }

    public Iterator<StringId> renameTypes(TypeKey typeKey, TypeKey typeKey2, boolean z, boolean z2) {
        return renameTypes(new KeyPair<>(typeKey, typeKey2), z, z2);
    }

    public Iterator<StringId> renameTypes(final KeyPair<TypeKey, TypeKey> keyPair, final boolean z, final boolean z2) {
        return FilterIterator.of(getClonedItems(SectionType.STRING_ID), new Predicate() { // from class: dq3
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return this.b.renameTypes((StringId) obj, (KeyPair<TypeKey, TypeKey>) keyPair, z, z2);
            }
        });
    }

    public Iterator<StringId> renameTypes(final Iterable<KeyPair<TypeKey, TypeKey>> iterable, final boolean z, final boolean z2) {
        return FilterIterator.of(getClonedItems(SectionType.STRING_ID), new Predicate() { // from class: eq3
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return this.b.renameTypes((StringId) obj, (Iterable<KeyPair<TypeKey, TypeKey>>) iterable, z, z2);
            }
        });
    }

    public Iterator<StringId> renameTypes(TypeKey typeKey, TypeKey typeKey2) {
        return renameTypes(typeKey, typeKey2, true, true);
    }

    public boolean renameTypes(StringId stringId, KeyPair<TypeKey, TypeKey> keyPair, boolean z, boolean z2) {
        DexClass dexClass;
        boolean zRenameTypeString = renameTypeString(stringId, keyPair, z, z2);
        if (zRenameTypeString && (dexClass = getDexClass(TypeKey.create(stringId.getString()))) != null) {
            dexClass.fixDalvikInnerClassName();
        }
        return zRenameTypeString;
    }

    public boolean containsDeepSearch(MethodKey methodKey) {
        DexClass dexClass = getDexClass(methodKey.getDeclaring());
        if (dexClass == null) {
            return false;
        }
        if (dexClass.containsDeclaredMethod(methodKey)) {
            return true;
        }
        Iterator<DexClass> overridingAndSuperTypes = dexClass.getOverridingAndSuperTypes();
        while (overridingAndSuperTypes.hasNext()) {
            if (overridingAndSuperTypes.next().containsDeclaredMethod(methodKey)) {
                return true;
            }
        }
        return false;
    }

    public boolean merge(DexClass dexClass) {
        return merge(new DexMergeOptions(), dexClass);
    }

    public void merge(DexDirectory dexDirectory) {
        merge(new DexMergeOptions(false), dexDirectory);
    }

    public void merge(MergeOptions mergeOptions, DexDirectory dexDirectory) {
        if (dexDirectory != this) {
            int mergeStartDexFile = mergeOptions.getMergeStartDexFile();
            int i = mergeStartDexFile;
            while (true) {
                DexFile dexFile = get(i);
                DexFile lastNonEmpty = dexDirectory.getLastNonEmpty(mergeOptions, 0);
                if (dexFile == null || lastNonEmpty == null) {
                    break;
                } else if (!dexFile.merge(mergeOptions, lastNonEmpty)) {
                    i++;
                }
            }
            if (i != mergeStartDexFile) {
                mergeOptions.setMergeStartDexFile(i);
            }
            shrink();
            dexDirectory.merge(mergeOptions);
            getDexSourceSet().merge(dexDirectory.getDexSourceSet());
            return;
        }
        w01.a("Cyclic merge");
    }

    private int distributeClasses(DexFile dexFile, int i) {
        DexDirectory dexDirectory = dexFile.getDexDirectory();
        int iDistributeClasses = 0;
        for (int i2 = 0; i2 < dexDirectory.size(); i2++) {
            DexLayout first = dexFile.getFirst();
            if (first != null) {
                iDistributeClasses += distributeClasses(first, dexDirectory.get(i2).getOrCreateFirst(), i);
            }
        }
        return iDistributeClasses;
    }

    public List<FieldKey> replace(FieldKey fieldKey, String str) {
        List<FieldKey> listRename = rename(fieldKey, str);
        if (!listRename.isEmpty()) {
            return listRename;
        }
        List<FieldId> list = CollectionUtil.toList(getItems(SectionType.FIELD_ID, fieldKey));
        int size = list.size();
        if (size == 0) {
            return EmptyList.of();
        }
        ArrayCollection arrayCollection = new ArrayCollection(size);
        for (FieldId fieldId : list) {
            fieldId.setName(str);
            arrayCollection.add(fieldId.getKey());
        }
        return arrayCollection;
    }

    private int distributeClasses(DexLayout dexLayout, DexLayout dexLayout2, int i) {
        ClassId classId;
        int i2 = 0;
        if (dexLayout.getDexLayoutBlock() == dexLayout2.getDexLayoutBlock()) {
            return 0;
        }
        BlockCreator itemArray = dexLayout.getSection(SectionType.CLASS_ID).getItemArray();
        ClassId classId2 = null;
        while (dexLayout.getDexClassesCount() > i && dexLayout2.getDexClassesCount() < i && (classId = (ClassId) itemArray.getLast()) != classId2) {
            dexLayout2.merge(classId);
            i2++;
            classId2 = classId;
        }
        return i2;
    }

    public void merge() {
        merge(new DexMergeOptions());
    }

    public void merge(MergeOptions mergeOptions) {
        if (size() < 2) {
            return;
        }
        int i = 0;
        while (true) {
            DexFile dexFile = get(i);
            int i2 = i + 1;
            DexFile lastNonEmpty = getLastNonEmpty(mergeOptions, i2);
            if (dexFile == null || lastNonEmpty == null) {
                break;
            } else if (!dexFile.merge(mergeOptions, lastNonEmpty)) {
                i = i2;
            }
        }
        shrink();
    }

    public List<MethodKey> rename(MethodKey methodKey, String str) {
        if (containsDeepSearch(methodKey.changeName(str))) {
            return EmptyList.of();
        }
        ArrayCollection<MethodId> arrayCollection = new ArrayCollection();
        arrayCollection.addAll(getMethodIds(methodKey));
        if (arrayCollection.size() == 0) {
            return EmptyList.of();
        }
        MethodKey methodKeyChangeName = methodKey.changeName(str);
        Iterator it = arrayCollection.iterator();
        while (it.hasNext()) {
            if (methodKeyChangeName.equals(((MethodId) it.next()).getKey())) {
                aca.a("Duplicate: ", methodKeyChangeName);
                return null;
            }
        }
        ArrayCollection arrayCollection2 = new ArrayCollection(arrayCollection.size());
        for (MethodId methodId : arrayCollection) {
            methodId.setName(str);
            arrayCollection2.add(methodId.getKey());
        }
        return arrayCollection2;
    }

    public int rename(TypeKey typeKey, TypeKey typeKey2) {
        int i = 0;
        if (!containsClass(typeKey2)) {
            Iterator<StringId> itRenameTypes = renameTypes(typeKey, typeKey2, true, true);
            while (itRenameTypes.hasNext()) {
                itRenameTypes.next();
                i++;
            }
            return i;
        }
        bq3.a("Duplicate: ", typeKey, " --> ", typeKey2);
        return 0;
    }
}
