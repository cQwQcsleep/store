package com.reandroid.dex.model;

import com.reandroid.dex.common.SectionItem;
import com.reandroid.dex.key.Key;
import com.reandroid.dex.key.TypeKey;
import com.reandroid.dex.sections.Marker;
import com.reandroid.dex.sections.Section;
import com.reandroid.dex.sections.SectionType;
import com.reandroid.utils.ObjectsUtil;
import com.reandroid.utils.collection.CombiningIterator;
import com.reandroid.utils.collection.EmptyIterator;
import com.reandroid.utils.collection.SingleIterator;
import java.util.Iterator;
import java.util.function.Predicate;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public interface DexClassModule extends DexClassRepository {
    void addMarker(Marker marker);

    @Override // com.reandroid.dex.model.DexClassRepository
    void clearPoolMap();

    @Override // com.reandroid.dex.model.DexClassRepository
    default int getCount(SectionType<?> sectionType) {
        Section section = getSection(sectionType);
        if (section != null) {
            return section.getCount();
        }
        return 0;
    }

    @Override // com.reandroid.dex.model.DexClassRepository
    DexClass getDexClass(TypeKey typeKey);

    @Override // com.reandroid.dex.model.DexClassRepository
    Iterator<DexClass> getDexClasses(Predicate<? super TypeKey> predicate);

    @Override // com.reandroid.dex.model.DexClassRepository
    Iterator<DexClass> getDexClassesCloned(Predicate<? super TypeKey> predicate);

    Iterator<DexClass> getExtendingClasses(TypeKey typeKey);

    Iterator<DexClass> getImplementClasses(TypeKey typeKey);

    @Override // com.reandroid.dex.model.DexClassRepository
    default <T extends SectionItem> T getItem(SectionType<T> sectionType, int i) {
        Section<T> section = getSection(sectionType);
        if (section != null) {
            return (T) section.get(i);
        }
        return null;
    }

    @Override // com.reandroid.dex.model.DexClassRepository
    default <T extends SectionItem> Iterator<T> getItems(SectionType<T> sectionType) {
        Section<T> section = getSection(sectionType);
        return section != null ? section.iterator() : EmptyIterator.of();
    }

    @Override // com.reandroid.dex.model.DexClassRepository
    Iterator<Marker> getMarkers();

    int getOffset();

    DexClass getOrCreateClass(TypeKey typeKey);

    <T extends SectionItem> Section<T> getOrCreateSection(SectionType<T> sectionType);

    <T extends SectionItem> Section<T> getSection(SectionType<T> sectionType);

    default DexSectionInfo getSectionInfo(SectionType<?> sectionType) {
        Iterator<DexSectionInfo> sectionInfo = getSectionInfo();
        while (sectionInfo.hasNext()) {
            DexSectionInfo next = sectionInfo.next();
            if (ObjectsUtil.equals(sectionType, next.getSectionType())) {
                return next;
            }
        }
        return null;
    }

    Iterator<DexSectionInfo> getSectionInfo();

    @Override // com.reandroid.dex.model.DexClassRepository
    default <T extends SectionItem> Iterator<Section<T>> getSections(SectionType<T> sectionType) {
        return SingleIterator.of(getSection(sectionType));
    }

    @Override // com.reandroid.dex.model.DexClassRepository
    default Iterator<DexClass> getSuccessorClasses(TypeKey typeKey) {
        return CombiningIterator.two(getExtendingClasses(typeKey), getImplementClasses(typeKey));
    }

    @Override // com.reandroid.dex.model.DexClassRepository
    int getVersion();

    boolean isMultiLayoutEntry();

    @Override // com.reandroid.dex.model.DexClassRepository
    boolean removeClasses(Predicate<? super DexClass> predicate);

    @Override // com.reandroid.dex.model.DexClassRepository
    <T1 extends SectionItem> boolean removeEntries(SectionType<T1> sectionType, Predicate<T1> predicate);

    @Override // com.reandroid.dex.model.DexClassRepository
    <T1 extends SectionItem> boolean removeEntriesWithKey(SectionType<T1> sectionType, Predicate<? super Key> predicate);

    @Override // com.reandroid.dex.model.DexClassRepository
    <T1 extends SectionItem> boolean removeEntry(SectionType<T1> sectionType, Key key);

    @Override // com.reandroid.dex.model.DexClassRepository
    void setVersion(int i);

    @Override // com.reandroid.dex.model.DexClassRepository
    int shrink();

    @Override // com.reandroid.dex.model.DexClassRepository
    boolean sort();

    @Override // com.reandroid.dex.model.DexClassRepository
    default <T extends SectionItem> T getItem(SectionType<T> sectionType, Key key) {
        Section<T> section = getSection(sectionType);
        if (section != null) {
            return (T) section.get(key);
        }
        return null;
    }
}
