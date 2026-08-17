package com.reandroid.dex.sections;

import com.reandroid.arsc.container.FixedBlockContainer;
import com.reandroid.arsc.io.BlockReader;
import com.reandroid.common.BytesOutputStream;
import com.reandroid.dex.base.DexException;
import com.reandroid.dex.common.DexUtils;
import com.reandroid.dex.common.FullRefresh;
import com.reandroid.dex.common.SectionItem;
import com.reandroid.dex.header.DexHeader;
import com.reandroid.dex.id.ClassId;
import com.reandroid.dex.id.StringId;
import com.reandroid.dex.key.Key;
import com.reandroid.dex.key.TypeKey;
import com.reandroid.dex.smali.model.SmaliClass;
import com.reandroid.utils.collection.CombiningIterator;
import com.reandroid.utils.collection.EmptyIterator;
import com.reandroid.utils.collection.IterableIterator;
import com.reandroid.utils.collection.MultiMap;
import com.reandroid.utils.io.FileUtil;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.function.Predicate;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class DexLayoutBlock extends FixedBlockContainer implements FullRefresh {
    private final MultiMap<TypeKey, ClassId> extendingClassMap;
    private final MultiMap<TypeKey, ClassId> interfaceMap;
    private Object mTag;
    private final SectionList sectionList;

    public DexLayoutBlock() {
        super(1);
        SectionList sectionList = new SectionList();
        this.sectionList = sectionList;
        addChild(0, sectionList);
        this.extendingClassMap = new MultiMap<>();
        this.interfaceMap = new MultiMap<>();
    }

    public static DexLayoutBlock createDefault() {
        DexLayoutBlock dexLayoutBlock = new DexLayoutBlock();
        SectionList sectionList = dexLayoutBlock.getSectionList();
        MapList mapList = sectionList.getMapList();
        mapList.getOrCreate(SectionType.HEADER);
        mapList.getOrCreate(SectionType.MAP_LIST);
        for (SectionType<?> sectionType : SectionType.getR8Order()) {
            sectionList.getOrCreateSection(sectionType);
        }
        sectionList.getMapList().linkHeader(sectionList.getHeader());
        return dexLayoutBlock;
    }

    private DexLayoutBlock getPreviousLayoutBlock() {
        DexContainerBlock dexContainerBlock = (DexContainerBlock) getParentInstance(DexContainerBlock.class);
        if (dexContainerBlock != null) {
            return dexContainerBlock.get(getIndex() - 1);
        }
        return null;
    }

    private void loadExtendingClassMap() {
        MultiMap<TypeKey, ClassId> multiMap = this.extendingClassMap;
        multiMap.clear();
        Section<ClassId> section = getSectionList().getSection(SectionType.CLASS_ID);
        if (section == null) {
            return;
        }
        multiMap.setInitialSize(section.getCount());
        for (ClassId classId : section) {
            TypeKey superClassKey = classId.getSuperClassKey();
            if (!DexUtils.isJavaFramework(superClassKey.getTypeName())) {
                multiMap.put(superClassKey, classId);
            }
        }
    }

    private void loadInterfacesMap() {
        MultiMap<TypeKey, ClassId> multiMap = this.interfaceMap;
        multiMap.clear();
        Section<ClassId> section = getSectionList().getSection(SectionType.CLASS_ID);
        if (section == null) {
            return;
        }
        for (ClassId classId : section) {
            for (TypeKey typeKey : classId.getInterfacesKey()) {
                if (!DexUtils.isJavaFramework(typeKey.getTypeName())) {
                    multiMap.put(typeKey, classId);
                }
            }
        }
    }

    private void updateChecksumAndSignature() {
        DexHeader header = getHeader();
        SectionList sectionList = getSectionList();
        int i = 0;
        while (i < 10) {
            if (!header.updateChecksum()) {
                if (i != 0) {
                    header.updateSignature();
                    header.updateChecksum();
                    return;
                }
                return;
            }
            sectionList.refresh();
            i++;
        }
        gke.a("Failed to update checksums, trial = ", i);
    }

    private void updateHeaderOffset() {
        int i;
        DexLayoutBlock previousLayoutBlock = getPreviousLayoutBlock();
        if (previousLayoutBlock != null) {
            DexHeader header = previousLayoutBlock.getHeader();
            i = header.getOffsetReference().get() + header.fileSize.get();
        } else {
            i = 0;
        }
        getHeader().getOffsetReference().set(i);
    }

    public void clear() {
        this.extendingClassMap.clear();
        this.interfaceMap.clear();
        getSectionList().clear();
    }

    public int clearEmptySections() {
        return getSectionList().clearEmptySections();
    }

    public void clearPoolMap() {
        this.extendingClassMap.clear();
        this.interfaceMap.clear();
        getSectionList().clearPoolMap();
    }

    public ClassId fromSmali(SmaliClass smaliClass) throws IOException {
        return getSectionList().fromSmali(smaliClass);
    }

    public <T1 extends SectionItem> Iterator<T1> getAll(SectionType<T1> sectionType, Key key) {
        Section<T1> section = getSection(sectionType);
        return section != null ? (Iterator<T1>) section.getAll(key) : EmptyIterator.of();
    }

    public byte[] getBytes() {
        BytesOutputStream bytesOutputStream = new BytesOutputStream(getFileSize());
        try {
            writeBytes(bytesOutputStream);
            bytesOutputStream.close();
        } catch (IOException unused) {
        }
        return bytesOutputStream.toByteArray();
    }

    public <T1 extends SectionItem> Iterator<T1> getClonedItems(SectionType<T1> sectionType) {
        Section<T1> section = getSectionList().getSection(sectionType);
        return section != null ? (Iterator<T1>) section.clonedIterator() : EmptyIterator.of();
    }

    public DexContainerBlock getDexContainerBlock() {
        return (DexContainerBlock) getParentInstance(DexContainerBlock.class);
    }

    public Iterator<ClassId> getExtendingClassIds(TypeKey typeKey) {
        if (this.extendingClassMap.size() == 0) {
            loadExtendingClassMap();
        }
        return this.extendingClassMap.getAll(typeKey);
    }

    public Iterator<ClassId> getExtendingOrImplementing(TypeKey typeKey) {
        return new IterableIterator<ClassId, ClassId>(CombiningIterator.two(getExtendingClassIds(typeKey), getImplementationIds(typeKey))) { // from class: com.reandroid.dex.sections.DexLayoutBlock.1
            public Iterator<ClassId> iterator(ClassId classId) {
                return CombiningIterator.singleOne(classId, DexLayoutBlock.this.getExtendingOrImplementing(classId.getKey()));
            }
        };
    }

    public int getFileSize() {
        return getHeader().getFileSize();
    }

    public DexHeader getHeader() {
        return getSectionList().getHeader();
    }

    public Iterator<ClassId> getImplementationIds(TypeKey typeKey) {
        if (this.interfaceMap.size() == 0) {
            loadInterfacesMap();
        }
        return this.interfaceMap.getAll(typeKey);
    }

    public <T1 extends SectionItem> T1 getItem(SectionType<T1> sectionType, Key key) {
        Section<T1> section = getSection(sectionType);
        if (section != null) {
            return (T1) section.getSectionItem(key);
        }
        return null;
    }

    public <T1 extends SectionItem> Iterator<T1> getItems(SectionType<T1> sectionType) {
        Section<T1> section = getSectionList().getSection(sectionType);
        return section != null ? (Iterator<T1>) section.iterator() : EmptyIterator.of();
    }

    public MapList getMapList() {
        return getSectionList().getMapList();
    }

    public Iterator<Marker> getMarkers() {
        return Marker.parse((Section<StringId>) getSection(SectionType.STRING_ID));
    }

    public <T1 extends SectionItem> Section<T1> getOrCreateSection(SectionType<T1> sectionType) {
        return getSectionList().getOrCreateSection(sectionType);
    }

    public <T1 extends SectionItem> Section<T1> getSection(SectionType<T1> sectionType) {
        return getSectionList().getSection(sectionType);
    }

    public SectionList getSectionList() {
        return this.sectionList;
    }

    public Iterator<StringId> getStrings() {
        return getItems(SectionType.STRING_ID);
    }

    public Object getTag() {
        return this.mTag;
    }

    public int getVersion() {
        return getHeader().getVersion();
    }

    public boolean isEmpty() {
        Section section = getSection(SectionType.CLASS_ID);
        return section == null || section.getCount() == 0;
    }

    public boolean merge(MergeOptions mergeOptions, DexLayoutBlock dexLayoutBlock) {
        if (dexLayoutBlock != this) {
            return getSectionList().merge(mergeOptions, dexLayoutBlock.getSectionList());
        }
        mergeOptions.onMergeError(this, getSectionList(), "Can not merge dex file to self");
        return false;
    }

    public void onPreRefresh() {
        super/*com.reandroid.arsc.base.BlockContainer*/.onPreRefresh();
        this.interfaceMap.clear();
        this.extendingClassMap.clear();
        updateHeaderOffset();
    }

    public void onRefreshed() {
        updateChecksumAndSignature();
    }

    public void readBytes(BlockReader blockReader, Predicate<SectionType<?>> predicate) throws IOException {
        getSectionList().readSections(blockReader, predicate);
    }

    @Override // com.reandroid.dex.common.FullRefresh
    public void refreshFull() throws DexException {
        onPreRefresh();
        sortStrings();
        getSectionList().refreshFull();
        onRefreshed();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public <T1 extends SectionItem> boolean removeEntries(SectionType<T1> sectionType, Predicate<T1> predicate) {
        Section<T1> section = getSection(sectionType);
        if (section != null) {
            return section.removeEntries(predicate);
        }
        return false;
    }

    public void removeSelf() {
        DexContainerBlock dexContainerBlock = getDexContainerBlock();
        if (dexContainerBlock != null) {
            dexContainerBlock.remove(this);
        }
    }

    public <T1 extends SectionItem> boolean removeWithKey(SectionType<T1> sectionType, Key key) {
        Section<T1> section = getSection(sectionType);
        if (section != null) {
            return section.remove(key);
        }
        return false;
    }

    public <T1 extends SectionItem> boolean removeWithKeys(SectionType<T1> sectionType, Predicate<? super Key> predicate) {
        Section<T1> section = getSection(sectionType);
        if (section != null) {
            return section.removeWithKeys(predicate);
        }
        return false;
    }

    public void setTag(Object obj) {
        this.mTag = obj;
    }

    public void setVersion(int i) {
        getHeader().setVersion(i);
    }

    public void sortSection(SectionType<?>[] sectionTypeArr) {
        refresh();
        getSectionList().sortSection(sectionTypeArr);
        refresh();
    }

    public boolean sortStrings() {
        return getSectionList().sortStrings();
    }

    public void write(File file) throws IOException {
        OutputStream outputStream = FileUtil.outputStream(file);
        writeBytes(outputStream);
        outputStream.close();
    }

    public void clearPoolMap(SectionType<?> sectionType) {
        getSectionList().clearPoolMap(sectionType);
    }

    public boolean merge(MergeOptions mergeOptions, ClassId classId) {
        return getSectionList().merge(mergeOptions, classId);
    }
}
