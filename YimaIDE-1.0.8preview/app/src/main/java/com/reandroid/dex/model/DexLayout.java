package com.reandroid.dex.model;

import com.reandroid.common.Origin;
import com.reandroid.dex.base.DexException;
import com.reandroid.dex.common.AccessFlag;
import com.reandroid.dex.common.DexUtils;
import com.reandroid.dex.common.SectionItem;
import com.reandroid.dex.id.ClassId;
import com.reandroid.dex.id.StringId;
import com.reandroid.dex.key.Key;
import com.reandroid.dex.key.TypeKey;
import com.reandroid.dex.model.DexLayout;
import com.reandroid.dex.sections.DexContainerBlock;
import com.reandroid.dex.sections.DexLayoutBlock;
import com.reandroid.dex.sections.MapItem;
import com.reandroid.dex.sections.Marker;
import com.reandroid.dex.sections.MergeOptions;
import com.reandroid.dex.sections.Section;
import com.reandroid.dex.sections.SectionType;
import com.reandroid.dex.smali.SmaliReader;
import com.reandroid.dex.smali.SmaliWriter;
import com.reandroid.dex.smali.model.SmaliClass;
import com.reandroid.utils.ObjectsUtil;
import com.reandroid.utils.collection.CollectionUtil;
import com.reandroid.utils.collection.ComputeIterator;
import com.reandroid.utils.collection.IterableIterator;
import com.reandroid.utils.collection.SingleIterator;
import com.reandroid.utils.io.FileByteSource;
import com.reandroid.utils.io.FileIterator;
import defpackage.qq3;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.function.Function;
import java.util.function.Predicate;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class DexLayout implements DexClassModule, Closeable, Iterable<DexClass> {
    public static final String DIRECTORY_PREFIX = ObjectsUtil.of("layout.");
    private boolean closed;
    private final DexFile dexFile;
    private final DexLayoutBlock dexLayoutBlock;

    public DexLayout(DexFile dexFile, DexLayoutBlock dexLayoutBlock) {
        this.dexFile = dexFile;
        this.dexLayoutBlock = dexLayoutBlock;
        dexLayoutBlock.setTag(this);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public DexClass create(ClassId classId) {
        return new DexClass(this, classId);
    }

    public static /* synthetic */ DexSectionInfo d(DexLayout dexLayout, MapItem mapItem) {
        dexLayout.getClass();
        return new DexSectionInfo(dexLayout, mapItem);
    }

    public static DexLayout findDexFile(ClassId classId) {
        if (classId == null) {
            return null;
        }
        return findDexFile((DexLayoutBlock) classId.getParentInstance(DexLayoutBlock.class));
    }

    private void requireNotClosed() throws IOException {
        if (isClosed()) {
            a16.a("Closed");
        }
    }

    @Override // com.reandroid.dex.model.DexClassModule
    public void addMarker(Marker marker) {
        if (marker.getStringId() == null) {
            marker.setStringId((StringId) getSection(SectionType.STRING_ID).createItem());
        }
        marker.save();
    }

    public int clearDuplicateData() {
        return getDexLayoutBlock().getSectionList().clearDuplicateData();
    }

    public void clearEmptySections() {
        getDexLayoutBlock().clearEmptySections();
    }

    @Override // com.reandroid.dex.model.DexClassModule, com.reandroid.dex.model.DexClassRepository
    public void clearPoolMap() {
        getDexLayoutBlock().clearPoolMap();
    }

    public int clearUnused() {
        return getDexLayoutBlock().getSectionList().clearUnused();
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        if (this.closed) {
            return;
        }
        this.closed = true;
        getDexLayoutBlock().clear();
    }

    public DexClass fromSmali(SmaliReader smaliReader) throws IOException {
        requireNotClosed();
        SmaliClass smaliClass = new SmaliClass();
        smaliClass.parse(smaliReader);
        DexClass dexClassFromSmali = fromSmali(smaliClass);
        smaliReader.skipWhitespacesOrComment();
        return dexClassFromSmali;
    }

    public void fromSmaliAll(SmaliReader smaliReader) throws IOException {
        smaliReader.skipWhitespacesOrComment();
        while (!smaliReader.finished()) {
            fromSmali(smaliReader);
            smaliReader.skipWhitespacesOrComment();
        }
    }

    public byte[] getBytes() {
        if (isClosed()) {
            return null;
        }
        return isEmpty() ? new byte[0] : getDexLayoutBlock().getBytes();
    }

    public DexClassRepository getClassRepository() {
        return getDexFile();
    }

    @Override // com.reandroid.dex.model.DexClassModule, com.reandroid.dex.model.DexClassRepository
    public DexClass getDexClass(TypeKey typeKey) {
        ClassId classId = (ClassId) getItem(SectionType.CLASS_ID, typeKey);
        if (classId == null) {
            return null;
        }
        return create(classId);
    }

    @Override // com.reandroid.dex.model.DexClassModule, com.reandroid.dex.model.DexClassRepository
    public Iterator<DexClass> getDexClasses(Predicate<? super TypeKey> predicate) {
        return ComputeIterator.of(getItemsIfKey(SectionType.CLASS_ID, (Predicate) ObjectsUtil.cast(predicate)), new qq3(this));
    }

    @Override // com.reandroid.dex.model.DexClassModule, com.reandroid.dex.model.DexClassRepository
    public Iterator<DexClass> getDexClassesCloned(Predicate<? super TypeKey> predicate) {
        return ComputeIterator.of(getClonedItemsIfKey(SectionType.CLASS_ID, (Predicate) ObjectsUtil.cast(predicate)), new qq3(this));
    }

    public int getDexClassesCountForDebug() {
        SectionType<ClassId> sectionType = SectionType.CLASS_ID;
        Section section = getSection(sectionType);
        if (section != null) {
            return section.getCount();
        }
        DexSectionInfo sectionInfo = getSectionInfo(sectionType);
        if (sectionInfo != null) {
            return sectionInfo.getCount();
        }
        return 0;
    }

    public DexFile getDexFile() {
        return this.dexFile;
    }

    public Iterator<DexInstruction> getDexInstructions() {
        return new IterableIterator<DexClass, DexInstruction>(getDexClasses()) { // from class: com.reandroid.dex.model.DexLayout.1
            public Iterator<DexInstruction> iterator(DexClass dexClass) {
                return dexClass.getDexInstructions();
            }
        };
    }

    public Iterator<DexInstruction> getDexInstructionsCloned() {
        return new IterableIterator<DexClass, DexInstruction>(getDexClassesCloned()) { // from class: com.reandroid.dex.model.DexLayout.2
            public Iterator<DexInstruction> iterator(DexClass dexClass) {
                return dexClass.getDexInstructions();
            }
        };
    }

    public DexLayoutBlock getDexLayoutBlock() {
        return this.dexLayoutBlock;
    }

    @Override // com.reandroid.dex.model.DexClassModule
    public Iterator<DexClass> getExtendingClasses(TypeKey typeKey) {
        return ComputeIterator.of(getDexLayoutBlock().getExtendingClassIds(typeKey), new qq3(this));
    }

    @Override // com.reandroid.dex.model.DexClassModule
    public Iterator<DexClass> getImplementClasses(TypeKey typeKey) {
        return ComputeIterator.of(getDexLayoutBlock().getImplementationIds(typeKey), new qq3(this));
    }

    public int getIndex() {
        return getDexLayoutBlock().getIndex();
    }

    @Override // com.reandroid.dex.model.DexClassModule, com.reandroid.dex.model.DexClassRepository
    public Iterator<Marker> getMarkers() {
        return getDexLayoutBlock().getMarkers();
    }

    public String getName() {
        return DIRECTORY_PREFIX + getIndex();
    }

    @Override // com.reandroid.dex.model.DexClassModule
    public int getOffset() {
        return getDexLayoutBlock().getHeader().getOffset();
    }

    @Override // com.reandroid.dex.model.DexClassModule
    public DexClass getOrCreateClass(TypeKey typeKey) {
        DexClass dexClassSearchClass = searchClass(typeKey);
        return dexClassSearchClass != null ? dexClassSearchClass : create(getOrCreateClassId(typeKey));
    }

    public ClassId getOrCreateClassId(TypeKey typeKey) {
        Section orCreateSection = getOrCreateSection(SectionType.CLASS_ID);
        ClassId classId = (ClassId) orCreateSection.get(typeKey);
        if (classId != null) {
            return classId;
        }
        ClassId classId2 = (ClassId) orCreateSection.getOrCreate(typeKey);
        classId2.getOrCreateClassData();
        classId2.setSuperClass(TypeKey.OBJECT);
        classId2.setSourceFile(DexUtils.toSourceFileName(typeKey.getTypeName()));
        classId2.addAccessFlag(AccessFlag.PUBLIC);
        return classId2;
    }

    public Marker getOrCreateMarker() {
        Marker marker = (Marker) CollectionUtil.getFirst(getMarkers());
        if (marker != null) {
            return marker;
        }
        Marker markerCreateR8 = Marker.createR8();
        markerCreateR8.setStringId((StringId) getSection(SectionType.STRING_ID).createItem());
        markerCreateR8.save();
        return markerCreateR8;
    }

    @Override // com.reandroid.dex.model.DexClassModule
    public <T1 extends SectionItem> Section<T1> getOrCreateSection(SectionType<T1> sectionType) {
        return getDexLayoutBlock().getOrCreateSection(sectionType);
    }

    @Override // com.reandroid.dex.model.DexClassRepository
    public DexClassRepository getRootRepository() {
        return getDexFile().getRootRepository();
    }

    @Override // com.reandroid.dex.model.DexClassModule
    public <T1 extends SectionItem> Section<T1> getSection(SectionType<T1> sectionType) {
        return getDexLayoutBlock().getSection(sectionType);
    }

    @Override // com.reandroid.dex.model.DexClassModule
    public Iterator<DexSectionInfo> getSectionInfo() {
        return ComputeIterator.of(getDexLayoutBlock().getMapList().iterator(), new Function() { // from class: rq3
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return DexLayout.d(this.b, (MapItem) obj);
            }
        });
    }

    @Override // com.reandroid.dex.model.DexClassModule, com.reandroid.dex.model.DexClassRepository
    public int getVersion() {
        return getDexLayoutBlock().getVersion();
    }

    public boolean isClosed() {
        return this.closed;
    }

    public boolean isEmpty() {
        return getDexLayoutBlock().isEmpty();
    }

    @Override // com.reandroid.dex.model.DexClassModule
    public boolean isMultiLayoutEntry() {
        DexContainerBlock dexContainerBlock = getDexLayoutBlock().getDexContainerBlock();
        if (dexContainerBlock != null) {
            return dexContainerBlock.isMultiLayout();
        }
        return false;
    }

    @Override // java.lang.Iterable
    public Iterator<DexClass> iterator() {
        return getDexClasses();
    }

    public boolean merge(MergeOptions mergeOptions, DexLayout dexLayout) {
        if (dexLayout == null || dexLayout.isEmpty()) {
            return false;
        }
        return getDexLayoutBlock().merge(mergeOptions, dexLayout.getDexLayoutBlock());
    }

    @Override // com.reandroid.dex.model.DexClassRepository
    public Iterator<DexClassModule> modules() {
        return SingleIterator.of(this);
    }

    public void parseSmaliDirectory(File file) throws IOException {
        requireNotClosed();
        if (!file.isDirectory()) {
            s8g.a("No such directory: ", file);
            return;
        }
        FileIterator fileIterator = new FileIterator(file, FileIterator.getExtensionFilter(".smali"));
        FileByteSource fileByteSource = new FileByteSource();
        SmaliReader smaliReader = new SmaliReader(fileByteSource);
        DexLayoutBlock dexLayoutBlock = getDexLayoutBlock();
        while (fileIterator.hasNext()) {
            smaliReader.reset();
            File next = fileIterator.next();
            fileByteSource.setFile(next);
            smaliReader.setOrigin(Origin.createNew(next));
            SmaliClass smaliClass = new SmaliClass();
            smaliClass.parse(smaliReader);
            dexLayoutBlock.fromSmali(smaliClass);
        }
        sort();
        shrink();
    }

    public void parseSmaliFile(File file) throws IOException {
        requireNotClosed();
        fromSmali(SmaliReader.of(file));
    }

    public String printSectionInfo(boolean z) {
        boolean z2;
        StringBuilder sb = new StringBuilder();
        if (isMultiLayoutEntry()) {
            sb.append(getName());
            sb.append(", offset = ");
            sb.append(getOffset());
            z2 = true;
        } else {
            z2 = false;
        }
        Iterator<DexSectionInfo> sectionInfo = getSectionInfo();
        while (sectionInfo.hasNext()) {
            DexSectionInfo next = sectionInfo.next();
            if (z2) {
                sb.append('\n');
            }
            sb.append(' ');
            int index = next.getIndex();
            if (index < 10) {
                sb.append(' ');
            }
            sb.append(index);
            sb.append(") ");
            sb.append(next.print(z));
            z2 = true;
        }
        return sb.toString();
    }

    public void refresh() {
        getDexLayoutBlock().refresh();
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: com.reandroid.dex.base.DexException */
    @Override // com.reandroid.dex.common.FullRefresh
    public void refreshFull() throws DexException {
        getDexLayoutBlock().refreshFull();
    }

    @Override // com.reandroid.dex.model.DexClassModule, com.reandroid.dex.model.DexClassRepository
    public boolean removeClasses(final Predicate<? super DexClass> predicate) {
        return getDexLayoutBlock().removeEntries(SectionType.CLASS_ID, new Predicate() { // from class: sq3
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return predicate.test(this.b.create((ClassId) obj));
            }
        });
    }

    @Override // com.reandroid.dex.model.DexClassModule, com.reandroid.dex.model.DexClassRepository
    public <T1 extends SectionItem> boolean removeEntries(SectionType<T1> sectionType, Predicate<T1> predicate) {
        return getDexLayoutBlock().removeEntries(sectionType, predicate);
    }

    @Override // com.reandroid.dex.model.DexClassModule, com.reandroid.dex.model.DexClassRepository
    public <T1 extends SectionItem> boolean removeEntriesWithKey(SectionType<T1> sectionType, Predicate<? super Key> predicate) {
        return getDexLayoutBlock().removeWithKeys(sectionType, predicate);
    }

    @Override // com.reandroid.dex.model.DexClassModule, com.reandroid.dex.model.DexClassRepository
    public <T1 extends SectionItem> boolean removeEntry(SectionType<T1> sectionType, Key key) {
        return getDexLayoutBlock().removeWithKey(sectionType, key);
    }

    @Override // com.reandroid.dex.model.DexClassModule, com.reandroid.dex.model.DexClassRepository
    public void setVersion(int i) {
        getDexLayoutBlock().setVersion(i);
    }

    @Override // com.reandroid.dex.model.DexClassModule, com.reandroid.dex.model.DexClassRepository
    public int shrink() {
        return getDexLayoutBlock().getSectionList().shrink();
    }

    @Override // com.reandroid.dex.model.DexClassModule, com.reandroid.dex.model.DexClassRepository
    public boolean sort() {
        return getDexLayoutBlock().sortStrings();
    }

    public void sortSection(SectionType<?>[] sectionTypeArr) {
        refresh();
        getDexLayoutBlock().sortSection(sectionTypeArr);
        refresh();
    }

    public String toString() {
        int count;
        StringBuilder sb = new StringBuilder();
        if (isMultiLayoutEntry()) {
            sb.append("offset = ");
            sb.append(getOffset());
            sb.append(", ");
        }
        sb.append("version = ");
        sb.append(getVersion());
        sb.append(", classes = ");
        SectionType<ClassId> sectionType = SectionType.CLASS_ID;
        if (getSections(sectionType).hasNext()) {
            count = getDexClassesCount();
        } else {
            DexSectionInfo sectionInfo = getSectionInfo(sectionType);
            count = sectionInfo != null ? sectionInfo.getCount() : 0;
        }
        sb.append(count);
        return sb.toString();
    }

    public void writeSmali(SmaliWriter smaliWriter, File file) throws IOException {
        Iterator<DexClass> dexClasses = getDexClasses();
        while (dexClasses.hasNext()) {
            dexClasses.next().writeSmali(smaliWriter, file);
        }
    }

    public static DexLayout findDexFile(DexLayoutBlock dexLayoutBlock) {
        if (dexLayoutBlock == null) {
            return null;
        }
        Object tag = dexLayoutBlock.getTag();
        if (tag instanceof DexLayout) {
            return (DexLayout) tag;
        }
        return null;
    }

    public DexClass fromSmali(SmaliClass smaliClass) throws IOException {
        requireNotClosed();
        return create(getDexLayoutBlock().fromSmali(smaliClass));
    }

    public boolean merge(MergeOptions mergeOptions, DexClass dexClass) {
        return merge(mergeOptions, dexClass.getId());
    }

    public boolean merge(ClassId classId) {
        return merge(new DexMergeOptions(true), classId);
    }

    public boolean merge(MergeOptions mergeOptions, ClassId classId) {
        return getDexLayoutBlock().merge(mergeOptions, classId);
    }

    public boolean merge(DexClass dexClass) {
        return merge(new DexMergeOptions(true), dexClass);
    }
}
