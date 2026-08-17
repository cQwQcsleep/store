package com.reandroid.dex.data;

import com.reandroid.dex.base.DexPositionAlign;
import com.reandroid.dex.base.PositionAlignedItem;
import com.reandroid.dex.base.UsageMarker;
import com.reandroid.dex.common.FullRefresh;
import com.reandroid.dex.data.AnnotationItem;
import com.reandroid.dex.id.IdItem;
import com.reandroid.dex.key.AnnotationItemKey;
import com.reandroid.dex.key.AnnotationSetKey;
import com.reandroid.dex.key.Key;
import com.reandroid.dex.key.KeyList;
import com.reandroid.dex.key.KeyReference;
import com.reandroid.dex.key.TypeKey;
import com.reandroid.dex.program.AnnotatedProgram;
import com.reandroid.dex.sections.SectionType;
import com.reandroid.dex.smali.SmaliFormat;
import com.reandroid.dex.smali.SmaliWriter;
import com.reandroid.dex.smali.model.SmaliAnnotationItem;
import com.reandroid.dex.smali.model.SmaliAnnotationSet;
import com.reandroid.dex.value.DexValueBlock;
import com.reandroid.utils.ObjectsUtil;
import com.reandroid.utils.collection.CollectionUtil;
import com.reandroid.utils.collection.FilterIterator;
import com.reandroid.utils.collection.IterableIterator;
import java.io.IOException;
import java.util.Iterator;
import java.util.function.Predicate;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class AnnotationSet extends IntegerDataItemList<AnnotationItem> implements KeyReference, SmaliFormat, PositionAlignedItem, FullRefresh, AnnotatedProgram {
    public AnnotationSet() {
        super(SectionType.ANNOTATION_ITEM, UsageMarker.USAGE_ANNOTATION, new DexPositionAlign());
    }

    private AnnotationItem addNew(TypeKey typeKey, String str) {
        AnnotationItem orCreate = getOrCreate(typeKey);
        orCreate.getOrCreateElement(str);
        return orCreate;
    }

    private AnnotationItem addNewItem(TypeKey typeKey) {
        AnnotationItem annotationItemAddNewItem = addNewItem();
        annotationItemAddNewItem.setType(typeKey);
        return annotationItemAddNewItem;
    }

    @Override // com.reandroid.dex.program.AnnotatedProgram
    public void addAnnotation(AnnotationItemKey annotationItemKey) {
        remove(annotationItemKey.getType());
        addNewItem().setKey(annotationItemKey);
    }

    @Override // com.reandroid.dex.smali.SmaliFormat
    public void append(SmaliWriter smaliWriter) throws IOException {
        smaliWriter.appendAllWithDoubleNewLine(iterator());
    }

    @Override // com.reandroid.dex.program.AnnotatedProgram
    public void clearAnnotations() {
        clear();
    }

    public boolean contains(TypeKey typeKey) {
        Iterator<AnnotationItem> it = iterator();
        while (it.hasNext()) {
            if (typeKey.equals(it.next().getType())) {
                return true;
            }
        }
        return false;
    }

    public void fromSmali(SmaliAnnotationSet smaliAnnotationSet) {
        Iterator<SmaliAnnotationItem> it = smaliAnnotationSet.iterator();
        while (it.hasNext()) {
            getOrCreate(it.next().getKey());
        }
    }

    public AnnotationItem get(TypeKey typeKey, String str) {
        for (AnnotationItem annotationItem : this) {
            if (typeKey.equals(annotationItem.getType()) && annotationItem.containsName(str)) {
                return annotationItem;
            }
        }
        return null;
    }

    public Iterator<AnnotationItem> getAll(final TypeKey typeKey) {
        return FilterIterator.of(iterator(), new Predicate() { // from class: b90
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return typeKey.equals(((AnnotationItem) obj).getType());
            }
        });
    }

    @Override // com.reandroid.dex.program.AnnotatedProgram
    public AnnotationItemKey getAnnotation(TypeKey typeKey) {
        AnnotationItem annotationItem = get(typeKey);
        if (annotationItem != null) {
            return annotationItem.getKey();
        }
        return null;
    }

    public AnnotationElement getElement(TypeKey typeKey, String str) {
        AnnotationItem annotationItem = get(typeKey);
        if (annotationItem != null) {
            return annotationItem.getElement(str);
        }
        return null;
    }

    @Override // com.reandroid.dex.data.IntegerDataItemList
    public AnnotationItemKey getItemKey(int i) {
        return (AnnotationItemKey) super.getItemKey(i);
    }

    @Override // com.reandroid.dex.data.IntegerDataItemList, com.reandroid.dex.data.DataItem, com.reandroid.dex.common.SectionItem, com.reandroid.dex.key.KeyItem
    public AnnotationSetKey getKey() {
        AnnotationItemKey[] annotationItemKeyArr = new AnnotationItemKey[size()];
        getItemKeys(annotationItemKeyArr);
        return (AnnotationSetKey) checkKey(AnnotationSetKey.of(annotationItemKeyArr));
    }

    public AnnotationItem getOrCreate(AnnotationItemKey annotationItemKey) {
        AnnotationItem annotationItem = get(annotationItemKey);
        return annotationItem == null ? addNewItem(annotationItemKey) : annotationItem;
    }

    @Override // com.reandroid.dex.common.SectionItem
    public SectionType<AnnotationSet> getSectionType() {
        return SectionType.ANNOTATION_SET;
    }

    public DexValueBlock<?> getValue(TypeKey typeKey, String str) {
        AnnotationElement element = getElement(typeKey, str);
        if (element != null) {
            return element.getValueBlock();
        }
        return null;
    }

    @Override // com.reandroid.dex.program.AnnotatedProgram
    public boolean hasAnnotation(TypeKey typeKey) {
        return contains(typeKey);
    }

    @Override // com.reandroid.dex.program.AnnotatedProgram
    public boolean hasAnnotations() {
        return !isEmpty();
    }

    @Override // com.reandroid.dex.common.SectionItem
    public boolean isBlank() {
        return isEmpty();
    }

    public void merge(AnnotationSet annotationSet) {
        if (annotationSet == this) {
            return;
        }
        Iterator<AnnotationItem> it = annotationSet.iterator();
        while (it.hasNext()) {
            addNewItem(it.next().getKey());
        }
    }

    @Override // com.reandroid.dex.common.FullRefresh
    public void refreshFull() {
        sort();
    }

    public boolean remove(final TypeKey typeKey) {
        return removeIf(new Predicate() { // from class: a90
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return ObjectsUtil.equals(typeKey, ((AnnotationItem) obj).getType());
            }
        });
    }

    @Override // com.reandroid.dex.program.AnnotatedProgram
    public boolean removeAnnotation(TypeKey typeKey) {
        return remove(typeKey);
    }

    @Override // com.reandroid.dex.program.AnnotatedProgram
    public boolean removeAnnotationIf(final Predicate<? super AnnotationItemKey> predicate) {
        return removeIf(new Predicate() { // from class: c90
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return predicate.test(((AnnotationItem) obj).getKey());
            }
        });
    }

    public void replaceKeys(Key key, Key key2) {
        Iterator<AnnotationItem> it = iterator();
        while (it.hasNext()) {
            it.next().replaceKeys(key, key2);
        }
    }

    @Override // com.reandroid.dex.program.AnnotatedProgram
    public void setAnnotation(AnnotationSetKey annotationSetKey) {
        setKey(annotationSetKey);
    }

    @Override // com.reandroid.dex.data.IntegerDataItemList, com.reandroid.dex.key.KeyReference
    public void setKey(Key key) {
        super.setKey(key);
    }

    public boolean sort() {
        return super.sort(CollectionUtil.getComparator());
    }

    public String toString() {
        if (getOffsetReference() == null) {
            return super.toString();
        }
        if (size() == 0) {
            return "EMPTY";
        }
        StringBuilder sb = new StringBuilder();
        boolean z = false;
        for (AnnotationItem annotationItem : this) {
            if (z) {
                sb.append(',');
            }
            sb.append(annotationItem);
            z = true;
        }
        return sb.toString();
    }

    @Override // com.reandroid.dex.data.DataItem, com.reandroid.dex.common.IdUsageIterator
    public Iterator<IdItem> usedIds() {
        return new IterableIterator<AnnotationItem, IdItem>(iterator()) { // from class: com.reandroid.dex.data.AnnotationSet.1
            public Iterator<IdItem> iterator(AnnotationItem annotationItem) {
                return annotationItem.usedIds();
            }
        };
    }

    public static class EmptyAnnotationSet extends AnnotationSet {
        public EmptyAnnotationSet() {
            addUsageType(UsageMarker.USAGE_ANNOTATION);
        }

        @Override // com.reandroid.dex.common.SectionItem, com.reandroid.dex.base.UsageMarker
        public void clearUsageType() {
            if (getSection(getSectionType()).getCount() != 1) {
                super.clearUsageType();
            }
        }

        @Override // com.reandroid.dex.data.AnnotationSet, com.reandroid.dex.data.IntegerDataItemList
        public /* bridge */ /* synthetic */ Key getItemKey(int i) {
            return super.getItemKey(i);
        }

        @Override // com.reandroid.dex.data.AnnotationSet, com.reandroid.dex.data.IntegerDataItemList, com.reandroid.dex.data.DataItem, com.reandroid.dex.common.SectionItem, com.reandroid.dex.key.KeyItem
        public /* bridge */ /* synthetic */ Key getKey() {
            return super.getKey();
        }

        @Override // com.reandroid.dex.data.AnnotationSet, com.reandroid.dex.common.SectionItem
        public boolean isBlank() {
            return isRemoved();
        }

        @Override // com.reandroid.dex.data.DataItem, com.reandroid.dex.common.SectionItemContainer
        public void onRefreshed() {
            super.onRefreshed();
            if (getSection(getSectionType()).getCount() == 1) {
                addUsageType(UsageMarker.USAGE_ANNOTATION);
            }
        }

        @Override // com.reandroid.dex.data.AnnotationSet, com.reandroid.dex.data.IntegerDataItemList, com.reandroid.dex.data.DataItem, com.reandroid.dex.common.SectionItem, com.reandroid.dex.key.KeyItem
        public /* bridge */ /* synthetic */ KeyList getKey() {
            return super.getKey();
        }
    }

    public boolean remove(final AnnotationItemKey annotationItemKey) {
        return removeIf(new Predicate() { // from class: d90
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return ObjectsUtil.equals(annotationItemKey, ((AnnotationItem) obj).getKey());
            }
        });
    }

    @Override // com.reandroid.dex.program.AnnotatedProgram
    public AnnotationSetKey getAnnotation() {
        return getKey();
    }

    public AnnotationItem getOrCreate(TypeKey typeKey, String str) {
        AnnotationItem annotationItem = get(typeKey, str);
        return annotationItem != null ? annotationItem : addNew(typeKey, str);
    }

    public AnnotationItem getOrCreate(TypeKey typeKey) {
        AnnotationItem annotationItem = get(typeKey);
        return annotationItem != null ? annotationItem : addNewItem(typeKey);
    }

    public AnnotationItem get(TypeKey typeKey) {
        for (AnnotationItem annotationItem : this) {
            if (typeKey.equals(annotationItem.getType())) {
                return annotationItem;
            }
        }
        return null;
    }

    public AnnotationItem get(AnnotationItemKey annotationItemKey) {
        for (AnnotationItem annotationItem : this) {
            if (annotationItemKey.equals(annotationItem.getKey())) {
                return annotationItem;
            }
        }
        return null;
    }
}
