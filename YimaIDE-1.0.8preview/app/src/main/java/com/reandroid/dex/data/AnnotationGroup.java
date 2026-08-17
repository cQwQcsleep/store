package com.reandroid.dex.data;

import com.reandroid.dex.base.UsageMarker;
import com.reandroid.dex.id.IdItem;
import com.reandroid.dex.key.ArrayKey;
import com.reandroid.dex.key.Key;
import com.reandroid.dex.key.KeyReference;
import com.reandroid.dex.sections.SectionType;
import com.reandroid.utils.collection.IterableIterator;
import java.util.Iterator;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class AnnotationGroup extends IntegerDataItemList<AnnotationSet> implements KeyReference {
    public AnnotationGroup() {
        super(SectionType.ANNOTATION_SET, UsageMarker.USAGE_ANNOTATION, null);
    }

    @Override // com.reandroid.dex.data.IntegerDataItemList, com.reandroid.dex.data.DataItem, com.reandroid.dex.common.SectionItem, com.reandroid.dex.key.KeyItem
    public ArrayKey getKey() {
        return (ArrayKey) checkKey(super.getKey());
    }

    @Override // com.reandroid.dex.common.SectionItem
    public SectionType<AnnotationGroup> getSectionType() {
        return SectionType.ANNOTATION_GROUP;
    }

    public void merge(AnnotationGroup annotationGroup) {
        int size = annotationGroup.size();
        for (int i = 0; i < size; i++) {
            addNewItem(annotationGroup.getItemKey(i));
        }
    }

    @Override // com.reandroid.dex.data.IntegerDataItemList
    public void removeNulls() {
    }

    public void replaceKeys(Key key, Key key2) {
        Iterator<AnnotationSet> it = iterator();
        while (it.hasNext()) {
            it.next().replaceKeys(key, key2);
        }
    }

    @Override // com.reandroid.dex.data.IntegerDataItemList, com.reandroid.dex.key.KeyReference
    public void setKey(Key key) {
        super.setKey(key);
    }

    public String toString() {
        return getKey().toString("\n");
    }

    @Override // com.reandroid.dex.data.DataItem, com.reandroid.dex.common.IdUsageIterator
    public Iterator<IdItem> usedIds() {
        return new IterableIterator<AnnotationSet, IdItem>(iterator()) { // from class: com.reandroid.dex.data.AnnotationGroup.1
            public Iterator<IdItem> iterator(AnnotationSet annotationSet) {
                return annotationSet.usedIds();
            }
        };
    }
}
