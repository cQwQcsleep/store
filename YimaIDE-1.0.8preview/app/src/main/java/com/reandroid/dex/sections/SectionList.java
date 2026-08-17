package com.reandroid.dex.sections;

import com.reandroid.arsc.base.BlockCreator;
import com.reandroid.arsc.base.OffsetSupplier;
import com.reandroid.arsc.container.BlockList;
import com.reandroid.arsc.container.FixedBlockContainer;
import com.reandroid.arsc.io.BlockReader;
import com.reandroid.arsc.item.IntegerReference;
import com.reandroid.common.ArraySupplier;
import com.reandroid.dex.common.FullRefresh;
import com.reandroid.dex.common.SectionItem;
import com.reandroid.dex.common.SectionTool;
import com.reandroid.dex.header.DexHeader;
import com.reandroid.dex.id.ClassId;
import com.reandroid.dex.id.IdItem;
import com.reandroid.dex.key.FieldKey;
import com.reandroid.dex.key.Key;
import com.reandroid.dex.key.MethodKey;
import com.reandroid.dex.key.ProtoKey;
import com.reandroid.dex.key.StringKey;
import com.reandroid.dex.key.TypeKey;
import com.reandroid.dex.key.TypeListKey;
import com.reandroid.dex.sections.DataSection;
import com.reandroid.dex.sections.IdSection;
import com.reandroid.dex.sections.Section;
import com.reandroid.dex.sections.SectionList;
import com.reandroid.dex.smali.model.SmaliClass;
import com.reandroid.utils.collection.ArraySupplierIterator;
import com.reandroid.utils.collection.CollectionUtil;
import com.reandroid.utils.collection.CombiningIterator;
import defpackage.h0f;
import defpackage.krd;
import java.io.IOException;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class SectionList extends FixedBlockContainer implements SectionTool, OffsetSupplier, Iterable<Section<?>>, ArraySupplier<Section<?>>, FullRefresh {
    private final IntegerReference baseOffset;
    private final BlockList<DataSection<?>> dataSectionList;
    private final DexHeader dexHeader;
    private final Section<DexHeader> dexHeaderSection;
    private final BlockList<IdSection<?>> idSectionList;
    private boolean mReading;
    private final MapList mapList;
    private final Section<MapList> mapListSection;
    private final Map<SectionType<?>, Section<?>> typeMap;

    public SectionList() {
        super(4);
        BlockList<IdSection<?>> blockList = new BlockList<>();
        this.idSectionList = blockList;
        BlockList<DataSection<?>> blockList2 = new BlockList<>();
        this.dataSectionList = blockList2;
        DexHeader dexHeader = new DexHeader();
        IntegerReference offsetReference = dexHeader.getOffsetReference();
        this.baseOffset = offsetReference;
        SectionType<DexHeader> sectionType = SectionType.HEADER;
        Section sectionCreateSpecialSection = sectionType.createSpecialSection(offsetReference);
        sectionCreateSpecialSection.add(dexHeader);
        this.dexHeaderSection = sectionCreateSpecialSection;
        SectionType<MapList> sectionType2 = SectionType.MAP_LIST;
        Section sectionCreateSpecialSection2 = sectionType2.createSpecialSection(dexHeader.map);
        MapList mapList = new MapList(dexHeader.map);
        sectionCreateSpecialSection2.add(mapList);
        this.mapListSection = sectionCreateSpecialSection2;
        HashMap map = new HashMap();
        this.typeMap = map;
        addChild(0, sectionCreateSpecialSection);
        addChild(1, blockList);
        addChild(2, blockList2);
        addChild(3, sectionCreateSpecialSection2);
        this.dexHeader = dexHeader;
        this.mapList = mapList;
        map.put(sectionType, sectionCreateSpecialSection);
        map.put(sectionType2, sectionCreateSpecialSection2);
    }

    public static /* synthetic */ int b(Section section, Section section2) {
        if (section == section2) {
            return 0;
        }
        if (section == null) {
            return 1;
        }
        return section.compareOffset(section2);
    }

    private boolean canAddAll(Collection<IdItem> collection) {
        Iterator<IdSection<?>> idSections = getIdSections();
        while (idSections.hasNext()) {
            if (!idSections.next().canAddAll(collection, 200)) {
                return false;
            }
        }
        return true;
    }

    private void clearUsageTypes() {
        Iterator<Section<?>> sections = getSections();
        while (sections.hasNext()) {
            sections.next().clearUsageTypes();
        }
    }

    private static <T1 extends Section<?>> Comparator<T1> getOffsetComparator() {
        return new Comparator() { // from class: j1d
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                return SectionList.b((Section) obj, (Section) obj2);
            }
        };
    }

    private void loadSection(MapItem mapItem, BlockReader blockReader) throws IOException {
        Section section = getSection(mapItem.getSectionType());
        if (section == null) {
            section = mapItem.createNewSection();
            add(section);
        }
        section.readBytes(blockReader);
    }

    private void readBody(BlockReader blockReader, Predicate<SectionType<?>> predicate) throws IOException {
        for (MapItem mapItem : this.mapList.getBodyReaderSorted()) {
            SectionType<?> sectionType = mapItem.getSectionType();
            if (predicate == null || predicate.test(sectionType)) {
                loadSection(mapItem, blockReader);
            }
        }
        this.idSectionList.trimToSize();
        this.dataSectionList.trimToSize();
        this.idSectionList.sort(getOffsetComparator());
        this.dataSectionList.sort(getOffsetComparator());
        this.mapList.linkHeader(this.dexHeader);
    }

    private void readSpecialSections(BlockReader blockReader) throws IOException {
        getSection(SectionType.HEADER).readBytes(blockReader);
        getSection(SectionType.MAP_LIST).readBytes(blockReader);
    }

    private boolean sortItems(SectionType<?> sectionType) {
        Section section = getSection(sectionType);
        if (section != null) {
            return section.sort();
        }
        return false;
    }

    public <T1 extends SectionItem> Section<T1> add(Section<T1> section) {
        SectionType<T> sectionType = section.getSectionType();
        Section<T1> section2 = getSection(sectionType);
        if (section2 == section) {
            return section2;
        }
        if (section2 != null) {
            yve.a("Already contains section type: ", sectionType, ", existing = ", section2, ", section = ", section);
            return null;
        }
        if (sectionType.isIdSection()) {
            this.idSectionList.add((IdSection) section);
        } else {
            if (!sectionType.isDataSection()) {
                h0f.a("Unknown section type: ", sectionType, ", ", section);
                return null;
            }
            this.dataSectionList.add((DataSection) section);
        }
        this.typeMap.put((SectionType<?>) section.getSectionType(), (Section<?>) section);
        return section;
    }

    public void clear() {
        Iterator<Section<?>> sections = getSections();
        while (sections.hasNext()) {
            sections.next().onRemove(this);
        }
        this.idSectionList.clearChildes();
        this.dataSectionList.clearChildes();
        this.typeMap.clear();
    }

    public int clearDuplicateData() {
        refresh();
        int i = 0;
        for (SectionType<?> sectionType : SectionType.getRemoveOrderList()) {
            Section section = getSection(sectionType);
            if (section != null) {
                int iClearDuplicates = section.getPool().clearDuplicates();
                i += iClearDuplicates;
                if (iClearDuplicates != 0) {
                    section.refresh();
                }
            }
        }
        if (i != 0) {
            refresh();
        }
        return i;
    }

    public int clearEmptySections() {
        int i = 0;
        for (Section<?> section : CollectionUtil.toList(getSections())) {
            if (section.isEmpty()) {
                remove(section);
                i++;
            }
        }
        return i;
    }

    public void clearPoolMap() {
        Iterator<Section<?>> it = iterator();
        while (it.hasNext()) {
            it.next().clearPoolMap();
        }
    }

    public int clearUnused() {
        clearUsageTypes();
        refresh();
        int iClearUnused = 0;
        for (SectionType<?> sectionType : SectionType.getRemoveOrderList()) {
            Section section = getSection(sectionType);
            if (section != null) {
                iClearUnused += section.clearUnused();
            }
        }
        return iClearUnused;
    }

    public boolean contains(Key key) {
        if (key == null) {
            return false;
        }
        if (key instanceof StringKey) {
            return contains(SectionType.STRING_ID, key);
        }
        if (key instanceof TypeKey) {
            return contains(SectionType.TYPE_ID, key);
        }
        if (key instanceof FieldKey) {
            return contains(SectionType.FIELD_ID, key);
        }
        if (key instanceof ProtoKey) {
            return contains(SectionType.PROTO_ID, key);
        }
        if (key instanceof MethodKey) {
            return contains(SectionType.METHOD_ID, key);
        }
        if (key instanceof TypeListKey) {
            return contains(SectionType.TYPE_LIST, key);
        }
        krd.a("Unknown key type: ", key.getClass(), ", '", key, "'");
        return false;
    }

    public ClassId fromSmali(SmaliClass smaliClass) throws IOException {
        ClassId classId = (ClassId) getOrCreateSectionItem(SectionType.CLASS_ID, smaliClass.getKey());
        classId.fromSmali(smaliClass);
        return classId;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.reandroid.common.ArraySupplier
    public Section<?> get(int i) {
        if (i == 0) {
            return this.dexHeaderSection;
        }
        if (i == getCount() - 1) {
            return this.mapListSection;
        }
        return i <= this.idSectionList.size() ? this.idSectionList.get(i - 1) : this.dataSectionList.get((i - 1) - this.idSectionList.size());
    }

    @Override // com.reandroid.common.CountSupplier
    public int getCount() {
        return this.idSectionList.size() + 2 + this.dataSectionList.size();
    }

    public Iterator<DataSection<?>> getDataSections() {
        return this.dataSectionList.iterator();
    }

    public DexHeader getHeader() {
        return this.dexHeader;
    }

    public Iterator<IdSection<?>> getIdSections() {
        return this.idSectionList.iterator();
    }

    public <T1 extends SectionItem> T1 getLoaded(SectionType<T1> sectionType, Key key) {
        Section<T1> section = getSection(sectionType);
        if (section != null) {
            return (T1) section.getSectionItem(key);
        }
        return null;
    }

    public MapList getMapList() {
        return this.mapList;
    }

    @Override // com.reandroid.arsc.base.OffsetSupplier, com.reandroid.dex.base.DexArraySupplier
    public IntegerReference getOffsetReference() {
        return this.baseOffset;
    }

    @Override // com.reandroid.dex.common.SectionTool
    public <T1 extends SectionItem> Section<T1> getOrCreateSection(SectionType<T1> sectionType) {
        Section<T1> section = getSection(sectionType);
        if (section != null) {
            return section;
        }
        if (sectionType == SectionType.MAP_LIST || sectionType == SectionType.HEADER) {
            return null;
        }
        MapItem orCreate = getMapList().getOrCreate(sectionType);
        Section<T1> sectionCreateNewSection = orCreate.createNewSection();
        add(sectionCreateNewSection);
        if (!isReading()) {
            sortSection(SectionType.getR8Order());
            orCreate.link(getHeader());
        }
        return sectionCreateNewSection;
    }

    @Override // com.reandroid.dex.common.SectionTool
    public <T1 extends SectionItem> Section<T1> getSection(SectionType<T1> sectionType) {
        return (Section) this.typeMap.get(sectionType);
    }

    @Override // com.reandroid.dex.common.SectionTool
    public SectionList getSectionList() {
        return this;
    }

    public Iterator<Section<?>> getSections() {
        return new CombiningIterator(getIdSections(), getDataSections());
    }

    public int indexOf(Section<?> section) {
        if (section == this.dexHeaderSection) {
            return 0;
        }
        if (section == this.mapListSection) {
            return getCount() - 1;
        }
        if (section == this.idSectionList.get(section.getIndex())) {
            return section.getIndex() + 1;
        }
        if (section == this.dataSectionList.get(section.getIndex())) {
            return this.idSectionList.size() + 1 + section.getIndex();
        }
        return -1;
    }

    @Override // com.reandroid.dex.common.SectionTool
    public boolean isReading() {
        return this.mReading;
    }

    @Override // java.lang.Iterable
    public Iterator<Section<?>> iterator() {
        return ArraySupplierIterator.of(this);
    }

    public void keyChangedInternal(SectionItem sectionItem, SectionType<?> sectionType, Key key) {
        ClassId classId;
        Section section = getSection(sectionType);
        if (section == null) {
            return;
        }
        section.keyChanged(sectionItem, key);
        if (sectionType != SectionType.TYPE_ID || (classId = (ClassId) getLoaded(SectionType.CLASS_ID, key)) == null) {
            return;
        }
        classId.getKey();
    }

    public boolean merge(MergeOptions mergeOptions, SectionList sectionList) {
        boolean z = false;
        if (sectionList == this) {
            mergeOptions.onMergeError((DexLayoutBlock) getParentInstance(DexLayoutBlock.class), sectionList, "Can not merge with self");
            return false;
        }
        if (sectionList.getParent() == null) {
            mergeOptions.onMergeError((DexLayoutBlock) getParentInstance(DexLayoutBlock.class), sectionList, "Destroyed section list");
            return false;
        }
        SectionType<ClassId> sectionType = SectionType.CLASS_ID;
        Section section = sectionList.getSection(sectionType);
        if (section != null && section.getCount() != 0) {
            Section orCreateSection = getOrCreateSection(sectionType);
            BlockCreator itemArray = section.getItemArray();
            int size = itemArray.size() - 1;
            boolean z2 = false;
            while (true) {
                if (size < 0) {
                    z = true;
                    break;
                }
                ClassId classId = (ClassId) itemArray.get(size);
                TypeKey key = classId.getKey();
                if (!mergeOptions.skipMerging(classId, key)) {
                    if (orCreateSection.contains(key)) {
                        mergeOptions.onDuplicate(classId);
                    } else {
                        if (!canAddAll(classId.listUsedIds())) {
                            mergeOptions.onDexFull((DexLayoutBlock) getParentInstance(DexLayoutBlock.class), classId);
                            break;
                        }
                        ((ClassId) orCreateSection.getOrCreate(classId.getKey())).merge(classId);
                        mergeOptions.onMergeSuccess(classId, key);
                        if (mergeOptions.relocateClass()) {
                            classId.removeSelf();
                        }
                        z2 = true;
                    }
                }
                size--;
            }
            if (section.getCount() == 0) {
                ((DexLayoutBlock) section.getSectionList().getParentInstance(DexLayoutBlock.class)).clear();
            }
            if (z2) {
                sortStrings();
                refresh();
            }
        }
        return z;
    }

    public void onReadBytes(BlockReader blockReader) throws IOException {
        readSections(blockReader, null);
    }

    public void onRefreshed() {
        super.onRefreshed();
        this.mapList.refresh();
    }

    public void readSections(BlockReader blockReader, Predicate<SectionType<?>> predicate) throws IOException {
        this.mReading = true;
        int position = blockReader.getPosition();
        DexHeader header = getHeader();
        header.getOffsetReference().set(position);
        readSpecialSections(blockReader);
        readBody(blockReader, predicate);
        blockReader.seek(position + header.getFileSize());
        this.mReading = false;
    }

    @Override // com.reandroid.dex.common.FullRefresh
    public void refreshFull() {
        for (SectionType<?> sectionType : SectionType.getSortSectionsOrder()) {
            Section section = getSection(sectionType);
            if (section != null) {
                section.refreshFull();
            }
        }
        clearUnused();
        clearDuplicateData();
        if (clearEmptySections() != 0) {
            refresh();
        }
    }

    public void remove(Section<?> section) {
        if (section == null) {
            return;
        }
        SectionType<T> sectionType = section.getSectionType();
        if (this.typeMap.remove(sectionType) != section) {
            return;
        }
        section.onRemove(this);
        if (sectionType.isDataSection()) {
            this.dataSectionList.remove((DataSection) section);
        } else if (sectionType.isIdSection()) {
            this.idSectionList.remove((IdSection) section);
        }
    }

    public int shrink() {
        int iClearUnused = 0;
        while (true) {
            int iClearUnused2 = clearUnused();
            if (iClearUnused2 == 0) {
                break;
            }
            iClearUnused += iClearUnused2;
        }
        while (true) {
            int iClearDuplicateData = clearDuplicateData();
            if (iClearDuplicateData == 0) {
                return iClearUnused + clearEmptySections();
            }
            iClearUnused = iClearUnused + iClearDuplicateData + clearUnused();
        }
    }

    public void sortSection(SectionType<?>[] sectionTypeArr) {
        this.idSectionList.sort(SectionType.comparator(sectionTypeArr, new Function() { // from class: k1d
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((IdSection) obj).getSectionType();
            }
        }));
        this.dataSectionList.sort(SectionType.comparator(sectionTypeArr, new Function() { // from class: l1d
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((DataSection) obj).getSectionType();
            }
        }));
        this.mapList.sortMapItems(sectionTypeArr);
    }

    public boolean sortStrings() {
        Section section = getSection(SectionType.STRING_DATA);
        boolean zSort = section != null ? section.sort() : false;
        if (sortItems(SectionType.STRING_ID)) {
            zSort = true;
        }
        if (sortItems(SectionType.TYPE_ID)) {
            zSort = true;
        }
        if (sortItems(SectionType.PROTO_ID)) {
            zSort = true;
        }
        if (sortItems(SectionType.FIELD_ID)) {
            zSort = true;
        }
        if (sortItems(SectionType.METHOD_ID)) {
            zSort = true;
        }
        if (sortItems(SectionType.CLASS_ID)) {
            return true;
        }
        return zSort;
    }

    public void clearPoolMap(SectionType<?> sectionType) {
        Section section = getSection(sectionType);
        if (section != null) {
            section.clearPoolMap();
        }
    }

    private boolean contains(SectionType<?> sectionType, Key key) {
        Section section = getSection(sectionType);
        if (section != null) {
            return section.contains(key);
        }
        return false;
    }

    public boolean merge(MergeOptions mergeOptions, ClassId classId) {
        if (classId == null) {
            mergeOptions.onMergeError((DexLayoutBlock) getParentInstance(DexLayoutBlock.class), classId, "Null class id");
            return false;
        }
        if (classId.getParent() == null) {
            mergeOptions.onMergeError((DexLayoutBlock) getParentInstance(DexLayoutBlock.class), classId, "Destroyed class id");
            return false;
        }
        if (classId.getParent(SectionList.class) == this) {
            mergeOptions.onMergeError((DexLayoutBlock) getParentInstance(DexLayoutBlock.class), classId, "Class id is on same section");
            return false;
        }
        if (mergeOptions.skipMerging(classId, classId.getKey())) {
            return false;
        }
        SectionType<ClassId> sectionType = SectionType.CLASS_ID;
        if (contains(sectionType, classId.getKey())) {
            mergeOptions.onDuplicate(classId);
            return false;
        }
        if (!canAddAll(classId.listUsedIds())) {
            mergeOptions.onDexFull((DexLayoutBlock) getParentInstance(DexLayoutBlock.class), classId);
            return false;
        }
        ((ClassId) getOrCreateSection(sectionType).getOrCreate(classId.getKey())).merge(classId);
        if (mergeOptions.relocateClass()) {
            classId.removeSelf();
        }
        mergeOptions.onMergeSuccess(classId, classId.getKey());
        return true;
    }
}
