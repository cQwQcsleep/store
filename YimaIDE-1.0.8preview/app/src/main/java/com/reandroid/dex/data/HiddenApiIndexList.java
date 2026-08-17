package com.reandroid.dex.data;

import com.reandroid.arsc.base.Creator;
import com.reandroid.arsc.container.BlockList;
import com.reandroid.arsc.io.BlockReader;
import com.reandroid.dex.id.ClassId;
import com.reandroid.dex.key.Key;
import com.reandroid.dex.key.TypeKey;
import com.reandroid.dex.sections.Section;
import com.reandroid.dex.sections.SectionList;
import com.reandroid.dex.sections.SectionType;
import com.reandroid.utils.CompareUtil;
import com.reandroid.utils.ObjectsUtil;
import com.reandroid.utils.collection.FilterIterator;
import java.io.IOException;
import java.util.Iterator;
import java.util.function.Predicate;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
class HiddenApiIndexList extends BlockList<HiddenApiIndex> implements Iterable<HiddenApiIndex> {

    public static class HiddenApiIndexCreator implements Creator<HiddenApiIndex> {
        private final HiddenApiIndexList hiddenApiIndexList;

        public HiddenApiIndexCreator(HiddenApiIndexList hiddenApiIndexList) {
            this.hiddenApiIndexList = hiddenApiIndexList;
        }

        @Override // com.reandroid.arsc.base.Creator
        public HiddenApiIndex newInstance() {
            throw new RuntimeException("Must call newInstanceAt(index)");
        }

        @Override // com.reandroid.arsc.base.Creator
        public HiddenApiIndex newInstanceAt(int i) {
            return new HiddenApiIndex((ClassId) this.hiddenApiIndexList.getClassIdSection().get(i));
        }
    }

    public HiddenApiIndexList() {
        setCreator(new HiddenApiIndexCreator(this));
    }

    private void ensureClassesLinked() {
        removeIf(new Predicate() { // from class: com.reandroid.dex.data.c
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return ((HiddenApiIndex) obj).isNull();
            }
        });
        Section<ClassId> classIdSection = getClassIdSection();
        if (classIdSection == null) {
            return;
        }
        int count = classIdSection.getCount();
        boolean z = false;
        for (int i = 0; i < count; i++) {
            ClassId classId = (ClassId) classIdSection.get(i);
            HiddenApiIndex hiddenApiIndex = get(classId);
            if (!z) {
                z = classId.getIndex() != hiddenApiIndex.getIndex();
            }
        }
        if (z) {
            sort(CompareUtil.getComparableComparator());
        }
    }

    private ClassId getClassId(TypeKey typeKey) {
        Section<ClassId> classIdSection = getClassIdSection();
        if (classIdSection != null) {
            return (ClassId) classIdSection.get(typeKey);
        }
        return null;
    }

    private int getClassIdSectionCount() {
        Section<ClassId> classIdSection = getClassIdSection();
        if (classIdSection != null) {
            return classIdSection.getCount();
        }
        return 0;
    }

    private SectionList getParentSectionList() {
        return (SectionList) getParentInstance(SectionList.class);
    }

    @Override // com.reandroid.arsc.container.BlockList
    public int countBytes() {
        return size() * 4;
    }

    public HiddenApiIndex createNext(ClassId classId, HiddenApiData hiddenApiData) {
        HiddenApiIndex hiddenApiIndex = new HiddenApiIndex(classId);
        add(hiddenApiIndex);
        hiddenApiIndex.linkData(hiddenApiData);
        return hiddenApiIndex;
    }

    public HiddenApiIndex get(ClassId classId) {
        if (classId == null || classId.isRemoved()) {
            return null;
        }
        HiddenApiIndex hiddenApiIndex = get(classId.getIndex());
        if (hiddenApiIndex != null && hiddenApiIndex.getClassId() == classId) {
            return hiddenApiIndex;
        }
        int size = size();
        for (int i = 0; i < size; i++) {
            HiddenApiIndex hiddenApiIndex2 = get(i);
            if (hiddenApiIndex2.getClassId() == classId) {
                return hiddenApiIndex2;
            }
        }
        return ((HiddenApiRestrictions) getParentInstance(HiddenApiRestrictions.class)).createNew(classId);
    }

    public Section<ClassId> getClassIdSection() {
        SectionList parentSectionList = getParentSectionList();
        return parentSectionList != null ? parentSectionList.getSection(SectionType.CLASS_ID) : (Section) ObjectsUtil.getNull();
    }

    public HiddenApiFlagValue getFlagValue(Key key) {
        HiddenApiIndex hiddenApiIndex = get(key.getDeclaring());
        if (hiddenApiIndex != null) {
            return hiddenApiIndex.get(key);
        }
        return null;
    }

    public Iterator<HiddenApiIndex> getHiddenApis() {
        return FilterIterator.of(iterator(), new Predicate() { // from class: com.reandroid.dex.data.b
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return ((HiddenApiIndex) obj).hasValidDataOffset();
            }
        });
    }

    public boolean isAllNoRestrictions() {
        Iterator<HiddenApiIndex> it = iterator();
        while (it.hasNext()) {
            if (!it.next().isAllNoRestrictions()) {
                return false;
            }
        }
        return true;
    }

    @Override // com.reandroid.arsc.container.BlockList
    public void onPreRefresh() {
        super.onPreRefresh();
        ensureClassesLinked();
    }

    public void onReadBytes(BlockReader blockReader) throws IOException {
        setSize(getClassIdSectionCount());
        super.readChildes(blockReader);
    }

    public HiddenApiIndex get(TypeKey typeKey) {
        return get(getClassId(typeKey));
    }
}
