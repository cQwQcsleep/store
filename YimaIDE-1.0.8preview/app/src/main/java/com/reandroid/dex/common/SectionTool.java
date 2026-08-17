package com.reandroid.dex.common;

import com.reandroid.arsc.base.Block;
import com.reandroid.dex.key.Key;
import com.reandroid.dex.pool.DexSectionPool;
import com.reandroid.dex.sections.Section;
import com.reandroid.dex.sections.SectionList;
import com.reandroid.dex.sections.SectionType;
import java.util.Iterator;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public interface SectionTool {
    static <T extends SectionItem> int compareIdx(Iterator<T> it, Iterator<T> it2) {
        while (it.hasNext() && it2.hasNext()) {
            int iCompareIdx = compareIdx(it.next(), it2.next());
            if (iCompareIdx != 0) {
                return iCompareIdx;
            }
        }
        if (it.hasNext()) {
            return 1;
        }
        return it2.hasNext() ? -1 : 0;
    }

    static <T extends Block> int compareIndex(T t, T t2) {
        if (t == t2) {
            return 0;
        }
        if (t == null) {
            return 1;
        }
        if (t2 == null) {
            return -1;
        }
        int index = t.getIndex();
        int index2 = t2.getIndex();
        if (index == index2) {
            return 0;
        }
        return index > index2 ? 1 : -1;
    }

    default <T1 extends SectionItem> T1 createSectionItem(SectionType<T1> sectionType) {
        Section<T1> orCreateSection = getOrCreateSection(sectionType);
        if (orCreateSection != null) {
            return (T1) orCreateSection.createItem();
        }
        return null;
    }

    default <T1 extends SectionItem> Section<T1> getOrCreateSection(SectionType<T1> sectionType) {
        SectionList sectionList = getSectionList();
        if (sectionList != null) {
            return sectionList.getOrCreateSection(sectionType);
        }
        return null;
    }

    default <T1 extends SectionItem> T1 getOrCreateSectionItem(SectionType<T1> sectionType, Key key) {
        Section<T1> orCreateSection;
        DexSectionPool<T> pool;
        if (key == null || (orCreateSection = getOrCreateSection(sectionType)) == null || (pool = orCreateSection.getPool()) == 0) {
            return null;
        }
        return (T1) pool.getOrCreate(key);
    }

    default <T1 extends SectionItem> DexSectionPool<T1> getPool(SectionType<T1> sectionType) {
        Section<T1> section = getSection(sectionType);
        if (section != null) {
            return (DexSectionPool<T1>) section.getPool();
        }
        return null;
    }

    default <T1 extends SectionItem> Section<T1> getSection(SectionType<T1> sectionType) {
        SectionList sectionList = getSectionList();
        if (sectionList != null) {
            return sectionList.getSection(sectionType);
        }
        return null;
    }

    default <T1 extends SectionItem> T1 getSectionItem(SectionType<T1> sectionType, Key key) {
        Section<T1> section = getSection(sectionType);
        if (section != null) {
            return (T1) section.getSectionItem(key);
        }
        return null;
    }

    default SectionList getSectionList() {
        return (SectionList) ((Block) this).getParent(SectionList.class);
    }

    default boolean isReading() {
        SectionList sectionList = getSectionList();
        return sectionList == null || sectionList.isReading();
    }

    default <T1 extends SectionItem> T1 getSectionItem(SectionType<T1> sectionType, int i) {
        Section<T1> section = getSection(sectionType);
        if (section != null) {
            return (T1) section.getSectionItem(i);
        }
        return null;
    }

    static <T extends SectionItem> int compareIdx(T t, T t2) {
        if (t == t2) {
            return 0;
        }
        if (t == null) {
            return 1;
        }
        if (t2 == null) {
            return -1;
        }
        int idx = t.getIdx();
        int idx2 = t2.getIdx();
        if (idx == idx2) {
            return 0;
        }
        return idx > idx2 ? 1 : -1;
    }
}
